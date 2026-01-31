package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Partnumbermaster;

public class PartnumbermasterDAO {
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
	public List<Partnumbermaster> findAll(){
		List<Partnumbermaster> partnumbermasterList=new ArrayList<>();
		try {
			this.getConnection();
			ps=db.prepareStatement("SELECT * FROM partnumbermaster ORDER BY id DESC");
			rs=ps.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				String partnumber=rs.getString("partnumber");
				String partname=rs.getString("partname");
				String sparepartnumber1=rs.getString("sparepartnumber1");
				String sparepartnumber2=rs.getString("sparepartnumber2");
				String sparepartnumber3=rs.getString("sparepartnumber3");
				String takenumber=rs.getString("takenumber");
	
				Partnumbermaster partnumbermaster=new Partnumbermaster(id,partnumber,partname,sparepartnumber1,sparepartnumber2,sparepartnumber3,takenumber);
				partnumbermasterList.add(partnumbermaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
		return partnumbermasterList;
	}
	public boolean insertOne(Partnumbermaster partnumbermaster){
		try {
			this.getConnection();
			ps=db.prepareStatement("INSERT INTO partnumbermaster(partnumber,partname,sparepartnumber1,sparepartnumber2,sparepartnumber3,takenumber)"
		            + "VALUES (?, ?, ?, ?, ?, ?)");

			ps.setString(1, partnumbermaster.getPartnumber());
			ps.setString(2, partnumbermaster.getPartname());
			ps.setString(3, partnumbermaster.getSparepartnumber1() != null ? partnumbermaster.getSparepartnumber1() : "");
			ps.setString(4, partnumbermaster.getSparepartnumber2() != null ? partnumbermaster.getSparepartnumber2() : "");
			ps.setString(5, partnumbermaster.getSparepartnumber3() != null ? partnumbermaster.getSparepartnumber3() : "");
			ps.setString(6, partnumbermaster.getTakenumber());
			
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
	public Partnumbermaster findOne(int id){
		Partnumbermaster partnumbermaster=null;
		try{
			this.getConnection();
			ps=db.prepareStatement("SELECT * FROM partnumbermaster WHERE id=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				String partnumber=rs.getString("partnumber");
				String partname=rs.getString("partname");
				String sparepartnumber1=rs.getString("sparepartnumber1");
				String sparepartnumber2=rs.getString("sparepartnumber2");
				String sparepartnumber3=rs.getString("sparepartnumber3");
				String takenumber=rs.getString("takenumber");

				partnumbermaster=new Partnumbermaster(id,partnumber,partname,sparepartnumber1,sparepartnumber2,sparepartnumber3,takenumber);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally{
			this.disconnect();
		}
		return partnumbermaster;
	}
	public boolean updateOne(Partnumbermaster partnumbermaster) {
	    try {
	        this.getConnection();
	        ps = db.prepareStatement("UPDATE partnumbermaster SET "
	                + "partnumber=?, partname=?, sparepartnumber1=?, sparepartnumber2=?, sparepartnumber3=?, takenumber=? "
	                + "WHERE id=?");

			ps.setString(1, partnumbermaster.getPartnumber());
			ps.setString(2, partnumbermaster.getPartname());
			ps.setString(3, partnumbermaster.getSparepartnumber1());
			ps.setString(4, partnumbermaster.getSparepartnumber2());
			ps.setString(5, partnumbermaster.getSparepartnumber3());
			ps.setString(6, partnumbermaster.getTakenumber());
	        
	        ps.setInt(7, partnumbermaster.getId());
	        int result = ps.executeUpdate();
	        return result == 1;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (NamingException e) {
	        e.printStackTrace();
	    } finally {
	        this.disconnect();
	    }
	    return false;
	}
	
	public List<Partnumbermaster> searchOne(String search) {
	    List<Partnumbermaster> partnumbermasterList = new ArrayList<>();
	    try {
	        this.getConnection();
	        ps = db.prepareStatement("SELECT * FROM partnumbermaster WHERE "
	                + "partnumber LIKE ? OR partname LIKE ? OR sparepartnumber1 LIKE ? OR sparepartnumber2 LIKE ? OR sparepartnumber3 LIKE ? OR takenumber LIKE ?");

	        String searchPattern = "%" + search + "%";

	        // プリペアードステートメントのパラメータを設定
	        for (int i = 1; i <= 6; i++) {
	            ps.setString(i, searchPattern);
	        }

	        rs = ps.executeQuery();
	        while (rs.next()) {
	            // Retrieve all necessary fields from the result set
	            int id = rs.getInt("id");
				String partnumber=rs.getString("partnumber");
				String partname=rs.getString("partname");
				String sparepartnumber1=rs.getString("sparepartnumber1");
				String sparepartnumber2=rs.getString("sparepartnumber2");
				String sparepartnumber3=rs.getString("sparepartnumber3");
				String takenumber=rs.getString("takenumber");

	            // Create Product object with retrieved values
				Partnumbermaster partnumbermaster = new Partnumbermaster(id,partnumber,partname,sparepartnumber1,sparepartnumber2,sparepartnumber3,takenumber);
	            partnumbermasterList.add(partnumbermaster);
	        }
	    } catch (SQLException | NamingException e) {
	        e.printStackTrace();
	    } finally {
	        this.disconnect();
	    }
	    return partnumbermasterList;
	}
	
	public void downloadAll() throws IOException {
	    PrintWriter csvWriter = null;

	    try {
	        // データベース接続の取得
	        this.getConnection();
	        
	        // SQL クエリの準備
	        ps = db.prepareStatement("SELECT * FROM partnumbermaster");
	        rs = ps.executeQuery();
	        
	        // 現在の日時を取得
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentDateAndTime = sdf.format(new Date());

            // CSV のファイル名
            String fileName = "partnumbermasterlist_DL " + currentDateAndTime + ".csv";

	        // 適切なパスを選んでください
            String filePath = "/var/samba/Data_Transfer/DailyMoldingWorkG_tomcat/Download_Files/"+ fileName;
            
	        //<!-- UM425QA-KIR915W -->
	        //<!-- DESKTOP-KBUH9GC -->
	        //<!-- String filePath = "E:\\Program Files/"+ fileName; -->

            //<!-- Raspberry Pi(192.168.10.103 ) -->
            //<!-- Raspberry Pi(192.168.10.122 ) -->
            //<!-- Raspberry Pi(192.168.10.118 ) -->
	        //<!-- String filePath = "/var/samba/Data_Transfer/DailyMoldingWorkG_tomcat/Download_Files/"+ fileName; -->
	        
	        // CSV Writer の準備
	        csvWriter = new PrintWriter(filePath, "Shift-JIS");

	        // ヘッダー行
	        csvWriter.append("品番,品名,予備品番1,予備品番2,予備品番3,取り数");
	        csvWriter.append("\n");

	        // データ行の書き出し
	        while (rs.next()) {
	            csvWriter.append(rs.getString("partnumber") + ",");
	            csvWriter.append(rs.getString("partname") + ",");
	            csvWriter.append(rs.getString("sparepartnumber1") + ",");
	            csvWriter.append(rs.getString("sparepartnumber2") + ",");
	            csvWriter.append(rs.getString("sparepartnumber3") + ",");
	            csvWriter.append(rs.getString("takenumber") + ",");
	            csvWriter.append("\n");
	        }

	        // ファイルへの書き込み
	        csvWriter.flush();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (NamingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        // リソースのクリーンアップ
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (db != null) db.close();
	            if (csvWriter != null) csvWriter.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 }
	
	public boolean deleteOne(int id){

		try{
			this.getConnection();
			ps=db.prepareStatement("DELETE FROM partnumbermaster WHERE id=?");
			ps.setInt(1, id);
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
	
	public List<Partnumbermaster> selectPartNumber() {
	    List<Partnumbermaster> partnumberList = new ArrayList<>();
	    try {
	        // データベース接続の取得
	        this.getConnection(); // 接続の確立

	        // partnumbermaster から partnumber、id、sparepartnumber1、sparepartnumber2、sparepartnumber3 を取得
	        ps = db.prepareStatement("SELECT id, partnumber FROM partnumbermaster");
	        rs = ps.executeQuery();

	        // 結果をArrayListに格納
	        while (rs.next()) {
	            // Partnumbermasterオブジェクトを生成
	            Partnumbermaster partnumbermaster = new Partnumbermaster();
	            // 各フィールドを設定
	            partnumbermaster.setId(rs.getInt("id"));
	            partnumbermaster.setPartnumber(rs.getString("partnumber"));
	            // リストに追加
	            partnumberList.add(partnumbermaster);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // エラーログの出力
	    } catch (NamingException e) {
	        e.printStackTrace(); // エラーログの出力
	    } finally {
	        this.disconnect(); // 接続のクローズ
	    }
	    return partnumberList;
	}
	
	public List<Partnumbermaster> selectPartName(String partnumber) {
	    List<Partnumbermaster> partnameList = new ArrayList<>();
	    try {
	        // データベース接続の取得
	        this.getConnection(); // 接続の確立

	        // partnumbermaster から partnumber、id、sparepartnumber1、sparepartnumber2、sparepartnumber3 を取得
	        ps = db.prepareStatement("SELECT id, partname FROM partnumbermaster WHERE partnumber = ?");
	        ps.setString(1, partnumber);
	        rs = ps.executeQuery();

	        // 結果をArrayListに格納
	        while (rs.next()) {
	            // Partnumbermasterオブジェクトを生成
	            Partnumbermaster partnumbermaster = new Partnumbermaster();
	            // 各フィールドを設定
	            partnumbermaster.setId(rs.getInt("id"));
	            partnumbermaster.setPartname(rs.getString("partname"));
	            // リストに追加
	            partnameList.add(partnumbermaster);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // エラーログの出力
	    } catch (NamingException e) {
	        e.printStackTrace(); // エラーログの出力
	    } finally {
	        this.disconnect(); // 接続のクローズ
	    }
	    return partnameList;
	}
	
	public List<String> selectSparePartNumber(String partnumber,String partname) {
	    List<String> sparePartNumbers = new ArrayList<>();
	    try {
	        // データベース接続の取得
	        this.getConnection();
	        ps = db.prepareStatement("SELECT takenumber, sparepartnumber1, sparepartnumber2, sparepartnumber3 FROM partnumbermaster WHERE partnumber = ? AND partname = ?");
	        ps.setString(1, partnumber); // partnumber パラメータを設定
	        ps.setString(2, partname); // partnumber パラメータを設定
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            // 各カラムの値を取得してリストに追加
	            String spare1 = rs.getString("sparepartnumber1");
	            String spare2 = rs.getString("sparepartnumber2");
	            String spare3 = rs.getString("sparepartnumber3");
	            String takenumber = rs.getString("takenumber");
	            
	            System.out.println("sparepartnumber1: " + spare1);
	            System.out.println("sparepartnumber2: " + spare2);
	            System.out.println("sparepartnumber3: " + spare3);
	            System.out.println("takenumber: " + takenumber);

	            sparePartNumbers.add(spare1 != null ? spare1 : "");
	            sparePartNumbers.add(spare2 != null ? spare2 : "");
	            sparePartNumbers.add(spare3 != null ? spare3 : "");
	            sparePartNumbers.add(takenumber != null ? takenumber : "");

	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (NamingException e) {
	        e.printStackTrace();
	    } finally {
	        if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
	        this.disconnect();
	    }
	    return sparePartNumbers;
	}

}