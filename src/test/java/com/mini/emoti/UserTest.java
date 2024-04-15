package com.mini.emoti;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.emoti.controller.UserController;
import com.mini.emoti.model.dto.UserDto;
import com.mini.emoti.service.PostService;
import com.mini.emoti.service.UserService;
// https://studyandwrite.tistory.com/502
// https://shinsunyoung.tistory.com/52
@ExtendWith(MockitoExtension.class)
// WebMvcTest에서 발생하는 SpringSecurity 의존성 문제 해결
@WebMvcTest(controllers = UserController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) })
@MockBean(JpaMetamodelMappingContext.class)
public class UserTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PostService postService;
    @MockBean
    private UserService userService;
    @Test
    @DisplayName("회원가입 테스트 - 이메일")
    public void joinUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setNickname("testuser");
        userDto.setEmail("testuse@error");
        userDto.setPassword("testuser123");
        String testUser = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}