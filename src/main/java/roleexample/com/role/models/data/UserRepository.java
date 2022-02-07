package roleexample.com.role.models.data;

import org.springframework.data.jpa.repository.JpaRepository;
import roleexample.com.role.core.user.jpa.data.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
}
