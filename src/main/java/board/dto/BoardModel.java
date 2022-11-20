package board.dto;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class BoardModel extends RepresentationModel<BoardModel> {

	@JsonUnwrapped
	private final BoardDto boardDto;

	public BoardModel(BoardDto boardDto) {
		this.boardDto = boardDto;
	}
}
