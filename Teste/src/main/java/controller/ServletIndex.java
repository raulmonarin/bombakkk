package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletIndex extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!request.getSession().isNew() && request.getSession().getAttribute("usuario") != null) {
            response.sendRedirect("agenda");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
