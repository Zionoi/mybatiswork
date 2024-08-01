package com.study.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	public static SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		
		// SqlSession을 생성하기 위해 =>  SqpSessionFactory 필요
		// SqlSessionFactory => SqlSessionFactoryBuilder 필요
		
		String resource = "/mybatis-config.xml";
		
		//xml파일을 읽어갖고올때 InputStream 사용
		try {
			InputStream stream = Resources.getResourceAsStream(resource);
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession(false);
//			openSession(false) : 자동 커밋 여부
//								 기본값 false, false일때 DB 자동 커밋 안하겠다는뜻
//								 true => 자동커밋
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return sqlSession;
	}
}
