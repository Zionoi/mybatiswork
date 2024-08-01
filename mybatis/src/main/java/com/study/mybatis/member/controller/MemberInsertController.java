package com.study.mybatis.member.controller;

import java.io.IOException;

import com.study.mybatis.member.service.MemberServiceImpl;
import com.study.mybatis.member.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

 
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 
			/*
			 * Member m = new Member();
			 * 
			 * m.setUserId(request.getParameter("userId"));
			 * m.setUserPwd(request.getParameter("userPwd"));
			 * m.setUserName(request.getParameter("userName"));
			 * m.setEmail(request.getParameter("email"));
			 * m.setBirthday(request.getParameter("birthday"));
			 * m.setGender(request.getParameter("gender"));
			 * m.setPhone(request.getParameter("phone"));
			 * m.setAddress(request.getParameter("address"));
			 */
		   //생성자가 있어야 함
		 Member m = new Member(request.getParameter("userId"),
				 			   request.getParameter("userPwd"),
				 			   request.getParameter("userName"),
				 			   request.getParameter("email"),
				 			   request.getParameter("birthday"),
				 			   request.getParameter("gender"),
				 			   request.getParameter("phone"),
				 			   request.getParameter("address"));
		 
		 int result = new MemberServiceImpl().insertMember(m);
		 
		 if(result > 0) {
			response.sendRedirect(request.getContextPath());
		 }else {
			//request.getRequestDispatcher("WEB-INF/views/member/memberEnrollForm.jsp").forward(request, response);
			 request.setAttribute("errorMsg","회원가입 실패"); // 페이지에 키 벨류값으로 받음 errorMsg로 호출하면 사라짐
			 request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		 }
		 
	}

}
