package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
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

import dao.BacklogDAO;
import dao.PartnumbermasterDAO;
import model.Backlog;
import model.LoginLogic;
import model.Partnumbermaster;
import model.User;

@WebServlet("/Login")
@MultipartConfig
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BacklogDAO dao=new BacklogDAO();
		
		// 手配残.csvのデータを読み込み
		String filePath = "/var/samba/Data_Transfer/DailyMoldingWorkG_tomcat/Install_Files/";  // 適切なパスを設定
        
        //<!-- UM425QA-KIR915W -->
        //<!-- DESKTOP-KBUH9GC -->
        //<!-- String filePath = "E:\\Program Files/"; -->

        //<!-- Raspberry Pi(192.168.10.103 ) -->
        //<!-- Raspberry Pi(192.168.10.122 ) -->
        //<!-- Raspberry Pi(192.168.10.118 ) -->
        //<!-- String filePath = "/var/samba/Data_Transfer/DailyMoldingWorkG_tomcat/Install_Files/"; -->

        // 指定したフォルダのインスタンスを作成
        File folder = new File(filePath);

        // フォルダ内のCSVファイルを取得
        File[] csvFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

        // "RubberOutstandingOrders.csv" という名前のファイルを探す
        File targetFile = Arrays.stream(csvFiles)
                                .filter(file -> "RubberOutstandingOrders.csv".equals(file.getName()))
                                .findFirst()
                                .orElse(null);  // ファイルが見つからなければnullを返す

        // CSVファイルが見つかった場合の処理
        if (targetFile != null) {
            List<Backlog> importedList = null; // インポートされたデータを格納するリスト

            try {
                // `targetFile` を `FilePart` オブジェクトに変換
                FilePart filePart = new FilePart(targetFile);

                // CSVデータのインポート
                importedList = dao.importOne(filePart);  

                // --- CSVファイルの削除処理 ---
                try {
                    Files.delete(targetFile.toPath());  // ファイル削除
                    System.out.println("ファイルが削除されました: " + targetFile.getAbsolutePath());
                } catch (IOException e) {
                    System.err.println("ファイル削除に失敗しました: " + e.getMessage());
                }
                
            } catch (ParseException e) {
                e.printStackTrace();
                request.setAttribute("err", "CSVデータの解析中にエラーが発生しました。");
                return;  // エラー時は処理終了
            } catch (NamingException e) {
                e.printStackTrace();
                request.setAttribute("err", "データベース接続中にエラーが発生しました。");
                return;  // エラー時は処理終了
            } catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}

            // インポートされたデータのデータベース保存処理
            for (Backlog backlog : importedList) {
                try {
                    dao.save(backlog);  // データベースに保存
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("err", "データベース保存中にエラーが発生しました。");
                    return;  // エラー時は処理終了
                }
            }
            
            // インポート成功時のメッセージ設定
            request.setAttribute("importedList", importedList);
            request.setAttribute("msg", "手配リストの情報が更新されました。");
        }
        
        // --- データベース内で重複している手配番号を削除する処理 ---
        try {
			dao.removeDuplicateArrangementNumbers();
		} catch (SQLException | NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        
		// リクエストパラメータの取得
	    request.setCharacterEncoding("UTF-8");
	    String name = request.getParameter("name");
	    String pass = request.getParameter("pass");

	    // Userインスタンス（ユーザー情報）の生成
	    User user = new User(name, pass);
	    // ログイン処理
	    LoginLogic loginLogic = new LoginLogic();
	    boolean isLogin = loginLogic.execute(user);

	    // ログイン成功時の処理
	    if (isLogin) {
	      // ユーザー情報をセッションスコープに保存
	      HttpSession session = request.getSession();
	      session.setAttribute("loginUser", user);
	    }
	    
	    PartnumbermasterDAO dao2 = new PartnumbermasterDAO();
	    List<Partnumbermaster> partnumberList = dao2.selectPartNumber();
	    request.setAttribute("partnumberList", partnumberList);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/main.jsp");
	    dispatcher.forward(request, response);
	  }
	}
