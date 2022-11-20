package board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity				// JPA의 엔티티 클래스로 테이블과 맵핑
@Table(name="t_jpa_board")
public class BoardEntity {
	@Id				// 엔티티의 기본키(PK)
					// 기본키 생성 전략 
					// GenerationType.AUTO : DB에서 제공하는 기본키 생성 전략을 따름 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int boardIdx;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String contents;
	
	@Column(nullable = false)
	private int hitCnt = 0;
	
	@Column(nullable = false)
	private String creatorId;
	
	@Column(nullable = false)
	private LocalDateTime createdDateTime = LocalDateTime.now();
	
	private String updaterId;
	private LocalDateTime updatedDateTime;
	
}
