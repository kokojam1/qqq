package board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.dto.BoardDto;
import board.mapper.BoardMapper;

@Service 
// @Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(BoardDto board) throws Exception {
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		boardMapper.updateHitCount(boardIdx);
		// int i = 10 / 0;
		return boardMapper.selectBoardDetail(boardIdx);
	}

	@Override
	public void updateBoard(BoardDto boardDto) throws Exception {
		boardMapper.updateBoard(boardDto);		
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
	}
}
