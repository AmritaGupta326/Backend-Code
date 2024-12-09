package spring.mysql.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/exercise")
public class ExerciseApplicationService {
    @Autowired
    private ExerciseApplicationRepository exerciseApplicationRepository;
    public ExerciseApplication insertSatResult(ExerciseApplication satResult) {

        satResult.setPassed(satResult.getSatScore() > 30);

        return exerciseApplicationRepository.save(satResult);
    }

    public Iterable<ExerciseApplication> getAllSatResults() {
        return exerciseApplicationRepository.findAll();
    }

    public int getRank(String name) {
        ExerciseApplication result = exerciseApplicationRepository.findByName(name);
        List<ExerciseApplication> allResults = (List<ExerciseApplication>) exerciseApplicationRepository.findAll();
        //return exerciseApplicationRepository.findAll().stream()
        return allResults.stream()
                .filter(s -> s.getSatScore() > result.getSatScore())
                .mapToInt(s -> 1)
                .sum() + 1;
    }

    public ExerciseApplication updateScore(String name, int newScore) {
        ExerciseApplication result = exerciseApplicationRepository.findByName(name);
        if (result != null) {
            result.setSatScore(newScore);
            result.setPassed(newScore > 30);
            return exerciseApplicationRepository.save(result);
        }
        return null;
    }

    public void deleteSatResult(String name) {
        exerciseApplicationRepository.deleteByName(name);
    }
}
