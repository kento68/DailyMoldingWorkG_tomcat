package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import model.Product;
import model.User;

@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO dao=new ProductDAO();
		String action=request.getParameter("action");
		if(action != null && action.equals("update")){
			Product product=dao.findOne(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("product", product);
			request.setAttribute("msg", "項目を編集してください。");
		}
		List<Product> list=dao.findAll();
		request.setAttribute("list", list);

	    // セッションスコープからユーザー情報を取得
	    HttpSession session = request.getSession();
	    User loginUser = (User) session.getAttribute("loginUser");
	   
	    // フォワード
	    RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/view/main.jsp");
	    rd.forward(request, response);
	    	
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String flag=request.getParameter("flag");
		String arrangementnumber=request.getParameter("arrangementnumber");
		String workperformancedate=request.getParameter("workperformancedate");
		String machinecode=request.getParameter("machinecode");
		String workmannumber=request.getParameter("workmannumber");
		String workinghours=request.getParameter("workinghours");
		String sparepartnumber1=request.getParameter("sparepartnumber1");
		String sparepartnumber2=request.getParameter("sparepartnumber2");
		String sparepartnumber3=request.getParameter("sparepartnumber3");
		String workmantime=request.getParameter("workmantime");
		String machinetime=request.getParameter("machinetime");
		String numbernodefectiveproducts=request.getParameter("numbernodefectiveproducts");
		String totalnumberdefects=request.getParameter("totalnumberdefects");
		String remarks=request.getParameter("remarks");
		String defectclassificationcode1=request.getParameter("defectclassificationcode1");
		String numberdefects1=request.getParameter("numberdefects1");
		String defectclassificationcode2=request.getParameter("defectclassificationcode2");
		String numberdefects2=request.getParameter("numberdefects2");
		String defectclassificationcode3=request.getParameter("defectclassificationcode3");
		String numberdefects3=request.getParameter("numberdefects3");
		String defectclassificationcode4=request.getParameter("defectclassificationcode4");
		String numberdefects4=request.getParameter("numberdefects4");
		String defectclassificationcode5=request.getParameter("defectclassificationcode5");
		String numberdefects5=request.getParameter("numberdefects5");
		String defectclassificationcode6=request.getParameter("defectclassificationcode6");
		String numberdefects6=request.getParameter("numberdefects6");
		String defectclassificationcode7=request.getParameter("defectclassificationcode7");
		String numberdefects7=request.getParameter("numberdefects7");
		String defectclassificationcode8=request.getParameter("defectclassificationcode8");
		String numberdefects8=request.getParameter("numberdefects8");
		String defectclassificationcode9=request.getParameter("defectclassificationcode9");
		String numberdefects9=request.getParameter("numberdefects9");
		String sparenumberdefects1=request.getParameter("sparenumberdefects1");
		String sparenumberdefects2=request.getParameter("sparenumberdefects2");
		String sparenumberdefects3=request.getParameter("sparenumberdefects3");
				
		if(arrangementnumber.isEmpty() || workperformancedate.isEmpty()){
			request.setAttribute("err","未記入の項目があります！");
		}else{
			ProductDAO dao=new ProductDAO();
			String id=request.getParameter("id");
			if(id != null){
				dao.updateOne(new Product(Integer.parseInt(id),Integer.parseInt(flag),Integer.parseInt(arrangementnumber),
						workperformancedate,machinecode,Integer.parseInt(workmannumber),workinghours,
						sparepartnumber1,sparepartnumber2,sparepartnumber3,Float.parseFloat(workmantime),
						Float.parseFloat(machinetime),Integer.parseInt(numbernodefectiveproducts),Integer.parseInt(totalnumberdefects),remarks,
						defectclassificationcode1,Integer.parseInt(numberdefects1),defectclassificationcode2,Integer.parseInt(numberdefects2),
						defectclassificationcode3,Integer.parseInt(numberdefects3),defectclassificationcode4,numberdefects4,
						defectclassificationcode5,numberdefects5,defectclassificationcode6,numberdefects6,
						defectclassificationcode7,numberdefects7,defectclassificationcode8,numberdefects8,
						defectclassificationcode9,numberdefects9,
						sparenumberdefects1,sparenumberdefects2,sparenumberdefects3));
				request.setAttribute("msg","1件更新しました。");
			}else{
				dao.insertOne(new Product(Integer.parseInt(flag),Integer.parseInt(arrangementnumber),
						workperformancedate,machinecode,Integer.parseInt(workmannumber),workinghours,
						sparepartnumber1,sparepartnumber2,sparepartnumber3,Float.parseFloat(workmantime),
						Float.parseFloat(machinetime),Integer.parseInt(numbernodefectiveproducts),Integer.parseInt(totalnumberdefects),remarks,
						defectclassificationcode1,Integer.parseInt(numberdefects1),defectclassificationcode2,Integer.parseInt(numberdefects2),
						defectclassificationcode3,Integer.parseInt(numberdefects3),defectclassificationcode4,numberdefects4,
						defectclassificationcode5,numberdefects5,defectclassificationcode6,numberdefects6,
						defectclassificationcode7,numberdefects7,defectclassificationcode8,numberdefects8,
						defectclassificationcode9,numberdefects9,
						sparenumberdefects1,sparenumberdefects2,sparenumberdefects3));
				request.setAttribute("msg","1件登録しました。");
			}
		}
		doGet(request,response);
	}
}