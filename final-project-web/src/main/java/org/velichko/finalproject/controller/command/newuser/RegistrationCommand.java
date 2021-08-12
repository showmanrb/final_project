package org.velichko.finalproject.controller.command.newuser;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.command.PageName;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserRole;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.util.RegistrationConfirmatory;
import org.velichko.finalproject.validator.BaseDataValidator;

import java.util.HashMap;
import java.util.Map;

import static org.velichko.finalproject.controller.command.MessageNameKey.REGISTRATION_FAILED_KEY;
import static org.velichko.finalproject.controller.command.MessageNameKey.REGISTRATION_SUCCESSFUL_KEY;
import static org.velichko.finalproject.controller.command.PageName.LOGIN_PAGE;
import static org.velichko.finalproject.controller.command.PageName.REGISTRATION_PAGE;
import static org.velichko.finalproject.controller.command.ParamName.*;


public class RegistrationCommand implements Command {
    private final Logger logger = LogManager.getLogger();
    private final UserService userService;
    private final RegistrationConfirmatory confirmatoryService;
    private final BaseDataValidator registrationDataValidator;
    private final I18nManager i18n;


    public RegistrationCommand(UserService userService,
                               RegistrationConfirmatory confirmatoryService,
                               BaseDataValidator registrationDataValidator,
                               I18nManager i18n) {
        this.userService = userService;
        this.confirmatoryService = confirmatoryService;
        this.registrationDataValidator = registrationDataValidator;
        this.i18n = i18n;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String locale = (String) request.getSession().getAttribute(LOCALE_PARAM);
        String firstName = request.getParameter(FIRST_NAME_PARAM);
        String lastName = request.getParameter(LAST_NAME_PARAM);
        String email = request.getParameter(EMAIL_PARAM);
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD_PARAM);

        Map<String, String> registrationData = new HashMap<>();
        registrationData.put(FIRST_NAME_PARAM, firstName);
        registrationData.put(LAST_NAME_PARAM, lastName);
        registrationData.put(EMAIL_PARAM, email);
        registrationData.put(LOGIN_PARAM, login);
        registrationData.put(PASSWORD_PARAM, password);
        registrationData.put(CONFIRM_PASSWORD_PARAM, confirmPassword);

        String method = request.getMethod();
        if (method.equals(POST_PARAM)) {
            Map<String, String> registrationDataCheckResult = registrationDataValidator.checkValues(registrationData, locale);
            if (!registrationDataCheckResult.isEmpty()) {
                request.setAttribute(CORRECT_REGISTRATION_DATA_PARAM, registrationData);
                request.setAttribute(ERROR_REGISTRATION_DATA_PARAM, registrationDataCheckResult);
                router.setPagePath(PageName.REGISTRATION_PAGE);
            } else {
                User user = new User(firstName, lastName, login, email, UserRole.STUDENT, UserStatus.WAIT_CONFIRMATION);
                System.out.println(user);
                try {
                    String registrationKey = confirmatoryService.setRegistrationToken(email, login);
                    if (userService.createNewUser(user, password, registrationKey)) {
                        request.setAttribute(USER_PARAM, user);
                        request.setAttribute(ParamName.REGISTRATION_IS_DONE, i18n.getMassage(REGISTRATION_SUCCESSFUL_KEY, locale));
                    }
                } catch (ServiceException e) {
                    e.printStackTrace(); //todo
                    request.setAttribute(ParamName.REGISTRATION_FAILED, i18n.getMassage(REGISTRATION_FAILED_KEY, locale) + e.getLocalizedMessage());
                }
                router.setPagePath(LOGIN_PAGE);
            }
        } else {
            router.setPagePath(REGISTRATION_PAGE);
        }
        return router;
    }
}
//todo редиспатчер на страничку приветствия нового пользователя и там возможно определение его роли


