package spring.mysql.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/exercise")
public class MainController {
    @Autowired
    private ExerciseApplicationService exerciseApplicationService;

    @PostMapping("/insert")
    public ExerciseApplication insertSatResult(@RequestBody ExerciseApplication satResult) {
        return exerciseApplicationService.insertSatResult(satResult);
    }

    @GetMapping("/viewAll")
    public Iterable<ExerciseApplication> getAllSatResults() {
        return exerciseApplicationService.getAllSatResults();
    }

    @GetMapping("/getRank/{name}")
    public int getRank(@PathVariable String name) {
        return exerciseApplicationService.getRank(name);
    }

    @PutMapping("/updateScore/{name}/{newScore}")
    public ExerciseApplication updateScore(@PathVariable String name, @PathVariable int newScore) {
        return exerciseApplicationService.updateScore(name, newScore);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteSatResult(@PathVariable String name) {
        exerciseApplicationService.deleteSatResult(name);
    }
}
