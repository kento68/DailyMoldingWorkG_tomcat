package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import model.Backlog;
import model.Product;


public class BacklogDAO {
	private Connection db;
	private PreparedStatement ps;
	private ResultSet rs;

private void getConnection() throws NamingException, SQLException{
			Context context=new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/jsp");
			this.db=ds.getConnection();
	}
	private void disconnect(){
		try{
			if(rs != null){rs.close();}
			if(ps != null){ps.close();}
			if(db != null){db.close();}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public List<Backlog> findAll(){
		List<Backlog> backlogList=new ArrayList<>();
		try {
			this.getConnection();
			ps=db.prepareStatement("SELECT * FROM backlogs ORDER BY id DESC");
			rs=ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String inquiryDivision = rs.getString("inquiryDivision");
				String supplierCode = rs.getString("supplierCode");
				String supplierName = rs.getString("supplierName");
				String productCode = rs.getString("productCode");
				String productName = rs.getString("productName");
				String orderSequence = rs.getString("orderSequence");
				String orderNumber = rs.getString("orderNumber");
				Date deliveryDateFrom = rs.getDate("deliveryDateFrom");
				Date deliveryDateTo = rs.getDate("deliveryDateTo");
				Date deliveryDate = rs.getDate("deliveryDate");
				String instructionCode = rs.getString("instructionCode");
				String instructionName = rs.getString("instructionName");
				String arrangementNumber = rs.getString("arrangementNumber");
				String itemCode = rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String sequence = rs.getString("sequence");
				String processDetails = rs.getString("processDetails");
				int arrangementQuantity = rs.getInt("arrangementQuantity");
				String unit = rs.getString("unit");
				float arrangementUnitPrice = rs.getFloat("arrangementUnitPrice");
				int remainingArrangementQuantity = rs.getInt("remainingArrangementQuantity");
				String receivingInProgress = rs.getString("receivingInProgress");

				Backlog backlog = new Backlog(id,inquiryDivision, supplierCode, supplierName, productCode,
					     productName, orderSequence, orderNumber, deliveryDateFrom,
					     deliveryDateTo, deliveryDate, instructionCode, instructionName,
					     arrangementNumber, itemCode, itemName, sequence, processDetails,
					     arrangementQuantity, unit, arrangementUnitPrice,
					     remainingArrangementQuantity, receivingInProgress);
				
				backlogList.add(backlog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
		return backlogList;
	}
	public boolean insertOne(Backlog backlog){
		try {
			this.getConnection();
			ps = db.prepareStatement(
				    "INSERT INTO backlogs (inquiryDivision, supplierCode, supplierName, productCode, " +
				    "productName, orderSequence, orderNumber, deliveryDateFrom, " +
				    "deliveryDateTo, deliveryDate, instructionCode, instructionName, " +
				    "arrangementNumber, itemCode, itemName, sequence, processDetails, " +
				    "arrangementQuantity, unit, arrangementUnitPrice, " +
				    "remainingArrangementQuantity, receivingInProgress) " +
				    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
				);

			ps.setString(1, backlog.getInquiryDivision());
			ps.setString(2, backlog.getSupplierCode());
			ps.setString(3, backlog.getSupplierName());
			ps.setString(4, backlog.getProductCode());
			ps.setString(5, backlog.getProductName());
			ps.setString(6, backlog.getOrderSequence());
			ps.setString(7, backlog.getOrderNumber());
			ps.setDate(8, new java.sql.Date(backlog.getDeliveryDateFrom().getTime()));
			ps.setDate(9, new java.sql.Date(backlog.getDeliveryDateTo().getTime()));
			ps.setDate(10, new java.sql.Date(backlog.getDeliveryDate().getTime()));
			ps.setString(11, backlog.getInstructionCode());
			ps.setString(12, backlog.getInstructionName());
			ps.setString(13, backlog.getArrangementNumber());
			ps.setString(14, backlog.getItemCode());
			ps.setString(15, backlog.getItemName());
			ps.setString(16, backlog.getSequence());
			ps.setString(17, backlog.getProcessDetails());
			ps.setInt(18, backlog.getArrangementQuantity());
			ps.setString(19, backlog.getUnit());
			ps.setFloat(20, backlog.getArrangementUnitPrice());
			ps.setInt(21, backlog.getRemainingArrangementQuantity());
			ps.setString(22, backlog.getReceivingInProgress());

			int result=ps.executeUpdate();
			if(result != 1){
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
		return true;
	}
	public Backlog findOne(int id){
		Backlog backlog=null;
		try{
			this.getConnection();
			ps=db.prepareStatement("SELECT * FROM backlogs WHERE id=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				
				String inquiryDivision = rs.getString("inquiryDivision");
				String supplierCode = rs.getString("supplierCode");
				String supplierName = rs.getString("supplierName");
				String productCode = rs.getString("productCode");
				String productName = rs.getString("productName");
				String orderSequence = rs.getString("orderSequence");
				String orderNumber = rs.getString("orderNumber");
				Date deliveryDateFrom = rs.getDate("deliveryDateFrom");
				Date deliveryDateTo = rs.getDate("deliveryDateTo");
				Date deliveryDate = rs.getDate("deliveryDate");
				String instructionCode = rs.getString("instructionCode");
				String instructionName = rs.getString("instructionName");
				String arrangementNumber = rs.getString("arrangementNumber");
				String itemCode = rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String sequence = rs.getString("sequence");
				String processDetails = rs.getString("processDetails");
				int arrangementQuantity = rs.getInt("arrangementQuantity");
				String unit = rs.getString("unit");
				float arrangementUnitPrice = rs.getFloat("arrangementUnitPrice");
				int remainingArrangementQuantity = rs.getInt("remainingArrangementQuantity");
				String receivingInProgress = rs.getString("receivingInProgress");
				
				backlog = new Backlog(id,inquiryDivision, supplierCode, supplierName, productCode,
					     productName, orderSequence, orderNumber, deliveryDateFrom,
					     deliveryDateTo, deliveryDate, instructionCode, instructionName,
					     arrangementNumber, itemCode, itemName, sequence, processDetails,
					     arrangementQuantity, unit, arrangementUnitPrice,
					     remainingArrangementQuantity, receivingInProgress);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
		return backlog;
	}
	
	public boolean updateOne(Backlog backlog){
		try{
			this.getConnection();
			ps = db.prepareStatement(
				    "UPDATE backlogs SET " +
				    "inquiryDivision = ?, supplierCode = ?, supplierName = ?, productCode = ?, " +
				    "productName = ?, orderSequence = ?, orderNumber = ?, deliveryDateFrom = ?, " +
				    "deliveryDateTo = ?, deliveryDate = ?, instructionCode = ?, instructionName = ?, " +
				    "arrangementNumber = ?, itemCode = ?, itemName = ?, sequence = ?, processDetails = ?, " +
				    "arrangementQuantity = ?, unit = ?, arrangementUnitPrice = ?, " +
				    "remainingArrangementQuantity = ?, receivingInProgress = ? " +
				    "WHERE id = ?"
				);

			ps.setString(1, backlog.getInquiryDivision());
			ps.setString(2, backlog.getSupplierCode());
			ps.setString(3, backlog.getSupplierName());
			ps.setString(4, backlog.getProductCode());
			ps.setString(5, backlog.getProductName());
			ps.setString(6, backlog.getOrderSequence());
			ps.setString(7, backlog.getOrderNumber());
			ps.setDate(8, new java.sql.Date(backlog.getDeliveryDateFrom().getTime()));
			ps.setDate(9, new java.sql.Date(backlog.getDeliveryDateTo().getTime()));
			ps.setDate(10, new java.sql.Date(backlog.getDeliveryDate().getTime()));
			ps.setString(11, backlog.getInstructionCode());
			ps.setString(12, backlog.getInstructionName());
			ps.setString(13, backlog.getArrangementNumber());
			ps.setString(14, backlog.getItemCode());
			ps.setString(15, backlog.getItemName());
			ps.setString(16, backlog.getSequence());
			ps.setString(17, backlog.getProcessDetails());
			ps.setInt(18, backlog.getArrangementQuantity());
			ps.setString(19, backlog.getUnit());
			ps.setFloat(20, backlog.getArrangementUnitPrice());
			ps.setInt(21, backlog.getRemainingArrangementQuantity());
			ps.setString(22, backlog.getReceivingInProgress());

			ps.setInt(23, backlog.getId());
			int result=ps.executeUpdate();
			if(result != 1){
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
		return true;
	}
	
	public List<Backlog> searchOne(String search) {
		List<Backlog> backlogList=new ArrayList<>();
	    try {
	        this.getConnection();
	        ps = db.prepareStatement("SELECT * FROM backlogs WHERE arrangementNumber LIKE ? OR itemCode LIKE ? OR itemName LIKE ?");

	        String searchPattern = "%" + search + "%";

	        // プリペアードステートメントのパラメータを設定
	        for (int i = 1; i <= 3; i++) {
	            ps.setString(i, searchPattern);
	        }

	        rs = ps.executeQuery();
	        while (rs.next()) {
				int id = rs.getInt("id");
	        	String inquiryDivision = rs.getString("inquiryDivision");
				String supplierCode = rs.getString("supplierCode");
				String supplierName = rs.getString("supplierName");
				String productCode = rs.getString("productCode");
				String productName = rs.getString("productName");
				String orderSequence = rs.getString("orderSequence");
				String orderNumber = rs.getString("orderNumber");
				Date deliveryDateFrom = rs.getDate("deliveryDateFrom");
				Date deliveryDateTo = rs.getDate("deliveryDateTo");
				Date deliveryDate = rs.getDate("deliveryDate");
				String instructionCode = rs.getString("instructionCode");
				String instructionName = rs.getString("instructionName");
				String arrangementNumber = rs.getString("arrangementNumber");
				String itemCode = rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String sequence = rs.getString("sequence");
				String processDetails = rs.getString("processDetails");
				int arrangementQuantity = rs.getInt("arrangementQuantity");
				String unit = rs.getString("unit");
				float arrangementUnitPrice = rs.getFloat("arrangementUnitPrice");
				int remainingArrangementQuantity = rs.getInt("remainingArrangementQuantity");
				String receivingInProgress = rs.getString("receivingInProgress");

				Backlog backlog=new Backlog(id,inquiryDivision, supplierCode, supplierName, productCode,
					     productName, orderSequence, orderNumber, deliveryDateFrom,
					     deliveryDateTo, deliveryDate, instructionCode, instructionName,
					     arrangementNumber, itemCode, itemName, sequence, processDetails,
					     arrangementQuantity, unit, arrangementUnitPrice,
					     remainingArrangementQuantity, receivingInProgress);
				
				backlogList.add(backlog);
	        }
	    } catch (SQLException | NamingException e) {
	        e.printStackTrace();
	    } finally {
	        this.disconnect();
	    }
	    return backlogList;
	}
	
	public List<Backlog> importOne(Part filePart) throws NamingException, ParseException, SQLException {
	    List<Backlog> backlogList = new ArrayList<>();
	    Set<String> arrangementNumbers = new HashSet<>();  // 重複防止用Set

	    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(filePart.getInputStream(), "MS932"), 65536)) {
	        buffer.readLine(); // 1行目（ヘッダー）をスキップ

	        String line;
	        int lineNumber = 0; // 行番号をカウント
	        while ((line = buffer.readLine()) != null) {
	            lineNumber++;
	            line = line.replace("\r", "").replaceAll("\\p{C}", "").trim();  // 改行や制御文字の除去
	            if (line.isEmpty()) continue;  // 空行はスキップ

	            String[] columns = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);  
	            if (columns.length < 22) {
	                System.err.println("行番号 " + lineNumber + " の列数が不足しています: " + columns.length);
	                continue;
	            }

	            if (columns.length >= 22) {  // 配列の長さチェックを追加
	                String arrangementNumber = columns[12].trim();
	                
	                // 重複確認
	                if (arrangementNumbers.contains(arrangementNumber)) {
	                    System.out.println("行番号 " + lineNumber + ": 手配番号 " + arrangementNumber + " は重複しているためスキップしました。");
	                    continue;
	                }

	                // 手配番号を記録
	                arrangementNumbers.add(arrangementNumber);

	                float column19 = parseFloatValue(columns[19]);   // 手配単価 (float)

	                String mergedValue20 = columns[20] != null 
	                        ? columns[20].replaceAll("[^0-9.]", "").replace("\"", "").trim() 
	                        : "0";
	                int column20 = mergedValue20.isEmpty() 
	                        ? 0 
	                        : (int) Math.round(Double.parseDouble(mergedValue20));

	                String mergedValue17 = columns[17] != null 
	                        ? columns[17].replaceAll("[^0-9.]", "").replace("\"", "").trim() 
	                        : "0";
	                int column17 = mergedValue17.isEmpty() 
	                        ? 0 
	                        : (int) Math.round(Double.parseDouble(mergedValue17));

	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/M/d");
	                Date deliveryDateFrom = columns[7].isEmpty() ? null : dateFormat.parse(columns[7]);
	                Date deliveryDateTo   = columns[8].isEmpty() ? null : dateFormat.parse(columns[8]);
	                Date deliveryDate     = columns[9].isEmpty() ? null : dateFormat.parse(columns[9]);

	                Backlog backlog = new Backlog(
	                    columns[0], columns[1], columns[2], columns[3], columns[4], 
	                    columns[5], columns[6], deliveryDateFrom, deliveryDateTo, 
	                    deliveryDate, columns[10], columns[11], arrangementNumber, 
	                    columns[13], columns[14], columns[15], columns[16], 
	                    column17, columns[18], column19, column20, columns[21]
	                );

	                backlogList.add(backlog);
	            }
	        }
	        
	        // データベース上のすべての手配番号を取得
	        Set<String> existingNumbers = fetchAllArrangementNumbers();  // データベースからすべての手配番号を取得

	        // CSVに含まれない手配番号をデータベースから削除
	        Set<String> numbersToDelete = new HashSet<>(existingNumbers);
	        numbersToDelete.removeAll(arrangementNumbers);  // CSVに含まれていない手配番号を削除対象として特定

	        if (!numbersToDelete.isEmpty()) {
	            System.out.println("削除対象の手配番号: " + numbersToDelete);
	            deleteBacklogs(numbersToDelete);  // 削除処理を実行
	        } else {
	            System.out.println("削除対象の手配番号はありません。");
	        }

	        // 一括データベースチェック
	        Set<String> bulkDatabaseCheck = getExistingArrangementNumbers(arrangementNumbers);

	        backlogList.removeIf(backlog -> {
	            if (bulkDatabaseCheck.contains(backlog.getArrangementNumber().trim().toUpperCase())) {
	                System.out.println("手配番号 " + backlog.getArrangementNumber() + " は既に存在するためスキップしました。");
	                return true;  // 削除
	            }
	            System.out.println("手配番号 " + backlog.getArrangementNumber() + " は新規データとして追加されました。");
	            return false;
	        });

	    } catch (IOException e) {
	        e.printStackTrace();
	    }


	    try {
	    	this.getConnection(); // DB接続を取得
	        db.setAutoCommit(false); // 自動コミットを無効化

	        // 挿入用の準備
	        PreparedStatement ps = db.prepareStatement(
				    "INSERT INTO backlogs (inquiryDivision, supplierCode, supplierName, productCode, " +
				    "productName, orderSequence, orderNumber, deliveryDateFrom, " +
				    "deliveryDateTo, deliveryDate, instructionCode, instructionName, " +
				    "arrangementNumber, itemCode, itemName, sequence, processDetails, " +
				    "arrangementQuantity, unit, arrangementUnitPrice, " +
				    "remainingArrangementQuantity, receivingInProgress) " +
				    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
				);

	        for (Backlog backlog : backlogList) {
	            // プリペアドステートメントのパラメータを設定
	        	ps.setString(1, backlog.getInquiryDivision());
				ps.setString(2, backlog.getSupplierCode());
				ps.setString(3, backlog.getSupplierName());
				ps.setString(4, backlog.getProductCode());
				ps.setString(5, backlog.getProductName());
				ps.setString(6, backlog.getOrderSequence());
				ps.setString(7, backlog.getOrderNumber());
				ps.setDate(8, new java.sql.Date(backlog.getDeliveryDateFrom().getTime()));
				ps.setDate(9, new java.sql.Date(backlog.getDeliveryDateTo().getTime()));
				ps.setDate(10, new java.sql.Date(backlog.getDeliveryDate().getTime()));
				ps.setString(11, backlog.getInstructionCode());
				ps.setString(12, backlog.getInstructionName());
				ps.setString(13, backlog.getArrangementNumber());
				ps.setString(14, backlog.getItemCode());
				ps.setString(15, backlog.getItemName());
				ps.setString(16, backlog.getSequence());
				ps.setString(17, backlog.getProcessDetails());
				ps.setInt(18, backlog.getArrangementQuantity());
				ps.setString(19, backlog.getUnit());
				ps.setFloat(20, backlog.getArrangementUnitPrice());
				ps.setInt(21, backlog.getRemainingArrangementQuantity());
				ps.setString(22, backlog.getReceivingInProgress());

	            try {
	                ps.executeUpdate(); // データベースに挿入
	                db.commit(); // コミット
	            } catch (SQLException e) {
	                System.err.println("Failed to insert row: " + backlog.toString());
	                e.printStackTrace();
	                db.rollback(); // 挿入失敗時にロールバック
	            }
	        }
	        
	        db.commit(); // すべての行の挿入後にコミット

	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (db != null) db.rollback();
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (db != null) db.close();
	        } catch (SQLException closeEx) {
	            closeEx.printStackTrace();
	        }
	    }

	    return backlogList;
	}

	public void save(Backlog backlog) throws NamingException {
	    try {
	        this.getConnection();
	            // 挿入用の準備
	            PreparedStatement ps = db.prepareStatement("INSERT INTO backlogs (inquiryDivision, supplierCode, supplierName, productCode, " +
					    "productName, orderSequence, orderNumber, deliveryDateFrom, " +
					    "deliveryDateTo, deliveryDate, instructionCode, instructionName, " +
					    "arrangementNumber, itemCode, itemName, sequence, processDetails, " +
					    "arrangementQuantity, unit, arrangementUnitPrice, " +
					    "remainingArrangementQuantity, receivingInProgress) " +
					    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
	    				);

	            // プリペアドステートメントのパラメータを設定
	        	ps.setString(1, backlog.getInquiryDivision());
				ps.setString(2, backlog.getSupplierCode());
				ps.setString(3, backlog.getSupplierName());
				ps.setString(4, backlog.getProductCode());
				ps.setString(5, backlog.getProductName());
				ps.setString(6, backlog.getOrderSequence());
				ps.setString(7, backlog.getOrderNumber());
				ps.setDate(8, new java.sql.Date(backlog.getDeliveryDateFrom().getTime()));
				ps.setDate(9, new java.sql.Date(backlog.getDeliveryDateTo().getTime()));
				ps.setDate(10, new java.sql.Date(backlog.getDeliveryDate().getTime()));
				ps.setString(11, backlog.getInstructionCode());
				ps.setString(12, backlog.getInstructionName());
				ps.setString(13, backlog.getArrangementNumber());
				ps.setString(14, backlog.getItemCode());
				ps.setString(15, backlog.getItemName());
				ps.setString(16, backlog.getSequence());
				ps.setString(17, backlog.getProcessDetails());
				ps.setInt(18, backlog.getArrangementQuantity());
				ps.setString(19, backlog.getUnit());
				ps.setFloat(20, backlog.getArrangementUnitPrice());
				ps.setInt(21, backlog.getRemainingArrangementQuantity());
				ps.setString(22, backlog.getReceivingInProgress());

	            // 挿入を実行
	            ps.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private float parseFloatValue(String value) {

	    if (value == null || value.trim().isEmpty()) {
	        return 0.0f;
	    }
	    try {
	        // カンマとダブルクォートを除去してから変換
	        String cleanedValue = value.replaceAll("[\",]", "").trim();
	        return Float.parseFloat(cleanedValue);
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        return 0.0f;
	    }
	}
	
	// 指定された手配番号のリストから、すでにデータベースに存在する手配番号を取得するメソッド
	private Set<String> getExistingArrangementNumbers(Set<String> arrangementNumbers) throws NamingException, SQLException {
	    // データベースに存在する手配番号を格納するためのセット
	    Set<String> existingNumbers = new HashSet<>();

	    // 手配番号のリストが空の場合は、処理を行わずに空のセットを返す
	    if (arrangementNumbers.isEmpty()) {
	        return existingNumbers;
	    }

	    try {
	        // データベース接続を取得
	        this.getConnection();

	        // SQLクエリのIN句を動的に作成
	        // Collections.nCopies(arrangementNumbers.size(), "?") で "?" を手配番号の数だけ生成し、カンマで連結
	        String sql = "SELECT TRIM(arrangementNumber) FROM backlogs WHERE TRIM(arrangementNumber) IN (" +
	                     String.join(",", Collections.nCopies(arrangementNumbers.size(), "?")) + ")";

	        // PreparedStatementを用いてSQLインジェクションを防止しつつ、クエリを実行
	        try (PreparedStatement ps = db.prepareStatement(sql)) {
	            // 手配番号をIN句のプレースホルダーに設定
	            int index = 1;
	            for (String number : arrangementNumbers) {
	                ps.setString(index++, number.trim());
	            }

	            // SQLクエリの結果を処理
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    // 結果セットから手配番号を取得し、空白を除去してセットに追加
	                    existingNumbers.add(rs.getString(1).trim());
	                }
	            }
	        }
	    } finally {
	        // データベース接続を閉じる
	        if (db != null) {
	            try { 
	                db.close(); 
	            } catch (SQLException e) { 
	                e.printStackTrace(); 
	            }
	        }
	    }

	    // データベースに存在する手配番号のセットを返す
	    return existingNumbers;
	}
	
	public void removeDuplicateArrangementNumbers() throws SQLException, NamingException {
	    try {
	        this.getConnection();  // データベース接続の確立

	        String sql = """
	            DELETE b1 FROM backlogs b1 JOIN backlogs b2 ON b1.arrangementNumber = b2.arrangementNumber AND b1.id < b2.id
	            """;

	        try (PreparedStatement ps = db.prepareStatement(sql)) {
	            int deletedCount = ps.executeUpdate();
	            System.out.println("削除した重複データ数: " + deletedCount);
	        }
	    } finally {
	        if (db != null) {
	            try {
	                db.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	// データベースからすべての手配番号を取得する新規メソッド
	public Set<String> fetchAllArrangementNumbers() throws SQLException, NamingException {
	    Set<String> arrangementNumbers = new HashSet<>();
	    String query = "SELECT arrangementNumber FROM backlogs";  // 必要なテーブル名とカラムを設定

	    try {
	        // データベース接続を取得
	        this.getConnection();

	        // PreparedStatementでクエリを実行
	        PreparedStatement ps = db.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();  // クエリ実行

	        // 結果セットから手配番号を取り出す
	        while (rs.next()) {
	            arrangementNumbers.add(rs.getString("arrangementNumber").trim().toUpperCase());
	        }

	        // リソースをクローズ
	        rs.close();
	        ps.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;  // エラーを呼び出し元にスロー
	    }

	    return arrangementNumbers;  // 取得した手配番号を返す
	}
	
	// CSVに存在しない手配番号のデータを削除するメソッド
	private void deleteBacklogs(Set<String> numbersToDelete) throws SQLException {
	    if (numbersToDelete.isEmpty()) return;

	    String deleteQuery = "DELETE FROM backlogs WHERE arrangementNumber IN (";
	    String placeholders = String.join(",", Collections.nCopies(numbersToDelete.size(), "?"));
	    deleteQuery += placeholders + ")";

	    try (PreparedStatement ps = db.prepareStatement(deleteQuery)) {
	        int index = 1;
	        for (String arrangementNumber : numbersToDelete) {
	            ps.setString(index++, arrangementNumber);
	        }

	        int deletedRows = ps.executeUpdate();
	        System.out.println(deletedRows + " 行が削除されました。");
	    }
	}
	
    public boolean deleteOne() throws NamingException {
	    try {
	        this.getConnection();
             PreparedStatement ps = db.prepareStatement("DELETE FROM backlogs");
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("All records from 'backlogs' table have been deleted.");
                return true;
            } else {
                System.out.println("No records were found to delete.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Product> selectPartnumber(String arrangementnumber) throws SQLException, NamingException {
        List<Product> partnumber_checkList = new ArrayList<>();

        if (arrangementnumber == null || arrangementnumber.trim().isEmpty()) {
            throw new IllegalArgumentException("arrangementnumber が NULL または空です。");
        }

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            // データベース接続の取得
            this.getConnection();

            // SQL文の準備
            String sql = "SELECT id, ItemCode FROM backlogs WHERE arrangementNumber = ? AND ItemCode IS NOT NULL";
            ps = db.prepareStatement(sql);
            ps.setString(1, arrangementnumber);

            // クエリ実行
            rs = ps.executeQuery();

            // 結果を Backlog から取得し、Product に変換
            while (rs.next()) {
                // Product オブジェクトにセット
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setPartnumber_check(rs.getString("ItemCode")); // ✅ 正しく値を取得

                partnumber_checkList.add(product);
            }

        } finally {
            // リソースの解放
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            this.disconnect();
        }

        return partnumber_checkList;
    }
}