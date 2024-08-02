package com.study.mybatis.board.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.study.mybatis.board.vo.Board;
import com.study.mybatis.board.vo.Reply;
import com.study.mybatis.common.vo.PageInfo;

public class BoardDao {

	public int selectTotalRecord(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.selectTotalRecord");
	}

	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi) {
		// 마이바티스에서 페이징처리를 위해서 RowBounds라는 클래스 제공 (== DB에서 RowNum)
		// offset : 몇 개의 게시글(레코드)을 건너뛰고 조회할건지에 대한 값
		
		/*
		 	ex) numPerPage : 5
		 							offset(건너뛸숫자)		limit(조회할숫자)
		 	nowPage : 1		1~5		  0						5
		 	nowPage : 2 	6~10	  5						5
		 	nowPage : 3 	11~15	  10					5
		 	
		 */
		int limit = pi.getNumPerPage();
		int offset = (pi.getNowPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
//					여러개일땐 selectList무조건 써야한다
//					rowBounds는 db의 rownum같은것 여기서 안쓸거면 쿼리문에서 rownum작업 해줘야함
		return (ArrayList)sqlSession.selectList("boardMapper.selectList", null, rowBounds);
		
	}

//	public ArrayList<Board> searchList(SqlSession sqlSession, String keyField, String keyword, PageInfo pi) {
//		int limit = pi.getNumPerPage();
//		int offset = (1-1)*limit;
//		RowBounds rowBounds = new RowBounds(offset, limit);
//		System.out.println("sqlSession.selectList(\"boardMapper.searchList\", null, rowBounds) : "+sqlSession.selectList("boardMapper.searchList", null, rowBounds));
//		return (ArrayList)sqlSession.selectList("boardMapper.searchList", null, rowBounds);
//	}
	public ArrayList<Board> searchList(SqlSession sqlSession, String keyField, String keyword, PageInfo pi) {
		int limit = pi.getNumPerPage();
		int offset = (1-1)*limit; // 현재 페이지에 맞게 수정

		RowBounds rowBounds = new RowBounds(offset, limit);

		HashMap<String, String> params = new HashMap<>();
	    params.put("keyField", keyField);
	    params.put("keyword", keyword);

	    System.out.println("sqlSession.selectList(\"boardMapper.searchList\", params, rowBounds) : " + sqlSession.selectList("boardMapper.searchList", params, rowBounds));

	    return (ArrayList) sqlSession.selectList("boardMapper.searchList", params, rowBounds);
	}

	public int increaseCount(SqlSession sqlSession, int boardNo) {
		
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}

	public Board selectBoard(SqlSession sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectboard", boardNo);
	}

	public ArrayList<Reply> selectReplyList(SqlSession sqlSession, int boardNo) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList", boardNo);
	}
	
	//교수님이 만든 검색된 게시글 갯수
	public int selectSearchCount(SqlSession sqlSession, HashMap<String, String> map) {
		System.out.println("검색 카운트 메소드 : "+map.get("keyField"));
		System.out.println("검색 카운트 메소드 : "+map.get("keyword"));
		return sqlSession.selectOne("boardMapper.selectSearchCount",map);
	}
	
	
	//교수님이 만든 게시글 검색 매소드
	public ArrayList<Board> selectSearchList(SqlSession sqlSession, HashMap<String, String> map, PageInfo pi) {
		int limit = pi.getNumPerPage();
		int offset = (pi.getNowPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		  System.out.println("검색 메소드 : "+map.get("keyField"));
		  System.out.println("검색 메소드 : "+map.get("keyword"));
//					여러개일땐 selectList무조건 써야한다
//					rowBounds는 db의 rownum같은것 여기서 안쓸거면 쿼리문에서 rownum작업 해줘야함
		return (ArrayList)sqlSession.selectList("boardMapper.selectSearchList", map, rowBounds);
		
	}

	public int insertReply(SqlSession sqlSession, Reply re) {
		return sqlSession.insert("boardMapper.insertReply", re);
	}

}
