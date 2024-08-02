package com.study.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.study.mybatis.board.service.BoardService;
import com.study.mybatis.board.service.BoardServiceImpl;
import com.study.mybatis.board.vo.Board;
import com.study.mybatis.common.template.Pagenation;
import com.study.mybatis.common.vo.PageInfo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 리스트 검색
//		// 내가 만든 버젼
//		  String keyField = request.getParameter("keyField"); 
//		 String keyword = "%"+request.getParameter("keyword")+"%"; 
//		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
//		  
//		  int searchTotalRecord = new BoardServiceImpl().searchTotalRecord(); 
//		  PageInfo  pi = Pagenation.getPageInfo(searchTotalRecord, nowPage, 5, 3);
//		  
//		  
//		  
//		  ArrayList<Board> board = new BoardServiceImpl().searchList(keyField, keyword,pi); 
//			request.setAttribute("pi",pi); 
//		 request.setAttribute("list", board);
//		  System.out.println("pi : "+pi); System.out.println("keyField : "+keyField);
//		  System.out.println("keyword : "+keyword);
//		  System.out.println("board : "+board);
//		  request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp")
//		  .forward(request, response);
		 
		
		// 교수님이 만든 버젼
		 String keyField = request.getParameter("keyField"); 
		 String keyword = request.getParameter("keyword"); 
		 int nowPage = Integer.parseInt(request.getParameter("nowPage"));
//		1. keyField와 keyword 2개를 모두 담을 bean객체를 만들어 넘기는 방법
//		2. keyField와 keyword HashMap<key, value>를 이용하는 방법
		 
		 HashMap<String, String> map = new HashMap();
		 map.put("keyField", keyField);
		 map.put("keyword", keyword);
		 
		 BoardService bService = new BoardServiceImpl();
		 int searchCount = bService.selectSearchCount(map);
		 
		 System.out.println("서치카운트 : "+searchCount);
		 
		 PageInfo  pi = Pagenation.getPageInfo(searchCount, nowPage, 5, 3);
		 System.out.println("컨트롤러 :: "+map.get("keyField"));
		  System.out.println("컨트롤러 :: "+map.get("keyword"));
		 System.out.println("컨트롤러 : "+ bService.selectSearchList(map, pi));
		 
		 ArrayList<Board> list = bService.selectSearchList(map, pi);
		 
		 
		 request.setAttribute("pi",pi);
		 request.setAttribute("list",list);
		 request.setAttribute("keyField",keyField);
		 request.setAttribute("keyword",keyword);
		 
		 request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp").forward(request, response);
		 
		 
		
	}

}
