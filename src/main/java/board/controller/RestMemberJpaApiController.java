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
import org.springframework.web.servlet.ModelAndView;

import board.dto.MemberDto;
import board.entity.BoardEntity;
import board.entity.MemberEntity;
import board.service.JpaMemberService;
import board.vo.RequestVo;
import board.vo.ResponseVo;

@RestController
@RequestMapping("/api/jpa")
public class RestMemberJpaApiController {

	@Autowired
	private JpaMemberService jpaMemberService;

	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public List<MemberEntity> openMemberList() throws Exception {
		return jpaMemberService.selectMemberList();
	}

	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public ResponseEntity<MemberEntity> insertMember(@RequestBody MemberEntity memberEntity) throws Exception {
		MemberEntity member = jpaMemberService.saveMember(memberEntity);
		if (member == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(member);
		}
	}

	@RequestMapping(value = "/member/{memberSeq}", method = RequestMethod.GET)
	public ResponseEntity<MemberEntity> openMemberDetail(@PathVariable("memberSeq") int memberSeq) throws Exception {
		MemberEntity memberEntity = jpaMemberService.selectMemberDetail(memberSeq);
		if (memberEntity == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return ResponseEntity.ok(memberEntity);
		}
	}

	@RequestMapping(value = "/member/{memberSeq}", method = RequestMethod.PUT)
	public ResponseEntity<MemberEntity> updateMember(@PathVariable("memberSeq") int memberSeq, @RequestBody MemberEntity memberEntity) throws Exception {
		memberEntity.setMemberSeq(memberSeq);
		MemberEntity member = jpaMemberService.saveMember(memberEntity);
		if (member == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(member);
		}
	}

	@RequestMapping(value = "/member/{memberSeq}", method = RequestMethod.DELETE)
	public void deleteMember(@PathVariable("memberSeq") int memberSeq) throws Exception {
		jpaMemberService.deleteMember(memberSeq);
	}

	@RequestMapping(value = "/member/name/{name}", method = RequestMethod.GET)
	public List<MemberEntity> selectMemberListByName(@PathVariable("name") String name) throws Exception {
		return jpaMemberService.selectMemberListByName(name);
	}
}
