//package ua.lviv.courierdelivery.service.impl;
//
//import com.softserve.academy.spaced.repetition.security.JwtTokenForMail;
//import freemarker.template.Configuration;
//import freemarker.template.TemplateException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.mail.javamail.MimeMessagePreparator;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//import ua.lviv.courierdelivery.model.entity.User;
//import ua.lviv.courierdelivery.service.MailService;
//
//import java.io.IOException;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Apple on 11.01.2018.
// */
//@Service
//public class MailServiceImpl implements MailService {
//    @Value("${app.origin.url}")
//    private String url;
//    @Autowired
//    private JavaMailSender mailSender;
//    @Autowired
//    private JwtTokenForMail jwtTokenForMail;
//    @Autowired
//    @Qualifier("freemarkerConf")
//    private Configuration freemarkerConfiguration;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
//
//    @Override
//    public void sendConfirmationMail(User user) {
//        MimeMessagePreparator preparator = mimeMessage -> {
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//            helper.setSubject("Confirmation registration");
//            helper.setTo(user.getAccount().getEmail());
//            Map<String, Object> model = new HashMap<>();
//            String token = jwtTokenForMail.generateTokenForMail(user.getAccount().getEmail());
//            model.put("person", user.getPerson());
//            model.put("token", token);
//            model.put("url", url);
//            String text = getFreeMarkerTemplateContent(model);
//            helper.setText(text, true);
//        };
//        mailSender.send(preparator);
//    }
//
//    @Override
//    public void sendActivationMail(String email) {
//        MimeMessagePreparator preparator = mimeMessage -> {
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//            helper.setSubject("Activation account");
//            helper.setTo(email);
//            Map<String, Object> model = new HashMap<>();
//            String token = jwtTokenForMail.generateTokenForMail(email);
//            model.put("token", token);
//            model.put("url", url);
//            String text = getActivationAccountTemplateContent(model);
//            helper.setText(text, true);
//        };
//        mailSender.send(preparator);
//    }
//
//    @Override
//    public void sendPasswordNotificationMail(User user) {
//        MimeMessagePreparator preparator = mimeMessage -> {
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//            helper.setSubject("Change password notification");
//            helper.setTo(user.getAccount().getEmail());
//            Map<String, Object> model = new HashMap<>();
//            model.put("person", user.getPerson());
//            model.put("datachange", Calendar.getInstance().getTime().toString());
//            model.put("url", url);
//            String text = getChangePasswordTemplateContent(model);
//            helper.setText(text, true);
//        };
//        mailSender.send(preparator);
//    }
//
//    @Override
//    public void sendPasswordRestoreMail(String accountEmail) {
//        LOGGER.debug("Send mail for reset password to email: {}", accountEmail);
//        MimeMessagePreparator preparator = mimeMessage -> {
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//            helper.setSubject("Password restore");
//            helper.setTo(accountEmail);
//            Map<String, Object> model = new HashMap<>();
//            String token = jwtTokenForMail.generateTokenForMail(accountEmail);
//            model.put("token", token);
//            model.put("url", url);
//            String text = getRestorePasswordTemplateContent(model);
//            helper.setText(text, true);
//        };
//        mailSender.send(preparator);
//    }
//
//    @Override
//    public String getFreeMarkerTemplateContent(Map<String, Object> model) {
//        StringBuilder content = new StringBuilder();
//        try {
//            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
//                    freemarkerConfiguration.getTemplate("registrationVerificationMailTemplate.html"), model));
//            return content.toString();
//        } catch (IOException | TemplateException e) {
//            LOGGER.error("Couldn't generate email content.", e);
//        }
//        return "";
//    }
//
//    @Override
//    public String getActivationAccountTemplateContent(Map<String, Object> model) {
//        StringBuilder content = new StringBuilder();
//        try {
//            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
//                    freemarkerConfiguration.getTemplate("activationAccountMailTemplate.html"), model));
//            return content.toString();
//        } catch (IOException | TemplateException e) {
//            LOGGER.error("Couldn't generate email content.", e);
//        }
//        return "";
//    }
//
//    @Override
//    public String getChangePasswordTemplateContent(Map<String, Object> model) {
//        StringBuilder content = new StringBuilder();
//        try {
//            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
//                    freemarkerConfiguration.getTemplate("changePasswordMailTemplate.html"), model));
//            return content.toString();
//        } catch (IOException | TemplateException e) {
//            LOGGER.error("Couldn't generate email content.", e);
//        }
//        return "";
//    }
//
//    @Override
//    public String getRestorePasswordTemplateContent(Map<String, Object> model) {
//        StringBuilder content = new StringBuilder();
//        try {
//            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
//                    freemarkerConfiguration.getTemplate("restorePasswordMailTemplate.html"), model));
//            return content.toString();
//        } catch (IOException | TemplateException e) {
//            LOGGER.error("Couldn't generate email content.", e);
//        }
//        return "";
//    }
//}
