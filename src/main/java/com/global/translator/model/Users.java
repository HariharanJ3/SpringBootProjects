/**
 * 
 */
package com.global.translator.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hariharan J
 *
 * 16-Feb-2024
 */

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	@Id
	private Integer userid;
	private String username;
	private String password;
	private String role;
	private String email;
}
