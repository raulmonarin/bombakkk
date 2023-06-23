package controller;

import java.io.IOException;

import dao.ContatoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Contato;
import model.ContatoExisteException;
import model.Usuario;

public class ServletNovo extends HttpServlet {

    private void erro(HttpServletRequest request, String erro) {
        request.setAttribute("erro", "Erro: " + erro);
        request.setAttribute("nome", Util.decodificar(request.getParameter("nome")));
        request.setAttribute("email", Util.decodificar(request.getParameter("email")));
        request.setAttribute("telefone", Util.decodificar(request.getParameter("telefone")));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("novo.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (!request.getSession().isNew() && usuario != null) {
            try {
                Contato contato = new Contato(Util.decodificar(request.getParameter("nome")), Util.decodificar(request.getParameter("email")), Util.decodificar(request.getParameter("telefone")));
                new ContatoDAOImpl().cadastrar(usuario.getId(), contato);
                request.setAttribute("sucesso", "Novo contato cadastrado com sucesso.");
            } catch (IndexOutOfBoundsException | IllegalArgumentException | ContatoExisteException e) {
                erro(request, e.getMessage());
            }
        }

        request.getRequestDispatcher("novo.jsp").forward(request, response);
    }
}
