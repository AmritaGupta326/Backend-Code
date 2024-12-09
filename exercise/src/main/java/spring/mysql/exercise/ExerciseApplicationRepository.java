package spring.mysql.exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface ExerciseApplicationRepository extends CrudRepository<ExerciseApplication, Integer> {
    ExerciseApplication findByName(String name);
    @Transactional
    void deleteByName(String name);

}