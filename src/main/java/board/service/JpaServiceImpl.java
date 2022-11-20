package board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.entity.BoardEntity;
import board.repository.JpaBoardRepository;

@Service 
public class JpaServiceImpl implements JpaService {

	@Autowired
	JpaBoardRepository jpaBoardRepository;

	@Override
	public List<BoardEntity> selectBoardList() throws Exception {
		return (List<BoardEntity>) jpaBoardRepository.findAll();
	}

	@Override
	public void saveBoard(BoardEntity boardEntity) throws Exception {
		boardEntity.setCreatorId("admin");
		jpaBoardRepository.save(boardEntity);		
	}

	@Override
	public BoardEntity selectBoardDetail(int boardIdx) throws Exception {
		Optional<BoardEntity> optional = jpaBoardRepository.findById(boardIdx);
		if (optional.isPresent()) {
			BoardEntity board = optional.get();
			board.setHitCnt(board.getHitCnt() + 1);
			jpaBoardRepository.save(board);
			return board;			
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		jpaBoardRepository.deleteById(boardIdx);		
	}

	@Override
	public List<BoardEntity> selectBoardListByTitle(String title) throws Exception {
		return jpaBoardRepository.findByTitleContaining(title);
		// return jpaBoardRepository.findBoardDetailByTitle(title);
	}


}
