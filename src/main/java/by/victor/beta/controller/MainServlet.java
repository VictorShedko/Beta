package by.victor.beta.controller;


import by.victor.beta.logic.command.*;
import by.victor.beta.logic.entity.Role;
import by.victor.beta.logic.service.ServiceFacade;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


@WebServlet("/cleaning")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MainServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        CommandProvider commandProvider = new CommandProvider();
        SessionAction sessionAction = new SessionAction();
        String requestCommand = request.getParameter(AttributeNameProvider.COMMAND);
        HttpSession session = request.getSession();
        Role requesterRole = sessionAction.getRole(session);
        Optional<AbstractCommand> optionalCommand = commandProvider.findCommand(requesterRole, requestCommand);
        AbstractCommand command = optionalCommand.orElseThrow(IllegalArgumentException::new);
        Transmitter transmitter = new Transmitter();
        RequestSessionContent content = transmitter.buildContent(request, session);
        Router router = command.execute(content);
        transmitter.addContent(request,session,content);
        RedirectionAction redirectionAction = new RedirectionAction();
        redirectionAction.redirect(request, response, router);
    }

    @Override
    public void destroy() {
        super.destroy();
        ServiceFacade.instance.destroy();
    }


}





