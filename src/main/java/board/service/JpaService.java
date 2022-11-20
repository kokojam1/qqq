package board.service;

import java.util.List;

import board.entity.BoardEntity;

public interface JpaService {
	public List<BoardEntity> selectBoardList() throws Exception;
	public void saveBoard(BoardEntity boardEntity) throws Exception;
	public BoardEntity selectBoardDetail(int boardIdx) throws Exception;
	public void deleteBoard(int boardIdx) throws Exception;
	public List<BoardEntity> selectBoardListByTitle(String title) throws Exception;
}
