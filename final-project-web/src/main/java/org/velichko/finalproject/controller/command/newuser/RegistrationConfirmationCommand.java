package org.velichko.finalproject.controller.command.newuser;

import jakarta.servlet.http.HttpServletRequest;
import org.velichko.finalproject.controller.command.Command;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.logic.entity.User;
import org.velichko.finalproject.logic.entity.type.UserStatus;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;

import java.util.Optional;

import static org.velichko.finalproject.controller.command.PageName.*;

public class RegistrationConfirmationCommand implements Command {
    private final UserService userService;

    public RegistrationConfirmationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String confirmationKey = request.getParameter(ParamName.CONFIRM_KEY);
        try {
            Optional<User> userOptional = userService.getUserByRegistrationKey(confirmationKey);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userService.changeUserStatus(user.getId(), UserStatus.ACTIVE);
                router.setPagePath(LOGIN_PAGE);
            } else {
                router.setPagePath(REGISTRATION_PAGE);
            }
        } catch (ServiceException e) {
            router.setPagePath(ERROR_PAGE);
        }
        return router;
    }
}