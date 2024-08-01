package com.study.mybatis.board.service;

import java.util.ArrayList;

import com.study.mybatis.board.vo.Board;
import com.study.mybatis.common.vo.PageInfo;

public interface BoardService {
	public int selectTotalRecord();
	ArrayList<Board> selectList(PageInfo pi);
	public ArrayList<Board> searchList(String keyField, String keyword, int nowPage, PageInfo pi);
}
