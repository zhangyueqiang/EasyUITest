package com.zyq.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zyq.db.DBUtil;

/**
 * Servlet implementation class CountryServlet
 */
@WebServlet("/CountryServlet")
public class CountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			int page=Integer.parseInt(request.getParameter("page"));
			int rows=Integer.parseInt(request.getParameter("rows"));
			
			String sql="select * from contestuser limit ?,? ";		//传入当前页数、显示数量
			
			String tsql="select count(*) from contestuser";		
			
			List<Map<String,Object>> temp=new DBUtil().queryDB(tsql);
			
			List<Map<String,Object>> result=new DBUtil().queryDB(sql,(page-1)*rows,rows);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("total", temp.get(0).get("count(*)"));
		
			map.put("rows", result);
			Gson gson=new Gson();
			//json(gson fastjson)   xml(xstream)
			String json=gson.toJson(map);	//转换成json
			//gson.fromJson(json, classOfT)  反射原理  映射原理
			
			/*String sql="select * from contestuser";
			List<Map<String,Object>> result=new DBUtil().queryDB(sql);
			Gson gson=new Gson();
			String json=gson.toJson(result);*/
		
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(json);
			
			
	}

}
