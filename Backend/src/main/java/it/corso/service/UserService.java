package it.corso.service;

import java.util.List;

import it.corso.dto.UserDto;
import it.corso.dto.UserLoginRequestDto;
import it.corso.dto.UserRegistrationDto;

public interface UserService {
	
	boolean existsUserById(int id);
	boolean existsUserByEmail(String email);
	
	void registration(UserRegistrationDto userDto);
	boolean login(UserLoginRequestDto userDto);
	
	UserDto getUserById(int id);
	UserDto getUserByEmail(String email);
	
	List<UserDto> getUsers();
		
}
