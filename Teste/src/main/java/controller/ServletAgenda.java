package controller;

import java.io.IOException;
import java.util.ArrayList;

import dao.ContatoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Contato;
import model.Usuario;

public class ServletAgenda extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (!request.getSession().isNew() && usuario != null) {
            ArrayList<Contato> contatos = new ContatoDAOImpl().carregar(usuario);
            if (!contatos.isEmpty()) {
                request.setAttribute("contatos", contatos);
            } else {
                request.setAttribute("agendavazia", "A agenda ainda não possui contatos.");
            }
        }

        request.getRequestDispatcher("agenda.jsp").forward(request, response);
    }
}
