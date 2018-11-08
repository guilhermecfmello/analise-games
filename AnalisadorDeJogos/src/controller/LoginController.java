package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDAO;
import model.User;
import dao.DAOFactory;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(name = "/LoginController", urlPatterns = { "/login", "/logout", "" })

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session;
		RequestDispatcher dispatcher;
		switch (request.getServletPath()) {
		case "":
			break;
		case "/logout":
			session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}

			response.sendRedirect(request.getContextPath() + "/");
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
			// TODO Auto-generated method stub
			UserDAO dao;
			User user = new User();
			HttpSession session = request.getSession();
			
			switch (request.getServletPath()) {
			case "/login":
				user.setLogin(request.getParameter("login"));
				user.setPassword(request.getParameter("password"));
				
				try (DAOFactory daoFactory = new DAOFactory();) {
					dao = daoFactory.getUsuarioDAO();

					dao.authenticate(user);
					session.setAttribute("user", user);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
			response.sendRedirect(request.getContextPath() + "/view/menuInicial.jsp");
//			doGet(request, response);
			
	}

}
