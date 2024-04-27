package com.epam.passwordmanagementtool.entity;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.epam.passwordmanagementtool.config.AttributeEncryptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String accountName;

	private String accountUserName;

	@Convert(converter=AttributeEncryptor.class)
	private String accountPassword;

	private String url;

	
	  @ManyToOne(fetch=FetchType.EAGER) 
	  @JoinColumn(name = "categoryId",nullable=false) 
	  private Category category;


	
	  
	  
	  
	
	 
}
