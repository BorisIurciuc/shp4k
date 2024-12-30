package train.shp4k.service;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import train.shp4k.domain.entity.User;
import train.shp4k.service.interfaces.ConfirmationService;
import train.shp4k.service.interfaces.EmailService;

/**
 * 30/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
@Service
public class EmailServiceImpl implements EmailService {


  //fields
  private final JavaMailSender sender;
  private final freemarker.template.Configuration mailConfig;
  private final ConfirmationService confirmationService;

  //constructor

  public EmailServiceImpl(JavaMailSender sender, Configuration mailConfig,
      ConfirmationService confirmationService) {
    this.sender = sender;
    this.mailConfig = mailConfig;
    this.confirmationService = confirmationService;

    mailConfig.setDefaultEncoding("UTF-8");
    mailConfig.setTemplateLoader(new ClassTemplateLoader(EmailServiceImpl.class, "/mail"));
  }

  @Override
  public void sendConfirmationEmail(User user) {

    MimeMessage message = sender.createMimeMessage(); // create object MimiMessage
    MimeMessageHelper helper= new MimeMessageHelper(message, "UTF-8");
    String emailText = generateEmailText(user);
    try {
      helper.setFrom("boris.iurciuc@gmail.com"); // ящик
      helper.setTo(user.getEmail()); // адресат
      helper.setSubject("Registration"); // тема
      helper.setText(emailText, true); // содержание и формат
      sender.send(message);
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  private String generateEmailText(User user) {
    Template template = null;
    try {
      template = mailConfig.getTemplate("confirm_reg_mail.ftlh");
      String code = confirmationService.generateConfirmationCode(user);
      String url = "http://localhost:8080/register?code=" + code;
      // Для добавления данных в шаблон создаём мап:
      // name -> Vasya
      // link -> localhost:8080/register?code=87fdsf6sf-fsffsd-f87sdf
      Map<String, Object> templateMap = new HashMap<>();
      templateMap.put("name", user.getUsername());
      templateMap.put("link", url);
      return FreeMarkerTemplateUtils.processTemplateIntoString(template, templateMap);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
