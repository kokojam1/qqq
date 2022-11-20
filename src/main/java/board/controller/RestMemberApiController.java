package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import board.dto.MemberDto;
import board.service.MemberService;
import board.vo.RequestVo;
import board.vo.ResponseVo;

@RestController
@RequestMapping("/api")
public class RestMemberApiController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public List<MemberDto> openMemberList() throws Exception {
		return memberService.selectMemberList();
	}

	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public ResponseEntity<String> insertMember(@RequestBody MemberDto board) throws Exception {
		int memberSeq = memberService.insertMember(board);
		if (memberSeq > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("등록성공");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록실패");
		}
	}

	@RequestMapping(value = "/member/{memberSeq}", method = RequestMethod.GET)
	public ResponseEntity<MemberDto> openMemberDetail(@PathVariable("memberSeq") int memberSeq) throws Exception {
		MemberDto boardDto = memberService.selectMemberDetail(memberSeq);
		if (boardDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.ok(boardDto);
		}
	}

	@RequestMapping(value = "/member/{memberSeq}", method = RequestMethod.PUT)
	public void updateMember(@PathVariable("memberSeq") int memberSeq, @RequestBody MemberDto boardDto) throws Exception {
		boardDto.setMemberSeq(memberSeq);
		memberService.updateMember(boardDto);
	}

	@RequestMapping(value = "/member/{memberSeq}", method = RequestMethod.DELETE)
	public void deleteMember(@PathVariable("memberSeq") int memberSeq) throws Exception {
		memberService.deleteMember(memberSeq);
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public ResponseEntity<ResponseVo> login(@RequestBody RequestVo requestVo) throws Exception {
		ResponseVo responseVo = memberService.login(requestVo);
		if (responseVo == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(responseVo);
		}		
	}
}
