package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import peaksoft.dto.response.GroupResponse;
import peaksoft.entity.Group;

import java.util.List;

@Repository
public interface GroupRepository  extends JpaRepository<Group,Long> {


    @Query("select new peaksoft.dto.response.GroupResponse(g.id,g.groupName,g.description,g.imageLink) from Group g")
    List<GroupResponse> getAll();
}
