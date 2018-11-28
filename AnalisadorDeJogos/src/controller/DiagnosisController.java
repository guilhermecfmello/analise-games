package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DAOFactory;
import dao.DiagnosisDAO;
import model.CMF;
import model.Category;
import model.DMF;
import model.Diagnosis;
import model.PriceYear;

/**
 * Servlet implementation class DiagnosisController
 */
@WebServlet(name = "/DiagnosisController", urlPatterns = {
		"/diagnosis",
		"/diagnosis/developers",
		"/diagnosis/categories",
		"/diagnosis/games",
		"/diagnosis/all"
})
public class DiagnosisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiagnosisController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher;
		DiagnosisDAO diagDao;
		switch(request.getServletPath()) {
		case "/diagnosis/games":
			try(DAOFactory daoFactory = new DAOFactory()){
				double avgSteam, avgNuuvem, avgGG;
				diagDao = daoFactory.getDiagnosisDAO();
				Diagnosis diag = new Diagnosis();
				
				avgSteam = diagDao.get_AVG_Steam();
				avgNuuvem = diagDao.get_AVG_Nuuvem();
				avgGG = diagDao.get_AVG_GamersGate();
				
				ArrayList<Double> list = new ArrayList<Double>();
				list.add(avgSteam);
				list.add(avgNuuvem);
				list.add(avgGG);
				
				ArrayList<Double> priceSteam = new ArrayList<Double>();
				ArrayList<Double> priceNuuvem = new ArrayList<Double>();
				ArrayList<Double> priceGG = new ArrayList<Double>();
				
				ArrayList<PriceYear> listPY;
				PriceYear item, newItem;
				listPY = diagDao.getAVGPriceByYearSteam();
				Integer currentYear, i = 0;
				for(currentYear = 1997; currentYear < 2019; currentYear++){
					item = listPY.get(i);
					if(!item.getYear().equals(currentYear)) {
						priceSteam.add((double) 0);
					}
					else {
						priceSteam.add(item.getPrice());
						i++;
					}
				}
				
				listPY = diagDao.getAVGPriceByYearNuuvem();
				i = 0;
				for(currentYear = 1997; currentYear < 2019; currentYear++){
					item = listPY.get(i);
					if(!item.getYear().equals(currentYear)) {
						priceNuuvem.add((double) 0);
					}
					else {
						priceNuuvem.add(item.getPrice());
						i++;
					}
				}
				
				
				
				listPY = diagDao.getAVGPriceByYearGamersGate();
				
				i = 0;
				for(currentYear = 1997; currentYear < 2019; currentYear++){
					item = listPY.get(i);
					
					if(!item.getYear().equals(currentYear)) {
						priceGG.add((double) 0);
					}
					else {
						priceGG.add(item.getPrice());
						i++;
					}
				}
//				
				
				String avgJson;
				String avgPriceSteam;
				String avgPriceNuuvem;
				String avgPriceGG;
				
				Gson gson = new GsonBuilder().create();
				avgJson = gson.toJson(list);
				avgPriceSteam = gson.toJson(priceSteam);
				avgPriceNuuvem = gson.toJson(priceNuuvem);
				avgPriceGG = gson.toJson(priceGG);
				
				
				request.setAttribute("avg", avgJson);
				request.setAttribute("avgSteam", avgPriceSteam);
				request.setAttribute("avgNuuvem", avgPriceNuuvem);
				request.setAttribute("avgGG", avgPriceGG);
				
				
				
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			dispatcher = request.getRequestDispatcher("/view/diagnosis/chartsGames.jsp");
			dispatcher.forward(request, response);
			break;
		case "/diagnosis/categories":
			
			try(DAOFactory daoFactory = new DAOFactory()){
				diagDao = daoFactory.getDiagnosisDAO();
				Diagnosis diag = new Diagnosis();
				
				diagDao.set_CMFSteam(diag);
				diagDao.set_CMFNuuvem(diag);
				diagDao.set_CMFGamersGate(diag);
				
				ArrayList<String> steamLabel = new ArrayList<String>();
				ArrayList<String> nuuvemLabel = new ArrayList<String>();
				ArrayList<String> ggLabel = new ArrayList<String>();
				
				ArrayList<Integer> steamData = new ArrayList<Integer>();
				ArrayList<Integer> nuuvemData = new ArrayList<Integer>();
				ArrayList<Integer> ggData = new ArrayList<Integer>();
				
				ArrayList<CMF> list = diag.getCMFSteamList();
				CMF item = new CMF();
				
				item = list.get(0);
				steamLabel.add(item.getName_cat());
				steamData.add(item.getCount());
				for(int i = 1; i < list.size(); i++) {
					item = list.get(i);
					steamLabel.add(item.getName_cat());
					steamData.add(item.getCount());
				}
				
				list = diag.getCMFNuuvemList();
				
				item = list.get(0);
				nuuvemLabel.add(item.getName_cat());
				nuuvemData.add(item.getCount());
				for(int i = 1; i < list.size(); i++) {
					item = list.get(i);
					nuuvemLabel.add(item.getName_cat());
					nuuvemData.add(item.getCount());
				}
				
				
				list = diag.getCMFGamersGateList();
				
				item = list.get(0);
				ggLabel.add(item.getName_cat());
				ggData.add(item.getCount());
				for(int i = 1; i < list.size(); i++) {
					item = list.get(i);
					ggLabel.add(item.getName_cat());
					ggData.add(item.getCount());
				}
				
				String steamLabelJson = null;
				String nuuvemLabelJson = null;
				String ggLabelJson = null;
				String steamDataJson = null;
				String nuuvemDataJson = null;
				String ggDataJson = null;
				
				Gson gson = new GsonBuilder().create();
				
				steamLabelJson = gson.toJson(steamLabel);
				steamDataJson = gson.toJson(steamData);
				nuuvemLabelJson = gson.toJson(nuuvemLabel);
				nuuvemDataJson = gson.toJson(nuuvemData);
				ggLabelJson = gson.toJson(ggLabel);
				ggDataJson = gson.toJson(ggData);
				
				
				request.setAttribute("labelSteam", steamLabelJson);
				request.setAttribute("dataSteam", steamDataJson);
				
				request.setAttribute("labelNuuvem", nuuvemLabelJson);
				request.setAttribute("dataNuuvem", nuuvemDataJson);
				
				request.setAttribute("labelGG", ggLabelJson);
				request.setAttribute("dataGG", ggDataJson);			
				
				dispatcher = request.getRequestDispatcher("/view/diagnosis/chartsCategory.jsp");
				dispatcher.forward(request, response);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/diagnosis/developers":
//			System.out.println("@@@@@ CHEGOU NO CONTROLER /diagnosis/developers");
			try(DAOFactory daoFactory = new DAOFactory()){
				diagDao = daoFactory.getDiagnosisDAO();
				Diagnosis diag = new Diagnosis(); 
				
				diagDao.set_DMFSteam(diag);
				diagDao.set_DMFNuuvem(diag);
				diagDao.set_DMFGamersGate(diag);
				
				ArrayList<String> steamLabel = new ArrayList<String>();
				ArrayList<String> nuuvemLabel = new ArrayList<String>();
				ArrayList<String> ggLabel = new ArrayList<String>();
				
				ArrayList<Integer> steamData = new ArrayList<Integer>();
				ArrayList<Integer> nuuvemData = new ArrayList<Integer>();
				ArrayList<Integer> ggData = new ArrayList<Integer>();

				ArrayList<DMF> list = diag.getDMFSteamList();
				DMF item = new DMF();
				
				item = list.get(0);
				steamLabel.add(item.getName_dev());
				steamData.add(item.getCount());
				for(int i = 1; i < list.size(); i++) {
					item = list.get(i);
					steamLabel.add(item.getName_dev());
					steamData.add(item.getCount());
				}
				
				list = diag.getDMFNuuvemList();
				
				item = list.get(0);
				nuuvemLabel.add(item.getName_dev());
				nuuvemData.add(item.getCount());
				for(int i = 1; i < list.size(); i++) {
					item = list.get(i);
					nuuvemLabel.add(item.getName_dev());
					nuuvemData.add(item.getCount());
				}
				
				
				list = diag.getDMFGamersGateList();
				
				item = list.get(1);
				ggLabel.add(item.getName_dev());
				ggData.add(item.getCount());
				for(int i = 2; i < list.size(); i++) {
					item = list.get(i);
					ggLabel.add(item.getName_dev());
					ggData.add(item.getCount());
				}
				
				
				String steamLabelJson = null;
				String nuuvemLabelJson = null;
				String ggLabelJson = null;
				String steamDataJson = null;
				String nuuvemDataJson = null;
				String ggDataJson = null;
				
				Gson gson = new GsonBuilder().create();
				
				steamLabelJson = gson.toJson(steamLabel);
				steamDataJson = gson.toJson(steamData);
				nuuvemLabelJson = gson.toJson(nuuvemLabel);
				nuuvemDataJson = gson.toJson(nuuvemData);
				ggLabelJson = gson.toJson(ggLabel);
				ggDataJson = gson.toJson(ggData);
				
				
				request.setAttribute("labelSteam", steamLabelJson);
				request.setAttribute("dataSteam", steamDataJson);
				
				request.setAttribute("labelNuuvem", nuuvemLabelJson);
				request.setAttribute("dataNuuvem", nuuvemDataJson);
				
				request.setAttribute("labelGG", ggLabelJson);
				request.setAttribute("dataGG", ggDataJson);				
				
				
				
			} catch (ClassNotFoundException e1) {
				System.out.println("ERRO 1");
				request.getSession().setAttribute("error", e1.getMessage());
			} catch (SQLException e1) {
				System.out.println("ERRO 2");
				request.getSession().setAttribute("error", e1.getMessage());
			} catch (Exception e1) {
				System.out.println("ERRO 3");
				request.getSession().setAttribute("error", e1.getMessage());
			}
			dispatcher = request.getRequestDispatcher("/view/diagnosis/chartsDevelopers.jsp");
			dispatcher.forward(request, response);
			break;
		case "/diagnosis/all":
			try(DAOFactory daoFactory = new DAOFactory()){
				
				diagDao = daoFactory.getDiagnosisDAO();
				
				Diagnosis diag = new Diagnosis(); 
				diagDao.set_DMFSteam(diag);
				diagDao.set_DMFNuuvem(diag);
				diagDao.set_DMFGamersGate(diag);
				diagDao.set_CMFSteam(diag);
				diagDao.set_CMFNuuvem(diag);
				diagDao.set_CMFGamersGate(diag);
				request.setAttribute("DSteam", diag.getDMFSteamList());
				
			} catch (ClassNotFoundException e1) {
				request.getSession().setAttribute("error", e1.getMessage());
			} catch (SQLException e1) {
				request.getSession().setAttribute("error", e1.getMessage());
			} catch (Exception e1) {
				request.getSession().setAttribute("error", e1.getMessage());
			}
			dispatcher = request.getRequestDispatcher("/view/diagnosis/charts.jsp");
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
