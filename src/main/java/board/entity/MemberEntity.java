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
@Entity				
@Table(name="t_jpa_member")
public class MemberEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int memberSeq;
	
	@Column(nullable = false)
	private String memberName;
	
	@Column(nullable = false)
	private String memberPass;
	
	@Column(nullable = false)
	private String memberEmail;
	
	@Column(nullable = false)
	private String creatorId;
	
	@Column(nullable = false)
	private LocalDateTime createdDateTime = LocalDateTime.now();
	
	@Column(nullable = false)
	private String deletedYn = "N";
	
	private String updaterId;
	private LocalDateTime updatedDateTime;
	
}
