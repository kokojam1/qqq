package board.dto;

import lombok.Data;

@Data
public class RequestLogin {
	private String memberEmail;		// 로그인에 사용하는 username
	private String memberPass;		// 로그인에 사용하는 password
}
