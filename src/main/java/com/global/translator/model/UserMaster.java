/**
 * 
 */
package com.global.translator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hariharan J
 *
 * Jun 20, 2023
 */
@Entity
@Table(name="usermaster")
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	
	private String username;
	private String password;
	private String emailid;
	private String phoneno;
	private String role;
	

}
