package com.study.mybatis.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
 
public class MemberEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		어디로 가시오 라는 구문만 넣어주면됨
		request.getRequestDispatcher("WEB-INF/views/member/memberEnrollForm.jsp").forward(request, response);
	}

}
