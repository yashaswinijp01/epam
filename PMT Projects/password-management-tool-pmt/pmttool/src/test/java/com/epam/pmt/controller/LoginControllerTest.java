package com.epam.pmt.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.passwordmanagementtool.controller.LoginController;
import com.epam.passwordmanagementtool.dto.UserDto;
import com.epam.passwordmanagementtool.entity.User;
import com.epam.passwordmanagementtool.exception.UserNotFoundException;
import com.epam.passwordmanagementtool.exceptionhandler.CustomExceptionHandler;
import com.epam.passwordmanagementtool.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
	
	@Mock
	UserRepository userRepo;
	
	@InjectMocks
	LoginController loginController;
	
	@Mock
	ModelMapper mapper;
	
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@BeforeEach
	public void setUp() {
		mockMvc=MockMvcBuilders.standaloneSetup(loginController).setControllerAdvice(CustomExceptionHandler.class).build();
		objectMapper=new ObjectMapper();
	}
	
	@Test
	void testLogOutpage() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assertNotNull(mvcResult);
	}
	
	@Test
	void testHomepage() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/home"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		assertNotNull(mvcResult);
	}
	@Test
	void testRegisterpage() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/Register"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		assertNotNull(mvcResult);
	}
	@Test
	void testLoginpage() throws Exception {
			
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/Login"))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		assertNotNull(mvcResult);
	}
	@Test
	void testRegistrationpage() throws Exception {
		
		UserDto userDto=new UserDto(1,"Aparna","Aparna@123");		
		User user=new User(1,"Aparna","Aparna@123");
		
		when(mapper.map(any(),Mockito.eq(User.class))).thenReturn(user);
		
		when(userRepo.save(user)).thenReturn(user);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/registerDetails").flashAttr("userDto", userDto)
				 .content(objectMapper.writeValueAsString(user)))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		
		
		assertNotNull(mvcResult);
	}
	@Test
	void testRegistrationpageFalse() throws Exception {
		
		UserDto userDto=new UserDto(1,"Aparna","Aparna");		
		User user=new User(1,"Aparna","Aparna");
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/registerDetails").flashAttr("userDto", userDto)
				 .content(objectMapper.writeValueAsString(user)))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		
		
		assertNotNull(mvcResult);
	}
	@Test
	void testLoginDetailspage() throws Exception {
		
		UserDto userDto=new UserDto(1,"Aparna","Aparna@123");		
		User user=new User(1,"Aparna","Aparna@123");
		
		
		
		when(userRepo.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
		
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/loginDetails").flashAttr("userDto", userDto)
				 .content(objectMapper.writeValueAsString(user)))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		
		
		assertNotNull(mvcResult);
	}
	@Test
	void testLoginDetailspageFalse() throws Exception {
		
		UserDto userDto=new UserDto(1,"Aparna","Aparna");		
		User user=new User(1,"Aparna","Aparna");		
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/loginDetails").flashAttr("userDto", userDto)
				 .content(objectMapper.writeValueAsString(user)))
	            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
	
	
	}
	@Test
	void testLoginDetailspageException() throws UserNotFoundException,Exception {
		
		UserDto userDto=new UserDto(1,"Aparna","Aparna@123");		
		User user=new User();

		Mockito.when(userRepo.findByUserNameAndPassword(Mockito.anyString(),Mockito.anyString())).thenThrow(new UserNotFoundException("user no found"));
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/loginDetails").flashAttr("userDto", userDto)
				 .content(objectMapper.writeValueAsString(user)))
	           .andExpect(MockMvcResultMatchers.status().isInternalServerError());
		
	
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
