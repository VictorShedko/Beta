package by.victor.beta.controller;


import by.victor.beta.command.*;
import by.victor.beta.entity.Role;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            logger.log(Level.ERROR,"do get command ex",e);
            request.getRequestDispatcher(PagePath.ERROR).forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            logger.log(Level.ERROR,"do post command ex",e);
            request.getRequestDispatcher(PagePath.ERROR).forward(request, response);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                                            throws ServletException, IOException, CommandException {
        CommandProvider commandProvider = new CommandProvider();
        String encoding=request.getCharacterEncoding();
        RequestSessionContent content = new RequestSessionContent(request,getServletContext());

        String requestCommand = (String)content.getRequestParameter(AttributeName.COMMAND);
        Role requesterRole = (Role) content.getSessionAttribute(AttributeName.ROLE);
        Optional<Command> optionalCommand = commandProvider.findCommand(requesterRole, requestCommand);
        logger.log(Level.TRACE,"role:"+requesterRole+" command:"+optionalCommand);
        Command command = optionalCommand.orElseThrow(CommandException::new);
        Router router = command.execute(content);
        content.addContent(request);
        switch (router.getRedirectType()) {
            case FORWARD:
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
                logger.log(Level.TRACE,"forward "+router.getPagePath());
                break;
            case REDIRECT:
                response.sendRedirect(router.getPagePath());
                logger.log(Level.TRACE,"redirect "+router.getPagePath());
        }

    }

    @Override
    public void destroy() {
        super.destroy();

    }
}





