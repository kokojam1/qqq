package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.dto.BoardDto;
import board.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Controller
public class RestBoardController {

	@Autowired
	private BoardService boardService;
	
	// @GetMapping("/board")
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public ModelAndView openBoardList() throws Exception {
		ModelAndView mv = new ModelAndView("/board/restBoardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping(value="/board/write", method=RequestMethod.GET)
	public String openBoardWrite() throws Exception {
		return "/board/restBoardWrite";
	}
	
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String insertBoard(BoardDto board) throws Exception {
		boardService.insertBoard(board);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/board/{boardIdx}", method=RequestMethod.GET)
	public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/board/restBoardDetail");
			BoardDto boardDto = boardService.selectBoardDetail(boardIdx);
			mv.addObject("board", boardDto);
		
		return mv;
	}
	
	@RequestMapping(value="/board/{boardIdx}", method=RequestMethod.PUT)
	public String updateBoard(BoardDto boardDto) throws Exception {
		boardService.updateBoard(boardDto);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/board/{boardIdx}", method=RequestMethod.DELETE)
	public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		return "redirect:/board";
	}
}
