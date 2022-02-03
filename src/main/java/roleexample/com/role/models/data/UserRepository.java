package roleexample.com.role.models.data;

import org.springframework.data.jpa.repository.JpaRepository;
import roleexample.com.role.core.user.jpa.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    User findByEmail(String email);
}
