package com.study.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.study.mybatis.board.service.BoardServiceImpl;
import com.study.mybatis.board.vo.Board;
import com.study.mybatis.common.template.Pagenation;
import com.study.mybatis.common.vo.PageInfo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 /*
		  int totalRecord = 0;   // db에서 한 행을 1record라고 부르기때문에 borad테이블의 전체 행의 수를 뜻한다
	      int numPerPage = 10;   // 한 페이지에 보여줄 record의 수
	      int pagePerBlock = 5;   // 블록당 페이지 수[1][2][3][4][5]
	      int nowPage =1;         // 현재 해당하는 페이지
	      
	      // 위의 4개를 가지고 계산
	      int totalPage = 0;      // (전체 레코드수/한페이지에 보여줄 레코드 수)를 올림함 전체 페이지의 수
	      int startPage =0;      // 페이징바의 시작 수
	      int endPage; 			 //페이징바의 끝수
	      
	      int nowBlock =1;      // 현재 해당하는 블록
	      
	      int start =0;         // border테이블의 select시 시작번호(한 페이지에 보여줄 record의 첫번째 primary key번호)
	      int end =0;            // 한 페이지에 보여줄 레코드의 갯수(보통은 10개, 맨 마지막 페이지는 totalRecord%numPerPage개)
	      int listSize =0;      // 현재 읽어온 게시물의 수
	  */
		int totalRecord = new BoardServiceImpl().selectTotalRecord();
		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		PageInfo pi = Pagenation.getPageInfo(totalRecord, nowPage, 5, 3);
		
		ArrayList<Board> list = new BoardServiceImpl().selectList(pi);
		request.setAttribute("pi",pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp")
			   .forward(request, response);
		
	}

}
