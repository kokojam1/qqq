package board.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import board.service.JpaMemberService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	// 의존 객체를 생성자를 통해서 주입 
	private JpaMemberService memberService;
	private BCryptPasswordEncoder passwordEncoder;
	private Environment env;
	
	public WebSecurity(JpaMemberService memberService, BCryptPasswordEncoder passwordEncoder, Environment env) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
		this.env = env;
	}

	// 접근 권한과 관련한 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// http.authorizeRequests().antMatchers("/**").permitAll();
		
		http.authorizeRequests()
//			.antMatchers("/api/jpa/member/**").authenticated()
			.antMatchers("/api/jpa/member/**").permitAll()
			.and().addFilter(getAuthenticationFilter()); 
	}													 				
	
	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(memberService, env);
		authenticationFilter.setAuthenticationManager(authenticationManager());
		return authenticationFilter;
	}
	
	// 인증 처리에 필요한 설정
	// 사용자 정보를 조회할 서비스와 패스워드 암호화에 사용할 방식을 지정 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder);
	}
	
}
