package board.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.dto.MemberDto;
import board.mapper.MemberMapper;
import board.vo.RequestVo;
import board.vo.ResponseVo;

@Service 
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public List<MemberDto> selectMemberList() throws Exception {
		return memberMapper.selectMemberList();
	}

	@Override
	public int insertMember(MemberDto member) throws Exception {
		memberMapper.insertMember(member);
		return member.getMemberSeq();
	}

	@Override
	public MemberDto selectMemberDetail(int memberSeq) throws Exception {
		return memberMapper.selectMemberDetail(memberSeq);
	}

	@Override
	public void updateMember(MemberDto memberDto) throws Exception {
		int count = memberMapper.updateMember(memberDto);
		System.out.println("***************** " + count);
	}

	@Override
	public void deleteMember(int memberSeq) throws Exception {
		int count = memberMapper.deleteMember(memberSeq);
		System.out.println("***************** " + count);
	}

	@Override
	public ResponseVo login(RequestVo requestVo) throws Exception {
				
//		MemberDto memberDto = new MemberDto();
//		memberDto.setMemberEmail(requestVo.getMemberEmail());
//		memberDto.setMemberPass(requestVo.getMemberPass());
		ModelMapper modelMapper = new ModelMapper();
		MemberDto memberDto = modelMapper.map(requestVo, MemberDto.class);
				
		MemberDto resultDto = memberMapper.login(memberDto);
		if (resultDto == null) 
			return null;
		
//		ResponseVo responseVo = new ResponseVo();
//		responseVo.setMemberEmail(resultDto.getMemberEmail());
//		responseVo.setMemberName(resultDto.getMemberName());
//		responseVo.setMemberSeq(resultDto.getMemberSeq());
		ResponseVo responseVo = modelMapper.map(resultDto, ResponseVo.class);
		return responseVo;
	}
}
