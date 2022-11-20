package board.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import board.entity.MemberEntity;

public interface JpaMemberRepository extends CrudRepository<MemberEntity, Integer> {
	List<MemberEntity> findByMemberNameContaining(String memberName);

	MemberEntity findByMemberEmail(String username);
}
