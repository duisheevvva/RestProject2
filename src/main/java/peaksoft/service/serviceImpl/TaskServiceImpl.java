package peaksoft.service.serviceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.TaskResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.exception.MyException;
import peaksoft.repository.LessonRepository;
import peaksoft.repository.TaskRepository;
import peaksoft.service.TaskService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;
    @Override
    public TaskResponse saveTask(Long lessonId, TaskRequest taskRequest) throws MyException {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new MyException("Lesson with id:" + lessonId + "not found"));
        Task task=new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
        lesson.getTasks().add(task);
        task.setLesson(lesson);
        taskRepository.save(task);
        return new TaskResponse(
                task.getId(),
                task.getTaskName(),
                task.getTaskText(),
                task.getDeadline()
        );
    }

    @Override
    public List<TaskResponse> getAllTask() {
        return taskRepository.getAll();
    }

    @Override
    public TaskResponse getByIdTask(Long id) throws MyException {
       Task task = taskRepository.findById(id)
                .orElseThrow(() -> new MyException("Task with id:" + id + "not found"));
        return new TaskResponse(
                task.getId(),
                task.getTaskName(),
                task.getTaskText(),
                task.getDeadline()
        );
    }

    @Override
    public TaskResponse updateTaskById(Long id, TaskRequest taskRequest) throws MyException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new MyException("Lesson with id:" + id + "not found"));
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
        taskRepository.save(task);
        return new TaskResponse(
                task.getId(),
                task.getTaskName(),
                task.getTaskText(),
                task.getDeadline()
        );
    }

    @Override
    public SimpleResponse deleteById(Long id) throws MyException {
        taskRepository.findById(id)
                .orElseThrow(() -> new MyException("Instructor with id:" + id + " not found"));
        taskRepository.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage("successfully deleted!")
                .build();
    }
    }

