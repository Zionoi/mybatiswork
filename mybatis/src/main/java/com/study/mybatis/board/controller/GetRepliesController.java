package com.study.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson; // JSON 변환을 위한 라이브러리
import com.study.mybatis.board.service.BoardService;
import com.study.mybatis.board.service.BoardServiceImpl;
import com.study.mybatis.board.vo.Reply;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetRepliesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));

        BoardService bService = new BoardServiceImpl();
        ArrayList<Reply> rlist = bService.selectReplyList(boardNo);

        // JSON 변환을 위해 Gson 라이브러리 사용
        Gson gson = new Gson();
        String json = gson.toJson(rlist);

        // 응답의 MIME 타입을 설정하고 JSON을 응답 본문에 작성
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
