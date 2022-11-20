package board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import board.entity.BoardEntity;

public interface JpaBoardRepository extends CrudRepository<BoardEntity, Integer> {
	// 리포지터리 인터페이스에서 기본으로 제공하는 메서드 외에 커스텀 쿼리를 추가하는 방법
	
	// 방법1. 쿼리 메서드
	List<BoardEntity> findByTitle(String title);
	List<BoardEntity> findByTitleLike(String title);
	List<BoardEntity> findByTitleContaining(String title);
	
	// 방법2. @Query - JPQL을 이용해서 쿼리를 직접 작성
	@Query("SELECT board FROM BoardEntity board WHERE title like '%'||:title||'%'")
	List<BoardEntity> findBoardDetailByTitle(@Param("title") String title);
}
