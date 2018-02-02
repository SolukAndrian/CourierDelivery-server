package ua.lviv.courierdelivery.service;

import ua.lviv.courierdelivery.model.entity.User;

import java.util.Map;

/**
 * Created by Apple on 11.01.2018.
 */
public interface MailService {
    void sendConfirmationMail(User user);

    void sendActivationMail(String email);

    void sendPasswordNotificationMail(User user);

    void sendPasswordRestoreMail(String accountEmail);

    String getFreeMarkerTemplateContent(Map<String, Object> model);

    String getActivationAccountTemplateContent(Map<String, Object> model);

    String getChangePasswordTemplateContent(Map<String, Object> model);

    String getRestorePasswordTemplateContent(Map<String, Object> model);
}
