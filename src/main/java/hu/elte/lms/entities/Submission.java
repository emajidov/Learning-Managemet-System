package hu.elte.lms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Length(min = 2)
    @Column
    @NotNull
    private String name;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime submissionDate;

    @Column
    @NotNull
    private String solution;

    @Column
    private Double grade;

    @Column
    private boolean isGraded;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", submissionDate=" + submissionDate +
                ", solution='" + solution + '\'' +
                ", grade=" + grade +
                ", isGraded=" + isGraded +
                ", task=" + task +
                ", user=" + user +
                '}';
    }
}
