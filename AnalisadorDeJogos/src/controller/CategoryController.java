package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import dao.CategoryDAO;
import dao.DAOFactory;
import model.Category;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet(name = "/CategoryController", urlPatterns = {
		"/category/create",
		"/category/delete",
		"/category/all" 
})

public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CategoryDAO dao = null;
		Category cat = new Category();
		
		
		if(!Controller.sessionVerify(request.getSession())) {
			//response.sendRedirect("http://localhost:8080/AnaliseGames/view/menuInicial.jsp");
			
			System.out.println("Usuario nao logado");
		}
		
		
		
		switch(request.getServletPath()) {
			case "/category/create":{
				cat.setName(request.getParameter("name"));
				
				try(DAOFactory daoFactory = new DAOFactory()){
					dao = daoFactory.getCategoryDAO();
					dao.create(cat);
					response.sendRedirect(request.getContextPath()+"/view/menuInicial.jsp");
				} catch (ClassNotFoundException ex) {
					System.err.println(ex.getMessage());
//					session.setAttribute("error", ex.getMessage());
					ex.printStackTrace();
				} catch (SQLException ex) {
					System.err.println(ex.getMessage());
//					session.setAttribute("error", ex.getMessage());
					ex.printStackTrace();
				} catch (Exception ex) {
					System.err.println(ex.getMessage());
//					session.setAttribute("error", ex.getMessage());
					ex.printStackTrace();
				}
				break;
			}
				
		}
		
		
		
		
		
			
		
			
		
			
		
			
		
			
	
		doGet(request, response);
	}

}
