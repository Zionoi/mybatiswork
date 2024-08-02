package com.study.mybatis.board.controller;

import java.io.IOException;

import com.study.mybatis.board.service.BoardService;
import com.study.mybatis.board.service.BoardServiceImpl;
import com.study.mybatis.board.vo.Reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
public class InsertReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Reply re = new Reply();
		re.setReplyWriter(request.getParameter("replyWriter"));
		re.setRefBno(Integer.parseInt(request.getParameter("boardNo")));
		re.setReplyContent(request.getParameter("inputReply"));
		
		System.out.println("댓글작성자 : "+re.getReplyWriter());
		System.out.println("댓글달린 게시글 번호 : "+request.getParameter("boardNo"));
		System.out.println("댓글내용 : "+re.getReplyContent());
		
		BoardService bService = new BoardServiceImpl();
		int result = bService.insertReply(re);
		System.out.println("컨트롤러 result : "+ result);
		if(result > 0) {
			response.getWriter().print("댓글작성 완료");
		}else {
			response.getWriter().print("댓글작성 실패");
			
		}
		
	}

}
