package com.epam.passwordmanagementtool.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	
	private static final int CATEGORY_NAME_MIN_SIZE=5;
	private static final int CATEGORY_NAME_MAX_SIZE=20;
	
	
	private Integer categoryId;
	
	
	@Size(min = CATEGORY_NAME_MIN_SIZE, max = CATEGORY_NAME_MAX_SIZE, message = "categoryName should be of min 5 characters and max of 20 chars")
	@NotNull
	private String categoryName;



	public CategoryDto( String categoryName) {
		super();
		this.categoryName = categoryName;
	}



	
	


}
