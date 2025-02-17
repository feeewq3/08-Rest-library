package com.douglashdezt.library.services.impls;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglashdezt.library.models.dtos.UserInfo;
import com.douglashdezt.library.models.entities.Token;
import com.douglashdezt.library.models.entities.User;
import com.douglashdezt.library.repositories.TokenRepository;
import com.douglashdezt.library.repositories.UserRepository;
import com.douglashdezt.library.services.UserService;
import com.douglashdezt.library.utils.TokenManager;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private TokenRepository tokenRepository;
	
	@Autowired
	private TokenManager tokenManager;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void register(UserInfo userInfo) throws Exception {
		User user = new User();
		
		String encryptedPassword = passEncoder.encode(userInfo.getPassword());
		
		user.setUsername(userInfo.getUsername());
		user.setEmail(userInfo.getEmail());
		user.setPassword(encryptedPassword);
		
		userRepository.save(user);
	}

	@Override
	public User findOneById(Long id) throws Exception {
		
		User foundUser = userRepository
				.findById(id)
				.orElse(null);
		
		return foundUser;
	}

	@Override
	public List<User> findAll() throws Exception {
		return userRepository.findAll();
	}

	@Override
	public User findOneByIdentifer(String identifier) throws Exception {
		
		User foundUser = userRepository	
				.findOneByUsernameOrEmail(identifier, identifier);
		
		return foundUser;
	}
	
	@Override
	public User findOneByUsernameAndEmail(String username, String email) throws Exception {
		User foundUser = userRepository	
				.findOneByUsernameOrEmail(username, email);
		
		return foundUser;
	}

	@Override
	public Boolean comparePassword(User user, String passToCompare) throws Exception {
		return passEncoder.matches(passToCompare, user.getPassword());
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void insertToken(User user, String token) throws Exception {
		cleanTokens(user);
		
		Token newToken = new Token(token, user);
		tokenRepository.save(newToken);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public Boolean isTokenValid(User user, String token) throws Exception {
		cleanTokens(user);
		
		List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
				
		return tokens.stream()
		.filter((userToken) -> {
			return userToken.getContent().equals(token) && userToken.getActive();
		})
		.findAny()
		.orElse(null) != null;
	}
	
	@Transactional(rollbackOn = Exception.class)
	private void cleanTokens(User user) {
		List<Token> tokens = tokenRepository.findByUserAndActive(user, true);
		
		tokens.forEach((userToken) -> {
			if(!tokenManager.validateJwtToken(userToken.getContent(), user.getUsername())) {
				userToken.setActive(false);
				tokenRepository.save(userToken);
			}
		});
	}
}
