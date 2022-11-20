package board.dto;

import lombok.Data;

@Data
public class MemberDto {
	private int memberSeq;
	private String memberName;
	private String memberPass;
	private String memberEmail;
	private String creatorId;
	private String createdDateTime;
	private String updaterId;
	private String updatedDateTime;
}
