package roleexample.com.role.core.user.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import roleexample.com.role.core.user.jpa.data.Group;

@Repository
public interface UserGroupRepository extends PagingAndSortingRepository<Group, Long> {
    Group findByCode(String code);
}
