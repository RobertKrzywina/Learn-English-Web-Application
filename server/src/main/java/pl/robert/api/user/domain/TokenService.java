package pl.robert.api.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class TokenService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    TokenRepository tokenRepository;
    UserService userService;
    JavaMailSender mailSender;

    void generateToken(User user) {
        Token token = new Token(user);
        tokenRepository.save(token);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            String htmlMsg = "To confirm your account, please follow the link below:<br>" +
                    "<a href='http://localhost:4200/confirm-account?token=" + token.getConfirmationToken() + "'>" +
                    "http://localhost:4200/confirm-account?token=" + token.getConfirmationToken() + "</a>";
            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(user.getEmail());
            helper.setSubject("Complete Registration!");
            helper.setFrom("Rob");
            mailSender.send(mimeMessage);
        } catch (MessagingException | MailException e) {
            tokenRepository.delete(token);
            userService.delete(user);
            logger.info("Deleting token and user cause {} appeared...", e.getClass());
            throw new MailSendException("MessagingException | MailException");
        }
    }
}
