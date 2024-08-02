package com.study.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.study.mybatis.board.service.BoardService;
import com.study.mybatis.board.service.BoardServiceImpl;
import com.study.mybatis.board.vo.Board;
import com.study.mybatis.board.vo.Reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bnoParam = request.getParameter("boardNo");
	    int boardNo = 0;
	    
	    if (bnoParam != null && !bnoParam.isEmpty()) {
	        try {
	            boardNo = Integer.parseInt(bnoParam);
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMsg", "잘못된 게시글 번호입니다.");
	            request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
	            return; // 예외 발생 시 요청 처리 중단
	        }
	    } else {
	        request.setAttribute("errorMsg", "게시글 번호가 제공되지 않았습니다.");
	        request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
	        return; // 예외 발생 시 요청 처리 중단
	    }
		
		
		BoardService bService = new BoardServiceImpl();
		
		// 1. 조회수 증가
		int result = bService.increaseCount(boardNo);
		
		if(result > 0) {
			// 2. 상세조회
			Board b = bService.selectBoard(boardNo);

			// 3. 해당글에 달린 댓글 리스트 조회
			ArrayList<Reply> rlist = bService.selectReplyList(boardNo);
			
			request.setAttribute("b", b);
			request.setAttribute("rlist", rlist);
			
			
			request.getRequestDispatcher("WEB-INF/views/board/boardDetailView.jsp").forward(request, response);
			
			
		}else {
			request.setAttribute("errorMsg", "상세조회 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
