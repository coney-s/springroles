package roleexample.com.role.services;

import roleexample.com.role.core.exceptions.UserAlreadyExistException;
import roleexample.com.role.web.data.user.UserData;

public interface UserService {
    void register(UserData user) throws UserAlreadyExistException;

    boolean checkIfUserExist(String email);
}
