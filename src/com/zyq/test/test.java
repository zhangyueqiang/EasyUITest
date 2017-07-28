package com.zyq.test;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.zyq.db.DBUtil;

public class test {

	
	public static void main(String[] args) {
		String sql="select * from contestuser";
		List<Map<String,Object>> result=new DBUtil().queryDB(sql);
		Gson gson=new Gson();
		String json=gson.toJson(result);
		System.out.println(json);
	}
}
