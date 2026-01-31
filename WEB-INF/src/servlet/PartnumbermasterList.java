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

@WebServlet("/PartnumbermasterList")
public class PartnumbermasterList extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PartnumbermasterDAO dao=new PartnumbermasterDAO();
		String searchKeyword = request.getParameter("searchKeyword"); // 検索キーワードを取得
		String action = request.getParameter("action");
		List<Partnumbermaster> list;

		// セッションスコープからユーザー情報を取得
	    HttpSession session = request.getSession();
	    User loginUser = (User) session.getAttribute("loginUser");

		// 検索処理
		if (searchKeyword != null && !searchKeyword.isEmpty()) {
			list = dao.searchOne(searchKeyword); // DAOで検索を実行
		} else {
			list = dao.findAll(); // 検索キーワードが指定されていない場合は全件取得
		}
		
		if (action != null) {
			String idParam = request.getParameter("id");
			if (action.equals("delete") && idParam != null) {
				dao.deleteOne(Integer.parseInt(idParam));
				request.setAttribute("msg", "1件削除しました。");
			}
		}
		
		// ダウンロード処理の追加
		// InventoryProcessing が実行済みかどうかを確認
		Boolean isProcessingDone = (Boolean) session.getAttribute("processingDone");
		Integer count = (Integer) session.getAttribute("count"); // セッションからカウント数を取得

		if (count == null) {
		    count = 0; // 初期値として0を設定
		}

		if (isProcessingDone == null || !isProcessingDone) {
		    if ("download".equals(action)) {
		        try {
		            dao.downloadAll();
		            request.setAttribute("msg", "CSVファイルをダウンロードしました。");
		            session.setAttribute("processingDone", true); // 処理完了フラグを設定
		            System.out.println("ダウンロード処理が完了しました。");
		        } catch (IOException e) {
		            e.printStackTrace();
		            request.setAttribute("err", "CSVファイルの生成中にエラーが発生しました。");
		            session.setAttribute("processingDone", false); // フラグを設定
		        }
		    }
		} else {
		    // 棚卸処理が完了している場合のメッセージ表示
		    if (count <= 1) {
		    } else {
		        request.setAttribute("msg", "CSVファイルをダウンロードしました。");
		    }
		}
		// カウントをインクリメントし、セッションに保存
		session.setAttribute("count", count + 1);
        
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/partnumbermasterlist.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PartnumbermasterDAO dao=new PartnumbermasterDAO();
		String action = request.getParameter("action");
		String[] selectedIds = request.getParameterValues("selectedIds"); // 選択されたIDを取得

		if (selectedIds != null && selectedIds.length > 0) {
			if ("deleteSelected".equals(action)) {
				for (String id : selectedIds) {
					dao.deleteOne(Integer.parseInt(id)); // IDに基づいて削除
				}
				request.setAttribute("msg", selectedIds.length + "件削除しました。");
			}
		}
		// 再度一覧を表示
		doGet(request, response);
	}
}

