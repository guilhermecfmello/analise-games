package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.DeveloperDAO;
import model.Developer;

/**
 * Servlet implementation class DeveloperController
 */
@WebServlet(name = "/DeveloperController", urlPatterns = {
		"/developer",
		"/developer/list",
		"/developer/create",
		"/developer/update",
		"/developer/delete"
})
public class DeveloperController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeveloperController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DeveloperDAO dao;
//		Developer developer;
		RequestDispatcher dispatcher;
		switch(request.getServletPath()) {
			case "/developer":
				try(DAOFactory daoFactory = new DAOFactory()){
					dao = daoFactory.getDeveloperDAO();
					
					List<Developer> developerList = dao.all();
					request.setAttribute("developerList", developerList);
				} catch (Exception e) {
					request.getSession().setAttribute("error", e.getMessage());
					
				}
				dispatcher = request.getRequestDispatcher("/view/developer/list.jsp");
				dispatcher.forward(request, response);
				break;
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
