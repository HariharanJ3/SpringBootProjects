/**
 * 
 */
package com.global.translator.Service;

import java.util.List;
import java.util.Optional;

import com.global.translator.model.Test;
import com.global.translator.model.UserMaster;
import com.global.translator.model.Users;

/**
 * @author Hariharan J
 *
 * 24-Sep-2022
 */

public interface TranslatorService {

	UserMaster selectUserMaster(String username);

	void saveTest(Test test);

	List<Test> getDetails();
	
	List<UserMaster> selectAll() throws Exception;

	Users findByEmailid(String username);

	List<Users> getUsersByRole(String role);

	Users updateUsers(Users users);

	String DeleteUsersById(Integer id);

	UserMaster findByEmail(String email);

}
