package by.victor.beta.controller;


import by.victor.beta.command.AbstractCommand;
import by.victor.beta.command.CommandProvider;
import by.victor.beta.entity.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet("/greenhouseHome")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

public class Servlet extends HttpServlet{
    CommandProvider commandProvider=new CommandProvider();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String requestCommand = request.getParameter("command");

        Optional<AbstractCommand> optionalCommand=commandProvider.findCommand(Role.DEFAULT,requestCommand);
        AbstractCommand command=optionalCommand.orElseThrow(IllegalArgumentException::new);

    }






}





