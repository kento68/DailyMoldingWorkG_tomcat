package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.BacklogDAO;
import model.Backlog;
import model.User;

@WebServlet("/BacklogList")
@MultipartConfig
public class BacklogList extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		BacklogDAO dao=new BacklogDAO();
		String action=request.getParameter("action");	
		String searchKeyword = request.getParameter("searchKeyword"); // 検索キーワードを取得
		
		List<Backlog> list;
		// セッションスコープからユーザー情報を取得
	    HttpSession session = request.getSession();
	    User loginUser = (User) session.getAttribute("loginUser");
	    
		// 検索処理
		if (searchKeyword != null && !searchKeyword.isEmpty()) {
			list = dao.searchOne(searchKeyword); // DAOで検索を実行
		} else {
			list = dao.findAll(); // 検索キーワードが指定されていない場合は全件取得
		}
		
		if ("import".equals(action)) {
		    synchronized (this) { // 同期化してリクエストを1回だけ処理する
		        if (session.getAttribute("importProcessed") == null) { // 既に処理済みでないことを確認
		            session.setAttribute("importProcessed", true); // ★ 最初に設定し、確実に1回だけ処理されるようにするを確認
		            Part filePart = request.getPart("csvFiles"); // リクエストからCSVファイルを取得
		            
		            if (filePart != null && filePart.getSize() > 0) { // ファイルがアップロードされているか確認
		                List<Backlog> importedList = Collections.emptyList(); // 空リストで初期化
		                StringBuilder errMsg = new StringBuilder();
		                
		                // --- データベース内にCSVデータをインポートする処理 ---
		                try {
		                    importedList = dao.importOne(filePart); // CSVファイルをインポートしてリストに変換
		                    if (!importedList.isEmpty()) {
		                        session.setAttribute("importProcessed", true); // ★ 成功時のみ設定
		                    }// インポート処理済みフラグをセッションに設定
		                    
		                    for (Backlog backlog : importedList) { // インポートされた各データをデータベースに保存
		                        try {
		                            dao.save(backlog); // データベースに保存
		                        } catch (Exception e) {
		                            e.printStackTrace();
		                            errMsg.append("データベース保存中にエラーが発生しました。").append("<br>");
		                        }
		                    }
		                    
		                    // --- データベース内で重複している手配番号を削除する処理 ---
		                    try {
		                        dao.removeDuplicateArrangementNumbers();
		                    } catch (SQLException | NamingException e) {
		                        e.printStackTrace();
		                        errMsg.append("重複削除中にエラーが発生しました。").append("<br>");
		                    }
		                    
		                } catch (ParseException | SQLException | NamingException e) {
		                    e.printStackTrace();
		                    errMsg.append("インポート処理中にエラーが発生しました。").append("<br>");
		                }
		                
		                if (errMsg.length() > 0) {
		                    request.setAttribute("err", errMsg.toString()); // エラーがある場合はエラーメッセージのみ設定
		                } else {
		                    request.setAttribute("importedList", importedList); // インポートされたリストをリクエストに設定
		                    request.setAttribute("msg", "手配リストの情報が更新されました。"); // 成功メッセージをリクエストに設定
		                }
		                
		        		request.setAttribute("list", list);
		        		
		        	    RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/view/main.jsp");
		        	    rd.forward(request, response);
		        	    return;
		        	    
		            } else {
		                request.setAttribute("err", "ファイルが選択されていません。"); // ファイルが選択されていない場合のエラーメッセージを設定
		            }
		        } else {
		            request.setAttribute("err", "手配リストの手動更新は処理済みです。再ログインしてください。"); // 既に処理済みのメッセージを表示
		        }
		    }
		}

		request.setAttribute("list", list);

	    RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/view/backloglist.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 再度一覧を表示
	    doGet(request, response);
	}

}