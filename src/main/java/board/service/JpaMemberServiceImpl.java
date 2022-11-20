package board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import board.entity.MemberEntity;
import board.repository.JpaMemberRepository;

@Service
public class JpaMemberServiceImpl implements JpaMemberService {

	// 의존 객체를 생성자를 통해서 주입
	private JpaMemberRepository jpaMemberRepository;
	private BCryptPasswordEncoder passwordEncoder;

	public JpaMemberServiceImpl(JpaMemberRepository jpaMemberRepository, BCryptPasswordEncoder passwordEncoder) {
		this.jpaMemberRepository = jpaMemberRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<MemberEntity> selectMemberList() throws Exception {
		return (List<MemberEntity>) jpaMemberRepository.findAll();
	}

	@Override
	public MemberEntity saveMember(MemberEntity memberEntity) throws Exception {
		
		// 패스워드 정보를 암호화해서 저장
		memberEntity.setMemberPass(passwordEncoder.encode(memberEntity.getMemberPass()));
		
		return jpaMemberRepository.save(memberEntity);
	}

	@Override
	public MemberEntity selectMemberDetail(int memberSeq) throws Exception {
		Optional<MemberEntity> optional = jpaMemberRepository.findById(memberSeq);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new Exception();
		}
	}

	@Override
	public void deleteMember(int memberSeq) throws Exception {
		jpaMemberRepository.deleteById(memberSeq);		
	}

	@Override
	public List<MemberEntity> selectMemberListByName(String memberName) throws Exception {
		return jpaMemberRepository.findByMemberNameContaining(memberName);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberEntity memberEntity = jpaMemberRepository.findByMemberEmail(username);
		if (memberEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		
		// String username, String password, boolean enabled, boolean accountNonExpired,
		// boolean credentialsNonExpired, boolean accountNonLocked,
		// Collection<? extends GrantedAuthority> authorities
		return new User(memberEntity.getMemberEmail(), memberEntity.getMemberPass(), 
				true, true, true, true, new ArrayList<>());
	}

	@Override
	public MemberEntity getMemberDetailByEmail(String username) {
		MemberEntity memberEntity = jpaMemberRepository.findByMemberEmail(username);
		if (memberEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		return memberEntity;
	}
}
