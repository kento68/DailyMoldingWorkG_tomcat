package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

@WebServlet("/Ingestiondata")
public class Ingestiondata extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO dao=new ProductDAO();
		String action=request.getParameter("action");
		if(action != null && action.equals("delete")){
			dao.deleteOne(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("msg", "1件削除しました。");
		}else if(action != null && action.equals("download")){
			dao.downloadOne(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("msg", "項目をダウンロードしました。");
		}
		List<Product> list=dao.findAll();
		request.setAttribute("list", list);
		
	    RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/view/ingestiondata.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
