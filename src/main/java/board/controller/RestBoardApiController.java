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

import board.dto.BoardDto;
import board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api")
public class RestBoardApiController {

	@Autowired
	private BoardService boardService;

	@ApiOperation(value = "목록 조회", notes = "등록된 게시물 목록을 조회")
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public List<BoardDto> openBoardList() throws Exception {
		return boardService.selectBoardList();
	}

	@ApiOperation(value = "게시물 등록", notes = "게시물 제목과 내용을 저장")
	@RequestMapping(value = "/board", method = RequestMethod.POST)
	public void insertBoard(
			@Parameter(description = "게시물 정보", required = true, example = "{ title: 제목, contents: 내용 }") @RequestBody BoardDto board)
			throws Exception {
		boardService.insertBoard(board);
	}

	@ApiOperation(value = "게시물 상세 조회", notes = "등록된 게시물 상세 정보를 조회")
	@RequestMapping(value = "/board/{boardIdx}", method = RequestMethod.GET)
	public ResponseEntity<BoardDto> openBoardDetail(
			@Parameter(description = "게시물 번호", required = true, example = "1") @PathVariable("boardIdx") int boardIdx)
			throws Exception {
		BoardDto boardDto = boardService.selectBoardDetail(boardIdx);
		if (boardDto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			// return ResponseEntity.status(HttpStatus.OK).body(boardDto);
			return ResponseEntity.ok(boardDto);
		}
	}

	@RequestMapping(value = "/board/{boardIdx}", method = RequestMethod.PUT)
	public void updateBoard(@PathVariable("boardIdx") int boardIdx, @RequestBody BoardDto boardDto) throws Exception {
		boardDto.setBoardIdx(boardIdx);
		boardService.updateBoard(boardDto);
	}

	@RequestMapping(value = "/board/{boardIdx}", method = RequestMethod.DELETE)
	public void deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
	}
}
