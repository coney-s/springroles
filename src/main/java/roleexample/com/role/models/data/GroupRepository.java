package roleexample.com.role.models.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import roleexample.com.role.models.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
