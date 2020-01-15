package by.victor.beta.controller;


import by.victor.beta.logic.command.AbstractCommand;
import by.victor.beta.logic.command.CommandProvider;
import by.victor.beta.logic.command.Router;
import by.victor.beta.logic.command.requsttransmiter.TransmitStrategy;
import by.victor.beta.logic.command.requsttransmiter.Transmiter;
import by.victor.beta.logic.entity.Role;

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

public class Servlet extends HttpServlet{
   private CommandProvider commandProvider=new CommandProvider();
   private SessionAction sessionAction= new SessionAction();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
     processRequest(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
    processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String requestCommand = request.getParameter("command");
        Role requesterRole=sessionAction.getRole(request.getSession());
        Optional<AbstractCommand> optionalCommand=commandProvider.findCommand(requesterRole,requestCommand);
        AbstractCommand command=optionalCommand.orElseThrow(IllegalArgumentException::new);
        Transmiter transmiter=new Transmiter();

        Router router=command.execute(transmiter.buildData(request,command));
        RedirectAction redirectAction=new RedirectAction();
        try {
            redirectAction.redirect(request,response,router);
        } catch (ServletException e) {
            //todo add processing
        } catch (IOException e) {

        }
    }
    @Override
    public void destroy(){
                super.destroy();
    }





}





