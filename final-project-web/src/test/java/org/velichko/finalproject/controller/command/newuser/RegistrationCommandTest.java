package org.velichko.finalproject.controller.command.newuser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.velichko.finalproject.controller.Router;
import org.velichko.finalproject.controller.Router.RouterType;
import org.velichko.finalproject.controller.command.PageName;
import org.velichko.finalproject.controller.command.ParamName;
import org.velichko.finalproject.i18n.I18nManager;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.service.UserService;
import org.velichko.finalproject.logic.util.PasswordHashGenerator;
import org.velichko.finalproject.logic.util.RegistrationConfirmatory;
import org.velichko.finalproject.validator.BaseDataValidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;
import static org.velichko.finalproject.controller.Router.RouterType.*;
import static org.velichko.finalproject.controller.command.PageName.*;
import static org.velichko.finalproject.controller.command.ParamName.*;

class RegistrationCommandTest {
    private RegistrationCommand toTest;

    @Mock
    private UserService userService;
    @Mock
    private RegistrationConfirmatory confirmatoryService;
    @Mock
    private BaseDataValidator registrationDataValidator;
    @Mock
    private I18nManager i18n;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() throws ServiceException {
        openMocks(this);
        when(userService.createNewUser(any(), anyString(), anyString())).thenReturn(true);
        when(confirmatoryService.sendEmailForConfirmRegistration(eq("email@gmail.com"), eq("login"))).thenReturn(PasswordHashGenerator.encodePassword("login"));
        when(registrationDataValidator.checkValues(anyMap(), anyString())).thenReturn(emptyMap());
        when(request.getParameter(FIRST_NAME_PARAM)).thenReturn("Ivan");
        when(request.getParameter(LAST_NAME_PARAM)).thenReturn("Velichko");
        when(request.getParameter(EMAIL_PARAM)).thenReturn("email@gmail.com");
        when(request.getParameter(LOGIN_PARAM)).thenReturn("login");
        when(request.getParameter(PASSWORD_PARAM)).thenReturn("September3211711");
        when(request.getParameter(CONFIRM_PASSWORD_PARAM)).thenReturn("September3211711");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(eq(LOCALE_PARAM))).thenReturn("any");
        when(request.getMethod()).thenReturn(POST_PARAM);
        toTest = new RegistrationCommand(userService, confirmatoryService, registrationDataValidator, i18n);
    }

    @Test
    void executeWhenUserIsCreatedSuccessful() {
        Router router = toTest.execute(request);
        assertEquals(REDIRECT, router.getRouterType());
    }

    @Test
    void expectedLoginPagePathWhenMethodIsGet() {
        Router router = toTest.execute(request);
        assertEquals(REDIRECT_TO_LOGIN_PAGE + "&" + REGISTRATION_IS_DONE, router.getPagePath());
    }

    @Test
    void executeWhenUserIsCreatedFailed() throws ServiceException {
        when(registrationDataValidator.checkValues(anyMap(), anyString())).thenReturn(singletonMap("error", "errors"));
        Router router = toTest.execute(request);
        assertEquals(FORWARD, router.getRouterType());
    }

    @Test
    void expectedRegistrationPagePathWhenMethodIsGet() {
        when(request.getMethod()).thenReturn(GET_PARAM);
        Router router = toTest.execute(request);
        assertEquals(REGISTRATION_PAGE, router.getPagePath());
    }

    @Test
    void expectedForwardRouterTypeWhenMethodIsGet() {
        when(request.getMethod()).thenReturn(GET_PARAM);
        Router router = toTest.execute(request);
        assertEquals(FORWARD, router.getRouterType());
    }


}