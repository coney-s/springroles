package roleexample.com.role.core.email.context;

import org.springframework.web.util.UriComponentsBuilder;
import roleexample.com.role.core.user.jpa.data.UserEntity;

public class ForgotEmailPasswordContext extends AbstractEmailContext{

    private String token;

    public <T> void init(T context){
        UserEntity customer = (UserEntity) context;
        put("firstName", customer.getFirstName());
        setTemplateLocation("emails/forgot-password");
        setSubject("Forgotten Password");
        setFrom("no-reply@shortstories.com");
        setTo(customer.getEmail());
    }

    public void setToken(String token){
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url = UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/password/change").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}
