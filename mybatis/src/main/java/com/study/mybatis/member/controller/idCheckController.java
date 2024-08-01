package com.study.mybatis.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.study.mybatis.member.service.MemberServiceImpl;

public class idCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("id");
		System.out.println("컨트롤러 통과");
		int result = new MemberServiceImpl().checkId(userId);
		if(result > 0) {
			response.getWriter().print("idN");
		}else {
			response.getWriter().print("idY");
		}
			
	}

}
