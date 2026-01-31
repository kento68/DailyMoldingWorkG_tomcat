package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		ProductDAO dao = new ProductDAO();
		String searchKeyword = request.getParameter("searchKeyword"); // 検索キーワードを取得
		List<Product> list;

		// 検索処理
		if (searchKeyword != null && !searchKeyword.isEmpty()) {
			list = dao.searchOne(searchKeyword); // DAOで検索を実行
		} else {
			list = dao.findAll(); // 検索キーワードが指定されていない場合は全件取得
		}
		
		String action = request.getParameter("action");
		if (action != null) {
			String idParam = request.getParameter("id");
			if (action.equals("delete") && idParam != null) {
				dao.deleteOne(Integer.parseInt(idParam));
				request.setAttribute("msg", "1件削除しました。");
			} else if (action.equals("download") && idParam != null) {
				dao.downloadOne(Integer.parseInt(idParam));
				request.setAttribute("msg", "項目をダウンロードしました。");
			}
		}

		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/ingestiondata.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ProductDAO dao = new ProductDAO();
	    String action = request.getParameter("action");
	    String[] selectedIds = request.getParameterValues("selectedIds"); // 選択されたIDを取得

	    if (selectedIds != null && selectedIds.length > 0) {
	        if ("deleteSelected".equals(action)) {
	            for (String id : selectedIds) {
	                dao.deleteOne(Integer.parseInt(id)); // IDに基づいて削除
	            }
	            request.setAttribute("msg", selectedIds.length + "件削除しました。");
	        } else if ("downloadSelected".equals(action)) {
	            for (String id : selectedIds) {
	                dao.downloadOne(Integer.parseInt(id)); // IDに基づいてダウンロード
	            }
	            request.setAttribute("msg", selectedIds.length + "件ダウンロードしました。");

	            // CSVダウンロード処理
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	            String currentDateAndTime = sdf.format(new Date());
	            String fileName = "AllList " + currentDateAndTime + ".csv";

	            String filePath = "/var/samba/Data_Transfer/DailyMoldingWorkG_tomcat/Download_Files/"+ fileName;
	            
	            //<!-- UM425QA-KIR915W -->
	            //<!-- DESKTOP-KBUH9GC -->
	            //<!-- String filePath = "E:\\Program Files/"+ fileName; -->

	            //<!-- Raspberry Pi(192.168.10.103 ) -->
	            //<!-- Raspberry Pi(192.168.10.122 ) -->
	            //<!-- Raspberry Pi(192.168.10.118 ) -->
	            //<!-- String filePath = "/var/samba/Data_Transfer/DailyMoldingWorkG_tomcat/Download_Files/"+ fileName; -->

	            try (PrintWriter csvWriter = new PrintWriter(filePath, "MS932")) {
	                // ヘッダー行
	                csvWriter.append("フラグ,手配番号,品番,作業実績日,作業担当者,日勤夜勤区分,良品数,合計不良数,作業工数,機械時間,機械コード,備考,"
	                        + "不良分類コード1,不良数1,不良分類コード2,不良数2,不良分類コード3,不良数3,"
	                        + "不良分類コード4,不良数4,不良分類コード5,不良数5,不良分類コード6,不良数6,"
	                        + "不良分類コード7,不良数7,不良分類コード8,不良数8,不良分類コード9,不良数9,"
	                        + "予備品番1,予備品番2,予備品番3,予備不良数1,予備不良数2,予備不良数3");
	                csvWriter.append("\n");

	                // 選択されたIDごとのデータをCSVに追加
	                for (String id : selectedIds) {
	                    dao.downloadOne_All(Integer.parseInt(id), csvWriter);
	                }
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	                request.setAttribute("err", "CSV出力中にエラーが発生しました。");
	            }
	        }
	      }
	    // 再度一覧を表示
	    doGet(request, response);
	}
}