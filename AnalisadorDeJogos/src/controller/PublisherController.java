package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.PublisherDAO;
import model.Publisher;

/**
 * Servlet implementation class PublisherController
 */
@WebServlet(name = "/PublisherController", urlPatterns = {
		"/publisher",
		"/publisher/create",
		"/publisher/update",
		"/publisher/read",
		"/publisher/delete",
		"/upload/json"
})
public class PublisherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublisherController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublisherDAO dao;
		Publisher pub;
		RequestDispatcher dispatcher;
		
		switch(request.getServletPath()) {
			case "/publisher":
				try(DAOFactory daoFactory = new DAOFactory()){
					dao = daoFactory.getPublisherDAO();
					List<Publisher> publisherList = dao.all();
					request.setAttribute("publisherList", publisherList);
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				dispatcher = request.getRequestDispatcher("/view/publisher/list.jsp");
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
		
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("/view/json/ErroJson.jsp");
		dispatcher.forward(request, response);
		doGet(request, response);
	}

}
