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
 
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyField = request.getParameter("keyField");
		String keyword = request.getParameter("keyword");
		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		
		int totalRecord = new BoardServiceImpl().selectTotalRecord();
		PageInfo pi = Pagenation.getPageInfo(totalRecord, nowPage, 5, 3);
		 
		ArrayList<Board> board = new BoardServiceImpl().searchList(keyField, keyword, pi);
	}

}
