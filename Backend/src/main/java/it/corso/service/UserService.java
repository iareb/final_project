package it.corso.service;

import java.util.List;

import it.corso.dto.UserDto;
import it.corso.dto.UserLoginRequestDto;
import it.corso.dto.UserRegistrationDto;
import it.corso.dto.UserUpdateDto;
import it.corso.model.User;

public interface UserService {
	
	boolean existsUserById(int id);
	boolean existsUserByEmail(String email);
	
	void registration(UserRegistrationDto userDto);
	boolean login(UserLoginRequestDto userDto);
	
	UserDto getUserById(int id);
	User getUserByEmail(String email);
	UserDto getUserDtoByEmail(String email);	
	List<UserDto> getUsers();
	
	void updateUserData(UserUpdateDto userDto);
	void deleteUserById(int userId);
	void deleteUserByEmail(String email);
	
}
