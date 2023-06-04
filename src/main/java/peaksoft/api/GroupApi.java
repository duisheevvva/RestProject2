package peaksoft.api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;
import peaksoft.service.GroupService;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupApi {
    private final GroupService groupService;

    @GetMapping("/getAll")
    public List<GroupResponse> getAllCourses() {
        return groupService.getAllGroup();
    }
@PostMapping("/{courseId}")
public GroupResponse saveGroup(@Valid @PathVariable Long courseId, @RequestBody GroupRequest groupRequest) throws MyException {
        return groupService.saveGroup(courseId,groupRequest);
}

    @GetMapping("/getById/{id}")
    public GroupResponse getGroupById(@PathVariable("id") Long id) throws MyException {
        return groupService.getByIdGroup(id);
    }

    @PostMapping("/update/{id}")
    public GroupResponse updateGroup(@Valid @PathVariable("id") Long id,
                                     @RequestBody GroupRequest groupRequest) throws MyException {
        return groupService.updateGroupById(id, groupRequest);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteCourse(@PathVariable("id") Long id) throws MyException {
        return groupService.deleteById(id);
    }

}
