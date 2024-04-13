/**
 * 
 */
package com.global.translator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.global.translator.model.Test;
import com.global.translator.model.UserMaster;
import com.global.translator.model.Users;

/**
 * @author Hariharan J
 *
 * Jun 20, 2023
 */

public interface UserRepository extends JpaRepository<UserMaster, Integer>{
	

}
