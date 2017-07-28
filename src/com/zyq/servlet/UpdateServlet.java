package com.zyq.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zyq.db.DBUtil;

/**
 * Servlet implementation class UpdataServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		String email=request.getParameter("email");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		
		System.out.println(email+"++++\n"+userName+"+++++\n"+password);
		
		String sql="insert into contestuser(penalty,userName,rank,cid,solved) values(?,?,?,?,?)";
		Map<String,String> msg=new HashMap<String, String>();
		if (new DBUtil().modifyDB(sql, email,userName,password,2,4)==true){
			msg.put("status","success");
			msg.put("message","hahahha");
		}else{
			msg.put("status", "error");
			msg.put("message","hahahha");
		}
		
		
		Gson gson=new Gson();
		String json=gson.toJson(msg);
		response.getWriter().write(json);
		
	}

}
