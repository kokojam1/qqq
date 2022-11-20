package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.MemberDto;

@Mapper
public interface MemberMapper {
	List<MemberDto> selectMemberList() throws Exception;
	int insertMember(MemberDto member) throws Exception;
	MemberDto selectMemberDetail(int memberSeq) throws Exception;
	int updateMember(MemberDto memberDto) throws Exception;
	int deleteMember(int memberSeq) throws Exception;
	MemberDto login(MemberDto memberDto) throws Exception;
}
