package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.exception.MyException;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.GroupRepository;
import peaksoft.service.GroupService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    @Override
    public GroupResponse saveGroup(Long courseId, GroupRequest groupRequest) throws MyException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new MyException("Course with id:" + courseId + "not found"));
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setDescription(groupRequest.getDescription());
        group.setImageLink(groupRequest.getImageLink());
        course.setGroups(List.of(group));
        group.setCourses(List.of(course));
        groupRepository.save(group);
        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getDescription(),
                group.getImageLink()
        );

    }

    @Override
    public List<GroupResponse> getAllGroup() {
        return groupRepository.getAll();

    }

    @Override
    public GroupResponse getByIdGroup(Long id) throws MyException {
        Group group = groupRepository.findById(id).
                orElseThrow(() -> new MyException("Group with id:" + id + "is not found"));
        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getDescription(),
                group.getImageLink()
        );
    }

    @Override
    public GroupResponse updateGroupById(Long id, GroupRequest groupRequest) throws MyException {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new MyException("Group with id:" + id + "is not found"));
        group.setGroupName(groupRequest.getGroupName());
        group.setDescription(groupRequest.getDescription());
        group.setImageLink(groupRequest.getImageLink());
        groupRepository.save(group);
        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getDescription(),
                group.getImageLink()
        );
    }

    @Override
    public SimpleResponse deleteById(Long id) throws MyException {
        groupRepository.findById(id).orElseThrow(() -> new MyException("Group with id:" + id + "is not found"));
        groupRepository.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage("successfully deleted!")
                .build();
    }
}


