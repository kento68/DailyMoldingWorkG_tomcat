package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.BacklogDAO;
import dao.PartnumbermasterDAO;
import dao.ProductDAO;
import model.Partnumbermaster;
import model.Product;
import model.User;

@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO dao=new ProductDAO();
		PartnumbermasterDAO dao2=new PartnumbermasterDAO();
		
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
	    
	    // 品番の取得
	    List<Partnumbermaster> partnumberList = dao2.selectPartNumber();
	    request.setAttribute("partnumberList", partnumberList);
	    
	    // フォワード
	    RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/view/main.jsp");
	    rd.forward(request, response);
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PartnumbermasterDAO dao2 = new PartnumbermasterDAO();
	    BacklogDAO dao3 = new BacklogDAO();
		
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json;charset=UTF-8");
	    
	    String arrangementnumber=request.getParameter("arrangementnumber");
		String partnumber_check=request.getParameter("partnumber_check");
	    String partnumber = request.getParameter("partnumber");
	    String partname = request.getParameter("partname");

	    System.out.println("Received partnumber_check via POST: " + partnumber_check);
	    System.out.println("Received partnumber via POST: " + partnumber);
	    System.out.println("Received partname via POST: " + partname);
	    
	    // 品番の取得
	    List<Partnumbermaster> partnumberList = dao2.selectPartNumber();
	    request.setAttribute("partnumberList", partnumberList);
	    
	    //if (arrangementnumber != null && !arrangementnumber.isEmpty() && partnumber_check == null) {
	        //try {
	            // データベースから予備品番を取得
	            //List<Product> partnumber_checkList = dao3.selectPartnumber(arrangementnumber);

	            // レスポンスのコンテンツタイプを設定
	            //response.setContentType("application/json;charset=UTF-8");

	            //try (PrintWriter out = response.getWriter()) {
	                //if (partnumber_checkList == null || partnumber_checkList.isEmpty()) {
	                    //response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404エラー
	                    //out.write("{\"error\":\"品番リストが見つかりませんでした。\"}");
	                //} else {
	                    //String json = new Gson().toJson(partnumber_checkList);
	                    //System.out.println("取得したデータ (JSON変換後): " + json); // ログで確認
	                    //out.write(json);
	                //}
	                //out.flush();
	            //}
	        //} catch (Exception e) {
	            //e.printStackTrace();
	            //response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            //response.setContentType("application/json;charset=UTF-8");

	            //try (PrintWriter out = response.getWriter()) {
	                //out.write("{\"error\":\"サーバーでエラーが発生しました。詳細: " + e.getMessage() + "\"}");
	                //out.flush();
	            //}
	        //}
	    //}
	    
	    // 1回目のリクエスト：部品名の取得
	    if (partnumber != null && !partnumber.isEmpty() && partname == null) {
	        List<Partnumbermaster> partnameList = dao2.selectPartName(partnumber);
	        
	        // JSON形式でレスポンスを返す
	        response.setContentType("application/json;charset=UTF-8");

	        try (PrintWriter out = response.getWriter()) {
                // GsonでJSONを作成し、出力
                new Gson().toJson(partnameList, out);
	        }
	    } 
	    // 2回目のリクエスト：予備品番の取得
	    else if (partname != null && !partname.isEmpty() && partnumber != null) {
	    	try {
        	    // データベースから予備品番を取得
        	    List<String> sparepartnumberList = dao2.selectSparePartNumber(partnumber, partname);
        	    
        	    // レスポンスのコンテンツタイプを設定
        	    response.setContentType("application/json;charset=UTF-8");
        	    
        	    if (sparepartnumberList == null || sparepartnumberList.isEmpty()) {
        	        response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404エラー
        	        response.getWriter().write("{\"error\":\"予備品番が見つかりませんでした。\"}");
        	    } else {
        	        String json = new Gson().toJson(sparepartnumberList);
        	        response.getWriter().write(json);
        	    }
        	} catch (Exception e) {
        	    e.printStackTrace();
        	    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        	    response.getWriter().write("{\"error\":\"サーバーでエラーが発生しました。\"}");
        	}
	    }
	
		String flag=request.getParameter("flag");
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
		String takenumber=request.getParameter("takenumber");
				
		ProductDAO dao=new ProductDAO();
		String id=request.getParameter("id");
			if(id != null){
				dao.updateOne(new Product(Integer.parseInt(id),Integer.parseInt(flag),Integer.parseInt(arrangementnumber),
						workperformancedate,machinecode,Integer.parseInt(workmannumber),workinghours,
						partnumber,partname,sparepartnumber1,sparepartnumber2,sparepartnumber3,Float.parseFloat(workmantime),
						Float.parseFloat(machinetime),Integer.parseInt(numbernodefectiveproducts),Integer.parseInt(totalnumberdefects),remarks,
						defectclassificationcode1,numberdefects1,defectclassificationcode2,numberdefects2,
						defectclassificationcode3,numberdefects3,defectclassificationcode4,numberdefects4,
						defectclassificationcode5,numberdefects5,defectclassificationcode6,numberdefects6,
						defectclassificationcode7,numberdefects7,defectclassificationcode8,numberdefects8,
						defectclassificationcode9,numberdefects9,
						sparenumberdefects1,sparenumberdefects2,sparenumberdefects3,takenumber,partnumber_check));
				request.setAttribute("msg","1件更新しました。");
			}else{
				dao.insertOne(new Product(Integer.parseInt(flag),Integer.parseInt(arrangementnumber),
						workperformancedate,machinecode,Integer.parseInt(workmannumber),workinghours,
						partnumber,partname,sparepartnumber1,sparepartnumber2,sparepartnumber3,Float.parseFloat(workmantime),
						Float.parseFloat(machinetime),Integer.parseInt(numbernodefectiveproducts),Integer.parseInt(totalnumberdefects),remarks,
						defectclassificationcode1,numberdefects1,defectclassificationcode2,numberdefects2,
						defectclassificationcode3,numberdefects3,defectclassificationcode4,numberdefects4,
						defectclassificationcode5,numberdefects5,defectclassificationcode6,numberdefects6,
						defectclassificationcode7,numberdefects7,defectclassificationcode8,numberdefects8,
						defectclassificationcode9,numberdefects9,
						sparenumberdefects1,sparenumberdefects2,sparenumberdefects3,takenumber,partnumber_check));
				request.setAttribute("msg","1件登録しました。");
			}
	doGet(request,response);
	}
}