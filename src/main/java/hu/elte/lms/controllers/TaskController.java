package hu.elte.lms.controllers;

import hu.elte.lms.entities.Submission;
import hu.elte.lms.entities.Task;
import hu.elte.lms.entities.User;
import hu.elte.lms.repositories.SubmissionRepository;
import hu.elte.lms.repositories.TaskRepository;
import hu.elte.lms.repositories.UserRepository;
import hu.elte.lms.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    SubmissionRepository submissionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticatedUser authenticatedUser;

    private User getUser(Principal principal){
        String username =  principal.getName();
        return userRepository.findByUsername(username).get();
    }
    @GetMapping("")
    public String list(Model model, Principal principal){
        User user = getUser(principal);
        Iterable<Task> tasks = user.getRole() == User.Role.ROLE_USER // each teacher can see their own tasks but lets assume that the students that we have takes courses from all teachers so they can see all the tasks from teachers
                ? taskRepository.findAll() :
                user.getTasks();
        model.addAttribute("tasks", tasks);
        return "list";
    }
    @GetMapping("/{id}")
    public String get(@PathVariable Integer id, Model model,Principal principal) throws Exception {
        Optional<Task> oTask = taskRepository.findById(id);
        if (oTask.isPresent()) {
            Task task = oTask.get();
            model.addAttribute("task", task);
            model.addAttribute("submission", new Submission());
            return "task";
        }
        throw new Exception("No such task id!");
    }

    @GetMapping("/submission/{id}")
    public String getSubmission(@PathVariable Integer id, Model model) throws Exception {
        Optional<Submission> oSubmission = submissionRepository.findById(id);
        if(oSubmission.isPresent()) {
            double grade = 0;
            Submission submission = oSubmission.get();
            model.addAttribute("submission", submission);
            return "submission";
        }
        throw new Exception("No such submission id");
    }

    @PostMapping("/submission/{id}")
    public String gradeSubmission(@PathVariable Integer id, @Valid Submission submission, BindingResult bindingResult,
                                  Model model, Principal principal) {
        System.out.println(submission.toString() + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (bindingResult.hasErrors()) {
            model.addAttribute("submission",submission);
            return "redirect:/tasks/submission/"+id;
        }

        Submission subDb = submissionRepository.findById(submission.getId()).get();
        submission.setTask(subDb.getTask());
        submission.setGraded(true);
        submissionRepository.save(submission);
        return "redirect:/tasks/" + submission.getTask().getId();
    }
    @PostMapping("/{taskId}/submit")
    public String submitSolution(@PathVariable Integer taskId, Submission submission, Model model, Principal principal){
        if(submission.getSolution() == null) {
            model.addAttribute("task", taskRepository.findById(taskId).get());
            model.addAttribute("submission", new Submission());
            return "redirect:/tasks/" + taskId;
        }
        String username = principal.getName();
        User user = userRepository.findByUsername(username).get();
        Task task = taskRepository.findById(taskId).get();
        LocalDateTime currTime = LocalDateTime.now();
        submission.setName(user.getName() + " " +  user.getSurname());
        submission.setGraded(false);
        submission.setUser(user);
        submission.setTask(task);
        submission.setSubmissionDate(currTime);
        submissionRepository.save(submission);
        return "redirect:/tasks";
    }

    @GetMapping("/new")
    public String taskForm(Model model){
        model.addAttribute("task", new Task());
        return "task-form";
    }
    @PostMapping("/new")
    public String addTask(@Valid Task task, BindingResult bindingResult,
                        Model model, Principal principal){
        if(bindingResult.hasErrors()) {
            return "task-form";
        }
        String username = principal.getName();
        User user = userRepository.findByUsername(username).get();
        task.setUser(user);
        taskRepository.save(task);
        return "redirect:/tasks";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}/edit")
    public  String editForm(@PathVariable Integer id, Model model) throws Exception{
        Optional<Task> oTask = taskRepository.findById(id);
        if(oTask.isPresent()) {
            model.addAttribute("task", oTask.get());
            return "task-form";
        }
        throw new Exception("No such a task");
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/{id}/edit")
    public String editTask(@PathVariable Integer id, @Valid Task task, BindingResult bindingResult,
                           Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("task",task);
            return "task-form";
        }
        task.setUser(userRepository.findByUsername(principal.getName()).get());
        taskRepository.save(task);
        return "redirect:/tasks";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Integer id){
        try {
            taskRepository.deleteById(id);
        }
        catch (Exception e){ }
        return "redirect:/tasks";
    }
}
