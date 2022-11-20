package board.service;

import java.util.List;

import board.dto.MemberDto;
import board.vo.RequestVo;
import board.vo.ResponseVo;

public interface MemberService {
	public List<MemberDto> selectMemberList() throws Exception;
	public int insertMember(MemberDto member) throws Exception;
	public MemberDto selectMemberDetail(int memberSeq) throws Exception;
	public void updateMember(MemberDto memberDto) throws Exception;
	public void deleteMember(int memberSeq) throws Exception;
	public ResponseVo login(RequestVo requestVo) throws Exception;
}
