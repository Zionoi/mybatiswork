package com.study.mybatis.board.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.study.mybatis.board.vo.Board;
import com.study.mybatis.board.vo.Reply;
import com.study.mybatis.common.vo.PageInfo;

public interface BoardService {
	// 게시판 리스트 조회
	public int selectTotalRecord();
	ArrayList<Board> selectList(PageInfo pi);
	
	// 내가만든 게시글 검색
	public ArrayList<Board> searchList(String keyField, String keyword, PageInfo pi);
	
	// 교수님이 만든 게시글 검색
	public int selectSearchCount(HashMap<String, String> map);

	// 상세조회
	public int increaseCount(int boardNo);
	public Board selectBoard(int boardNo);
	public ArrayList<Reply> selectReplyList(int boardNo);
	public ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi);
	public int insertReply(Reply re);
	
	
}
