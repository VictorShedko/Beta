package by.victor.beta.controller;

import by.victor.beta.logic.command.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

 class RedirectionAction {
     void redirect(HttpServletRequest request, HttpServletResponse response, Router router) throws ServletException, IOException {
        switch (router.getRedirectType()) {
            case FORWARD:
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(router.getPagePath());
                break;
            default:
                throw new IllegalArgumentException();
        }

    }

}
