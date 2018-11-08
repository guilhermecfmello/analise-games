package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Category; 
import model.DevHasCat;
import model.Developer;
import model.Game;
import model.GameHasCat;
import model.PubHasCat;
import model.Publisher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.CategoryDAO;
import dao.DAOFactory;
import dao.DevHasCatDAO;
import dao.DeveloperDAO;
import dao.GameHasCatDAO;
import dao.PubHasCatDAO;
import dao.PublisherDAO;
import dao.GameDAO;

/**
 * Servlet implementation class ImportController
 */
@MultipartConfig()
@WebServlet(name = "/ImportController", urlPatterns = {
		"/import",
		"/import/json"
})
public class ImportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportController() {
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
		Gson gson = null;
		Game[] game = null;
		
		
		
		switch (request.getServletPath()) {
        case "/import/json": {
            
            // Upload do avatar
//            String fileName = uploadFile(request);
        	System.out.println("@@@@@@@@@@@@@ Recebendo arquivo Json @@@@@@@@@@@@@");
			Part filePart = request.getPart("file");
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			InputStream fileContent = filePart.getInputStream();
			Reader reader = new InputStreamReader(fileContent, "UTF-8");
			Integer site = Integer.parseInt(request.getParameter("site"));
			switch(site) {
//			Steam
			case 1:
				gson = new GsonBuilder().setDateFormat("dd MMM, yyyy").create();
				game = gson.fromJson(reader, Game[].class);
				
				break;
//			Nuuvem
			case 2:
				gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				game = gson.fromJson(reader, Game[].class);
				
				break;
//			Gamersgate	
			case 3:
				gson = new GsonBuilder().setDateFormat("dd MMM yyyy").create();
				game = gson.fromJson(reader, Game[].class);
//				imprimirJogo(game);
				break;
			}
//			imprimirJogo(game);
			
			
			CategoryDAO catDao = null;
			PublisherDAO pubDao = null;
			DeveloperDAO devDao = null;
			DevHasCatDAO devHasCatDAO = null;
			GameHasCatDAO gameHasCatDAO = null;
			PubHasCatDAO pubHasCatDAO = null;
			GameDAO gameDao = null;
			DAOFactory daoFactory = null;
			
			try {
				daoFactory = new DAOFactory();
			} catch (ClassNotFoundException | SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			try{
				
				catDao = daoFactory.getCategoryDAO();
				pubDao = daoFactory.getPublisherDAO();
				devDao = daoFactory.getDeveloperDAO();
				devHasCatDAO = daoFactory.getDevHasCatDAO();
				gameHasCatDAO = daoFactory.getGameHasCatDAO();
				pubHasCatDAO = daoFactory.getPubHasCatDAO();
				gameDao = daoFactory.getGameDAO();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			for(int i = 0; i < game.length ; i++) {
				
				
				Developer dev;
				Publisher pub;
				DevHasCat devHasCat;
				GameHasCat gameHasCat;
				PubHasCat pubHasCat;
				Category cat;
				ArrayList<Category> catList = new ArrayList<Category>();
				ArrayList<DevHasCat> devHasCatList = new ArrayList<DevHasCat>();
				ArrayList<PubHasCat> pubHasCatList = new ArrayList<PubHasCat>();
				ArrayList<GameHasCat> gameHasCatList = new ArrayList<GameHasCat>();
				
				game[i].setSite(site);
				
				dev = new Developer();
				pub = new Publisher();
				
				dev.setName(game[i].getDeveloper());
				pub.setName(game[i].getPublisher());
				
				for(int k = 0; k < game[i].getCategory().size(); k++){
					cat = new Category();
					devHasCat = new DevHasCat();
					pubHasCat = new PubHasCat();
					gameHasCat = new GameHasCat();
					
					cat.setName(game[i].getCategory().get(k));
					devHasCat.setCatName(cat.getName());
					devHasCat.setDevName(game[i].getDeveloper());
					pubHasCat.setCatName(cat.getName());
					pubHasCat.setPubName(game[i].getPublisher());
					gameHasCat.setCatName(cat.getName());
					gameHasCat.setGameName(game[i].getName());
					gameHasCat.setGameSite(game[i].getSite());
					
					devHasCatList.add(devHasCat);
					pubHasCatList.add(pubHasCat);
					gameHasCatList.add(gameHasCat);
					catList.add(cat);
				}
				
				
//				Iniciando uso do DAO
				
					
					
					try {
						devDao.create(dev);
						pubDao.create(pub);
						
						for(Category cat2 : catList) {
							catDao.create(cat2);
						}
						
						for(DevHasCat devHasCat2 : devHasCatList) {
							devHasCatDAO.create(devHasCat2);
						}
						
						for(PubHasCat pubHasCat2 : pubHasCatList) {
							pubHasCatDAO.create(pubHasCat2);
						}
						
						gameDao.create(game[i]);
						
						for(GameHasCat gameHasCat2 : gameHasCatList) {
							gameHasCatDAO.create(gameHasCat2);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				} 
				
				
				
			}
			response.sendRedirect(request.getContextPath()+"/view/json/Success.jsp");
			
            break;
        }
		doGet(request, response);
    }
		


	void imprimirJogo(Game[] game) {
		for(int i = 0; i < 10; i ++ ) {
			System.out.println("==================== Imprimindo Jogo ====================");
			System.out.println(">>Nome do jogo: " + game[i].getName());
			System.out.println(">>Desenvolvedora: " + game[i].getDeveloper());
			System.out.println(">>Publicadora: " + game[i].getPublisher());
			System.out.print(">>Categorias: ");
			for(int j = 0; j < game[i].getCategory().size(); j++)
				System.out.println(game[i].getCategory().get(j)+ " ");
			System.out.println(">>Preço: " + game[i].getPrice());
			System.out.println(">>Data de lançamento: " + game[i].getDate_release());
		}
	}
}

