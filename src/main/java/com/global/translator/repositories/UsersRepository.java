/**
 * 
 */
package com.global.translator.repositories;

import java.util.List;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.global.translator.model.Users;

/**
 * @author Hariharan J
 *
 * 16-Feb-2024
 */

@Repository
public interface UsersRepository extends  MongoRepository<Users, Integer>{
	
	List<Users> findByEmail(String email);

	List<Users> findByRole(String role);
	
	Users findByUserid(Integer userid);

	void deleteByUserid(Integer id);

}
