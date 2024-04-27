/**
 * 
 */
package com.global.translator.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hariharan J
 *
 * Jun 20, 2023
 */
@Entity
@Table(name="productmaster")
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productid;
	
	private String productname;
	private String weight;
	private Double price;

}
