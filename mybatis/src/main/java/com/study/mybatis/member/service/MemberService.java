package com.study.mybatis.member.service;

import com.study.mybatis.member.vo.Member;

public interface MemberService {
	/*public abstract*/ int checkId(String userId);
	
			public int insertMember(Member m);
			
			public Member loginMember(Member m);
}
