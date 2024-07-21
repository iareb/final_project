package it.corso.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.UserDao;
import it.corso.dto.UserDto;
import it.corso.dto.UserLoginRequestDto;
import it.corso.dto.UserRegistrationDto;
import it.corso.dto.UserUpdateDto;
import it.corso.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean existsUserById(int id) {
		Optional<User> optional = userDao.findById(id);
		if (optional.isPresent()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean existsUserByEmail(String email) {
		Optional<User> optional = userDao.findByEmail(email);
		if (optional.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public void registration(UserRegistrationDto userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		
		String sha256hex = DigestUtils.sha256Hex(userDto.getPassword());
		user.setPassword(sha256hex);
		
		userDao.save(user);
	}

	@Override
	public boolean login(UserLoginRequestDto userDto) {
		String email = userDto.getEmail();
		String password = userDto.getPassword();	
		Optional<User> optional = userDao.findByEmail(email);
		
		if (optional.isPresent()) {
			
			User user = optional.get();
			String hashedPassword = DigestUtils.sha256Hex(password);
			return hashedPassword.equals(user.getPassword());
		}
		
		return false;
	}

	@Override
	public UserDto getUserById(int id) {
		Optional<User> userOptional = userDao.findById(id);
		if (!userOptional.isPresent()) {
			return new UserDto();
		}
		
		User userDb = userOptional.get();
		UserDto userDto = modelMapper.map(userDb, UserDto.class);
		return userDto;
	}
	
	@Override
	public User getUserByEmail(String email) {
		Optional<User> userOptional = userDao.findByEmail(email);	
		if (!userOptional.isPresent()) {
			return new User();
		}
		
		User userDb = userOptional.get();
		return userDb;
	}

	@Override
	public UserDto getUserDtoByEmail(String email) {
		Optional<User> userOptional = userDao.findByEmail(email);	
		if (!userOptional.isPresent()) {
			return new UserDto();
		}
		
		User userDb = userOptional.get();
		UserDto userDto = modelMapper.map(userDb, UserDto.class);
		return userDto;
	}

	@Override
	public List<UserDto> getUsers() {
		List<User> users = (List<User>) userDao.findAll();	
		List<UserDto> usersDto = modelMapper.map(users, new TypeToken<List<UserDto>>() {}.getType());
		return usersDto;
	}
	

	@Override
	public void updateUserData(UserUpdateDto userDto) {
		
		Optional<User> optional = userDao.findByEmail(userDto.getEmail());
		if (optional.isPresent()) {
			
			User userDb = optional.get();
			userDb.setName(userDto.getName());
			userDb.setLastName(userDto.getLastName());
			userDb.setEmail(userDto.getEmail());
			
			if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
	            String sha256hex = DigestUtils.sha256Hex(userDto.getPassword());
	            userDb.setPassword(sha256hex);
			}
			
			userDao.save(userDb);
		}

	}

	@Override
	public void deleteUserById(int id) {
		
		Optional<User> optional = userDao.findById(id);
		if (optional.isPresent()) {
			userDao.delete(optional.get());
		}

	}

	@Override
	public void deleteUserByEmail(String email) {
		
		Optional<User> optional = userDao.findByEmail(email);
		if (optional.isPresent()) {
			userDao.delete(optional.get());
		}
	}
	
}
