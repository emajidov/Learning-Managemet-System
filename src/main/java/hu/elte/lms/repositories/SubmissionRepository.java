package hu.elte.lms.repositories;

import hu.elte.lms.entities.Submission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission,Integer> {
}
