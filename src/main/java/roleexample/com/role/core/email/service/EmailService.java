package roleexample.com.role.core.email.service;

import roleexample.com.role.core.email.context.AbstractEmailContext;
import javax.mail.MessagingException;

public interface EmailService {

    void sendMail(final AbstractEmailContext email) throws MessagingException;
}
