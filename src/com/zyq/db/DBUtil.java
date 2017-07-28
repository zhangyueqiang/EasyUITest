package com.zyq.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {

	public static final String URL="jdbc:mysql://127.0.0.1:3306/ranklist?userUnicode=true&characterEncoding=utf8";
	public static final String USER="root";
	public static final String PASSWORD="123456";
	public static final String DRIVER="com.mysql.jdbc.Driver";
	
	public ResultSet rs;
	public Connection conn;
	public PreparedStatement ps;
	
	public Connection openDB(){
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL,USER,PASSWORD);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void closeDB(){
		if (rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs=null;
		}
		if (ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps=null;
		}
		if (conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn=null;
		}
	}
	
	public boolean modifyDB(String sql,Object...objects){		//可变数目
		conn=openDB();
		try {
			ps=conn.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {		//填充参数
				ps.setObject(i+1, objects[i]);
			}
			int cnt=ps.executeUpdate();
			if (cnt>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return false;
	}
	
	public List<Map<String, Object>> queryDB(String sql,Object...objects){
		Connection conn=openDB();
		try {
			ps=conn.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {		//填充参数
				ps.setObject(i+1, objects[i]);
			}
			rs=ps.executeQuery();
			List<Map<String,Object>> list=new ArrayList<>();
			while (rs.next()){
				Map map=new HashMap<>();
				for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
					String fieldName=rs.getMetaData().getColumnName(i+1);
					Object value=rs.getObject(fieldName);
					map.put(fieldName, value);
				}
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return null;
	}
	
}
