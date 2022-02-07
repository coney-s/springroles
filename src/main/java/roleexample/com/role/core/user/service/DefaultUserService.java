package roleexample.com.role.core.user.service;

//The Following are the imports as listed in example.


/*











import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

import java.util.Objects; */



//Actual Imports below

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import roleexample.com.role.core.exceptions.InvalidTokenException;
import roleexample.com.role.core.exceptions.UnknownIdentifierException;
import roleexample.com.role.core.exceptions.UserAlreadyExistException;
import roleexample.com.role.core.security.jpa.SecureToken;
import roleexample.com.role.core.user.jpa.data.UserEntity;
import roleexample.com.role.core.user.jpa.data.Group;
import roleexample.com.role.core.user.jpa.repository.UserRepository;
import roleexample.com.role.core.user.jpa.repository.UserGroupRepository;
import roleexample.com.role.web.data.user.UserData;
import org.apache.commons.lang3.BooleanUtils;
import roleexample.com.role.core.email.context.AccountVerificationEmailContext;
import roleexample.com.role.core.email.service.EmailService;
import roleexample.com.role.core.security.token.repository.SecureTokenRepository;
import roleexample.com.role.core.security.token.SecureTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.lang3.StringUtils;


import javax.mail.MessagingException;
import java.util.Objects;



@Service("userService")
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
   SecureTokenRepository secureTokenRepository;

    @Autowired
    SecureTokenService secureTokenService;

    @Autowired
    UserGroupRepository groupRepository;

    @Value("${site.base.url.https}")
    private String baseURL;

    @Override
    public void register(UserData user) throws UserAlreadyExistException {
        if(checkIfUserExist(user.getEmail())){
            throw new UserAlreadyExistException("User already exists for this email");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        encodePassword(user, userEntity);
        updateCustomerGroup(userEntity);
        userRepository.save(userEntity);
        sendRegistrationConfirmationEmail(userEntity);

    }

    @Override
    public boolean checkIfUserExist(String email) {
        return false;
    }

    private void updateCustomerGroup(UserEntity userEntity){
        Group group = groupRepository.findByCode("customer");
        userEntity.addUserGroups(group);
    }

    @Override
    public void sendRegistrationConfirmationEmail(UserEntity user){
        SecureToken secureToken = secureTokenService.createSecureToken();
        secureToken.setUser(user);
       secureTokenRepository.save(secureToken);
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifyUser(String token) throws InvalidTokenException {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if(Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()){
            throw new InvalidTokenException("Token is not valid");
        }
        UserEntity user = userRepository.getOne(secureToken.getUser().getId());
        if(Objects.isNull(user)){
            return false;
        }
        user.setAccountVerified(true);
        userRepository.save(user);

        secureTokenService.removeToken(secureToken);
        return true;
    }

    @Override
    public UserEntity getUserById(String id) throws UnknownIdentifierException {
        UserEntity user = userRepository.findByEmail(id);
        if(user == null || BooleanUtils.isFalse(user.isAccountVerified())){
            throw new UnknownIdentifierException("Unable to find account or account is not active.");
        }
        return user;
    }

    private void encodePassword(UserData source, UserEntity target){
        target.setPassword(passwordEncoder.encode(source.getPassword()));
    }





}
