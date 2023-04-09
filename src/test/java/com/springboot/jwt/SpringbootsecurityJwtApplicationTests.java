package com.springboot.jwt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.jwt.domain.UserDTO;
import com.springboot.jwt.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringbootsecurityJwtApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void generateTokenTest() throws Exception {
		User user= Mockito.mock(User.class);
		user.setEmail("satishkumar@gmail.com");
		user.setPassword("satishkumar");
		
		// when
		MockHttpServletResponse response = mockMvc.perform(post("/login")
				.content(asJsonString(user))
				.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		
		// then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
	
	@Test
    public void shouldGenerateAuthToken() throws Exception {
		generateTokenTest();

        //assertNotNull(token);
        //mvc.perform(MockMvcRequestBuilders.get("/test").header("Authorization", token)).andExpect(status().isOk());
    }
	
	private static String asJsonString(final Object obj) {
	    try {
	        return Mockito.mock(ObjectMapper.class).writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
