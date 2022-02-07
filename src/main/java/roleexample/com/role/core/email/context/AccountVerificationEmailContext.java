package roleexample.com.role.core.email.context;

import org.springframework.web.util.UriComponentsBuilder;
import roleexample.com.role.core.user.jpa.data.UserEntity;

public class AccountVerificationEmailContext extends AbstractEmailContext {

    private String token;

    @Override
    public<T> void init(T context){
        UserEntity customer = (UserEntity) context; //Pass the customer information
        put("firstName", customer.getFirstName());
        setTemplateLocation("emails/email-verification");
        setSubject("Complete your registration.");
        setFrom("no-reply@shortstories.com");
        setTo(customer.getEmail());
    }

    public void setToken(String token){
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/register/verify").queryParam("token", token).toUriString();
                put("verificationURL", url);
    }
}
