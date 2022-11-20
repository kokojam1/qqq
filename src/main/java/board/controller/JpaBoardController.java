package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import board.entity.BoardEntity;
import board.service.JpaService;

@Controller
@RequestMapping("/jpa")
public class JpaBoardController {

	@Autowired
	private JpaService jpaService;

	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public ModelAndView openBoardList() throws Exception {
		ModelAndView mv = new ModelAndView("/board/jpaBoardList");
		List<BoardEntity> list = jpaService.selectBoardList();
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String openBoardWrite() throws Exception {
		return "/board/jpaBoardWrite";
	}

	// 등록(INSERT)
	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String insertBoard(BoardEntity boardEntity) throws Exception {
		jpaService.saveBoard(boardEntity);
		return "redirect:/jpa/board";
	}

	@RequestMapping(value = "/board/{boardIdx}", method = RequestMethod.GET)
	public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/board/jpaBoardDetail");
		BoardEntity boardEntity = jpaService.selectBoardDetail(boardIdx);
		mv.addObject("board", boardEntity);
		return mv;
	}

	// 수정(UPDATE)	==> JPA의 save 메서드는 insert 기능과 update 기능을 모두 수행 
	@RequestMapping(value = "/board/{boardIdx}", method = RequestMethod.PUT)
	public String updateBoard(BoardEntity boardEntity) throws Exception {
		jpaService.saveBoard(boardEntity);
		return "redirect:/jpa/board";
	}

	@RequestMapping(value = "/board/{boardIdx}", method = RequestMethod.DELETE)
	public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
		jpaService.deleteBoard(boardIdx);
		return "redirect:/jpa/board";
	}
	
	@RequestMapping(value = "/board/title/{title}", method = RequestMethod.GET)
	public ModelAndView selectBoardListByTitle(@PathVariable("title") String title) throws Exception {
		ModelAndView mv = new ModelAndView("/board/jpaBoardList");
		List<BoardEntity> list = jpaService.selectBoardListByTitle(title);
		mv.addObject("list", list);
		return mv;
	}
	
}
