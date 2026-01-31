package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PartnumbermasterDAO;
import model.Partnumbermaster;
import model.User;

/**
 * Servlet implementation class PartnumbermasterForm
 */
@WebServlet("/PartnumbermasterForm")
public class PartnumbermasterForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PartnumbermasterDAO dao=new PartnumbermasterDAO();
		String action=request.getParameter("action");
		if(action != null && action.equals("update")){
			Partnumbermaster partnumbermaster=dao.findOne(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("partnumbermaster", partnumbermaster);
			request.setAttribute("msg", "項目を編集してください。");
		}
		List<Partnumbermaster> list=dao.findAll();
		request.setAttribute("list", list);

	    // セッションスコープからユーザー情報を取得
	    HttpSession session = request.getSession();
	    User loginUser = (User) session.getAttribute("loginUser");
	   
	    // フォワード
	    RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/view/partnumbermasterform.jsp");
	    rd.forward(request, response);
	    	
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String partnumber=request.getParameter("partnumber");
		String partname=request.getParameter("partname");
		String sparepartnumber1=request.getParameter("sparepartnumber1");
		String sparepartnumber2=request.getParameter("sparepartnumber2");
		String sparepartnumber3=request.getParameter("sparepartnumber3");
		String takenumber=request.getParameter("takenumber");

		PartnumbermasterDAO dao=new PartnumbermasterDAO();
		String id=request.getParameter("id");
		if(id != null){
			dao.updateOne(new Partnumbermaster(Integer.parseInt(id),partnumber,partname,sparepartnumber1,sparepartnumber2,sparepartnumber3,takenumber));
			request.setAttribute("msg","1件更新しました。");
		}else{
			dao.insertOne(new Partnumbermaster(partnumber,partname,sparepartnumber1,sparepartnumber2,sparepartnumber3,takenumber));
			request.setAttribute("msg","1件登録しました。");
		}
		doGet(request,response);
	}
}