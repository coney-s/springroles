package roleexample.com.role.core.user.service;

import roleexample.com.role.core.exceptions.InvalidTokenException;
import roleexample.com.role.core.exceptions.UserAlreadyExistException;
import roleexample.com.role.core.user.jpa.data.UserEntity;
import roleexample.com.role.web.data.user.UserData;
import roleexample.com.role.core.exceptions.UnknownIdentifierException;

public interface UserService {



        void register(final UserData user) throws UserAlreadyExistException;
        boolean checkIfUserExist(final String email);
        void sendRegistrationConfirmationEmail(final UserEntity user);
        boolean verifyUser(final String token) throws InvalidTokenException;
        UserEntity getUserById(final String id) throws UnknownIdentifierException;
    }

