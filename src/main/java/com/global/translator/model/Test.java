/**
 * 
 */
package com.global.translator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hariharan J
 *
 * Jun 26, 2023
 */
//@Entity
//@Table(name="test")
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test {
	
//	@Id
	private Integer userid;
	
	private Integer productid;
}
