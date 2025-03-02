package com.study.mybatis.member.service;

import org.apache.ibatis.session.SqlSession;

import com.study.mybatis.common.template.Template;
import com.study.mybatis.member.dao.MemberDao;
import com.study.mybatis.member.vo.Member;

public class MemberServiceImpl implements MemberService{
	private MemberDao mDao = new MemberDao();
	
	@Override
	public int checkId(String userId) {
		SqlSession sqlSession = Template.getSqlSession();
		int checkid = mDao.checkId(sqlSession, userId);
		sqlSession.close();
		return checkid;
	}
	
	@Override
	public int insertMember(Member m) {
			SqlSession sqlSession = Template.getSqlSession();
			int result = mDao.insertMember(sqlSession,m);
			if(result > 0 ) {

				sqlSession.commit();
			}	
			sqlSession.close();
			return result;
	}

	@Override
	public Member loginMember(Member m) {
		SqlSession sqlSession = Template.getSqlSession(); //세션 얻어오기
		Member loginUser = mDao.loginMember(sqlSession, m);
		sqlSession.close();	//로그인작업 수행후 세션 종료
		return loginUser;
	}
	
	

}
