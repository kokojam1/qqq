package board.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import board.entity.MemberEntity;

public interface JpaMemberService extends UserDetailsService {
	public List<MemberEntity> selectMemberList() throws Exception;
	public MemberEntity saveMember(MemberEntity memberEntity) throws Exception;
	public MemberEntity selectMemberDetail(int memberSeq) throws Exception;
	public void deleteMember(int memberSeq) throws Exception;
	public List<MemberEntity> selectMemberListByName(String memberName) throws Exception;
	public MemberEntity getMemberDetailByEmail(String username);
}
