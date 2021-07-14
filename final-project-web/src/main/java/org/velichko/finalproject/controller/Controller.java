package org.velichko.finalproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.velichko.finalproject.command.CommandName;
import org.velichko.finalproject.command.PageName;
import org.velichko.finalproject.command.ParamName;

import java.io.IOException;
import java.util.Optional;

import static org.velichko.finalproject.command.PageName.*;
import static org.velichko.finalproject.command.ParamName.*;
import static org.velichko.finalproject.command.ParamName.REFERER_PARAM;

@WebServlet(name = "controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {

    private CommandName commandName;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<CommandName> commandName = getCommandName(request);
        if (commandName.isPresent()) {
            Router router = commandName.get().getCommand().execute(request);
            request.setAttribute(REFERER_PARAM, commandName.get().name());
            switch (router.getRouterType()) {
                case FORWARD -> request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                case REDIRECT -> response.sendRedirect(router.getPagePath());
            default -> request.getRequestDispatcher(router.getPagePath()).forward(request, response);
            }
        }else {
            response.sendRedirect(ERROR_PAGE);
        }


    }

    private Optional<CommandName> getCommandName(HttpServletRequest request) {
        String name = request.getParameter(COMMAND_PARAM);
        CommandName commandName;
        commandName = CommandName.valueOf(name.toUpperCase());
        return Optional.of(commandName);
    }
}
