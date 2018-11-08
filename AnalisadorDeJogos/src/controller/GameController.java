package controller;

import java.io.IOException;
import controller.Controller;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.GameDAO;
import dao.GameHasCatDAO;
import dao.PubHasCatDAO;
import dao.PublisherDAO;
import model.Category;
import model.DevHasCat;
import model.Developer;
import model.Game;
import model.GameHasCat;
import model.PubHasCat;
import model.Publisher;
import dao.CategoryDAO;
import dao.DAO;
import dao.DAOFactory;
import dao.DevHasCatDAO;
import dao.DeveloperDAO;
/**
 * Servlet implementation class GameController
 */
@WebServlet(name = "/GameController", urlPatterns = {
		"/game",
		"/game/create",
		"/game/create/first",
		"/game/update",
		"/game/read",
		"/game/delete",
		"/game/all",
		"/game/json"
} )

public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		GameDAO dao;
		Game game;
		RequestDispatcher dispatcher;
		String name;
		Integer site;
		switch (request.getServletPath()) {
			case "/game/create/first":
				
				
				CategoryDAO daoCat;
				try(DAOFactory daoFactory = new DAOFactory()){
					daoCat = daoFactory.getCategoryDAO();
					
					List<Category> catList = daoCat.all();
					request.setAttribute("catList", catList);
				} catch (ClassNotFoundException e1) {
					request.getSession().setAttribute("error", e1.getMessage());
				} catch (SQLException e1) {
					request.getSession().setAttribute("error", e1.getMessage());
				} catch (Exception e1) {
					request.getSession().setAttribute("error", e1.getMessage());
				}
				dispatcher = request.getRequestDispatcher("/view/game/create.jsp");
				dispatcher.forward(request, response);
			break;
			case "/game":
				System.out.println(">>\n\n\n>>>>>> Listagemmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
				try(DAOFactory daoFactory = new DAOFactory()){
					dao = daoFactory.getGameDAO();
					
					List<Game> gameList = dao.all();
					request.setAttribute("gameList", gameList);
				} catch (ClassNotFoundException e1) {
					request.getSession().setAttribute("error", e1.getMessage());
				} catch (SQLException e1) {
					request.getSession().setAttribute("error", e1.getMessage());
				} catch (Exception e1) {
					request.getSession().setAttribute("error", e1.getMessage());
				}
				dispatcher = request.getRequestDispatcher("/view/game/gameList.jsp");
				dispatcher.forward(request, response);
				break;
			case "/game/read":
				
				try(DAOFactory daoFactory = new DAOFactory()){
					dao = daoFactory.getGameDAO();
					name = request.getParameter("name");
					site = Integer.parseInt(request.getParameter("site"));
					game = dao.read(name,site);
					Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
					String json = gson.toJson(game);
					response.getOutputStream().print(json);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.getSession().setAttribute("error", e.getMessage());
					response.sendRedirect(request.getContextPath() + "/game");
				} 
				break;
			case "/game/json":{
				
				dispatcher = request.getRequestDispatcher("/view/json/ErroJson.jsp");
				dispatcher.forward(request, response);
				break;
			}
			case "/game/delete":
				
				name = request.getParameter("name");
				site = Integer.parseInt(request.getParameter("site"));
				
				try(DAOFactory daoFactory = new DAOFactory()){
					dao = daoFactory.getGameDAO();
					dao.delete(name,site);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				response.sendRedirect(request.getContextPath() + "/game");
				break;
			case "/game/update":
				
				try(DAOFactory daoFactory = new DAOFactory()){
				
					
					dao = daoFactory.getGameDAO();
					site = Integer.parseInt(request.getParameter("site"));
					name = request.getParameter("name");
					game = dao.read(name, site);
					/*if(game == null)
						System.out.println("JOGO É NULO");
					
					System.out.println("nome jogo: "+ game.getName());
					System.out.println("site jogo: "+ game.getSite());
					System.out.println("dev jogo: "+ game.getDeveloper());*/
					
					
					request.setAttribute("game", game);
					dispatcher = request.getRequestDispatcher("/view/game/update.jsp");
					dispatcher.forward(request, response);
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GameDAO dao = null;
		Game game = new Game();
		//if(!Controller.sessionVerify(request.getSession())) {
		//	response.sendRedirect("http://localhost:8080/Analise-games-projeto/index.jsp");
		//}
		
		String releaseDate;
		
		switch(request.getServletPath()) {
			case "/game/update":
//				System.out.println("CHEGOU AQUIIIIIIIIIIIIIIIIIIII");
				
//				Novo update				
				
				game.setDeveloper(request.getParameter("developer"));
				game.setPublisher(request.getParameter("publisher"));
				game.setPrice(Double.parseDouble(request.getParameter("price")));
				game.setName(request.getParameter("name"));
				game.setSite(Integer.parseInt(request.getParameter("site")));
				releaseDate = request.getParameter("release_date");
				
				SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
				try {
					java.util.Date dateUtil = format2.parse(releaseDate);
					java.sql.Date date = new java.sql.Date(dateUtil.getTime());
					game.setDate_release(date);
					
					//response.sendRedirect(request.getContextPath()+"/view/menuInicial.jsp");
				} catch (ParseException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
				
				try(DAOFactory daoFactory = new DAOFactory()){
					dao = daoFactory.getGameDAO();
					
					dao.update(game);
					RequestDispatcher dispatcher;
					dispatcher = request.getRequestDispatcher("/view/menuInicial.jsp");
					dispatcher.forward(request, response);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}
				break;
			case "/game/create":{
				
				CategoryDAO catDao;
				PublisherDAO pubDao;
				DeveloperDAO devDao;
				DevHasCatDAO devHasCatDAO;
				GameHasCatDAO gameHasCatDAO;
				PubHasCatDAO pubHasCatDAO;
				
				Developer dev;
				Publisher pub;
				DevHasCat devHasCat;
				GameHasCat gameHasCat;
				PubHasCat pubHasCat;
				
				String[] categories;
//				Pegando informacoes do jogo a ser inserido
				game.setName(request.getParameter("name"));
				game.setPrice(Double.parseDouble(request.getParameter("price")));
				game.setDeveloper(request.getParameter("developer"));
				game.setPublisher(request.getParameter("publisher"));
				game.setSite(Integer.parseInt(request.getParameter("site")));
				
				releaseDate = request.getParameter("release_date");
				
				//preparando outras classes
				dev = new Developer();
				dev.setName(game.getDeveloper());
				pub = new Publisher();
				pub.setName(game.getPublisher());
				
				categories = request.getParameterValues("categorias");
				
				List<Category> categoryList = new ArrayList<Category>();
				List<DevHasCat> devHasCatList = new ArrayList<DevHasCat>();
				List<GameHasCat> gameHasCatList = new ArrayList<GameHasCat>();
				List<PubHasCat> pubHasCatList = new ArrayList<PubHasCat>();
				
				if(categories != null) {
					for(int i = 0; i < categories.length; i++) {
						//System.out.println("\n>>Categorias: "+ categories[i]);
						Category cat = new Category();
						cat.setName(categories[i]);
						categoryList.add(cat);
						
						devHasCat = new DevHasCat();
						devHasCat.setCatName(cat.getName());
						devHasCat.setDevName(game.getDeveloper());
						devHasCatList.add(devHasCat);
						
						gameHasCat = new GameHasCat();
						gameHasCat.setCatName(cat.getName());
						gameHasCat.setGameName(game.getName());
						gameHasCat.setGameSite(game.getSite());
						gameHasCatList.add(gameHasCat);
						
						pubHasCat = new PubHasCat();
						pubHasCat.setCatName(cat.getName());
						pubHasCat.setPubName(game.getPublisher());
						pubHasCatList.add(pubHasCat);
					}
				}
				
				
//				System.out.println("Release_Date: " + releaseDate);
//				Formatando data de lancamento do jogo
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					java.util.Date dateUtil = format.parse(releaseDate);
					java.sql.Date date = new java.sql.Date(dateUtil.getTime());
					game.setDate_release(date);
					
					//response.sendRedirect(request.getContextPath()+"/view/menuInicial.jsp");
				} catch (ParseException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
				
				try(DAOFactory daoFactory = new DAOFactory()) {
					catDao = daoFactory.getCategoryDAO();
					pubDao = daoFactory.getPublisherDAO();
					devDao = daoFactory.getDeveloperDAO();
					devHasCatDAO = daoFactory.getDevHasCatDAO();
					gameHasCatDAO = daoFactory.getGameHasCatDAO();
					pubHasCatDAO = daoFactory.getPubHasCatDAO();
					
					if(categories != null) {
						for(Category cat : categoryList) {
							catDao.create(cat);
						}
					}
					
					devDao.create(dev);
					pubDao.create(pub);
					
					for(DevHasCat devHasCat2 : devHasCatList) {
						devHasCatDAO.create(devHasCat2);
					}
					
					for(PubHasCat pubHasCat2 : pubHasCatList) {
						pubHasCatDAO.create(pubHasCat2);
					}
					
					dao = daoFactory.getGameDAO();
					dao.create(game);
					for(GameHasCat gameHasCat2 : gameHasCatList) {
						gameHasCatDAO.create(gameHasCat2);
					}
					
					response.sendRedirect(request.getContextPath()+"/view/menuInicial.jsp");
				} catch (Exception ex) {
					System.err.println(ex.getMessage());

					ex.printStackTrace();
				}
				
				
				break;
			}
		}
		doGet(request, response);
	}
	
	
	
}
