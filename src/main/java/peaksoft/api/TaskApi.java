package peaksoft.api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.TaskResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;
import peaksoft.service.TaskService;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskApi {
    private final TaskService taskService;

    @GetMapping("/getAll")
    public List<TaskResponse> getAllTask() {
        return taskService.getAllTask();
    }


    @PostMapping("/save")
    public TaskResponse saveTask(@Valid  @RequestBody TaskRequest taskRequest) throws MyException {
        return taskService.saveTask(taskRequest.getLessonId(),taskRequest);
    }


    @GetMapping("/getById/{id}")
    public TaskResponse getTaskById( @PathVariable("id") Long id) throws MyException {
        return taskService.getByIdTask(id);
    }

    @PostMapping("/update/{id}")
    public TaskResponse updateTask(@Valid @PathVariable("id") Long id,
                                         @RequestBody TaskRequest taskRequest) throws MyException {
        return taskService.updateTaskById(id,taskRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteInstructor(@PathVariable("id") Long id) throws MyException {
        return taskService.deleteById(id);
    }
}
