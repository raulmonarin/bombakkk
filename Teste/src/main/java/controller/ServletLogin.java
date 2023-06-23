package controller;

import java.io.IOException;

import dao.UsuarioDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Usuario;

@WebServlet(urlPatterns= {"/login", "/"})
public class ServletLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;

        Usuario usuario = new UsuarioDAOImpl().buscar(new Usuario(request.getParameter("nome"), request.getParameter("senha")));

        if (usuario.getId() != -1) {
            HttpSession objSessao = request.getSession();
            if (objSessao == null) {
                objSessao = request.getSession(true);
            }
            objSessao.setAttribute("usuario", usuario);

            rd = request.getRequestDispatcher("agenda");
        } else {
            request.setAttribute("nome", Util.decodificar(usuario.getNome()));
            request.setAttribute("erro", "Erro: Usuário e/ou senha inválido(s)");
            rd = request.getRequestDispatcher("index.jsp");
        }

        rd.forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		dispatcher.forward(request, response);
    }

}
