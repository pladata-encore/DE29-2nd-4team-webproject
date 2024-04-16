package com.mini.emoti;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.emoti.controller.PublicController;
import com.mini.emoti.model.dto.UserDto;
import com.mini.emoti.service.UserService;

// https://studyandwrite.tistory.com/502
// https://shinsunyoung.tistory.com/52
// https://github.com/WooriVeryGood/honeycourses-backend-spring/blob/30343352d8c456420c6a3b7d799c713e9fe7a4d2/src/test/java/org/wooriverygood/api/util/ControllerTest.java#L73

@ExtendWith(MockitoExtension.class)
/// WebMvcTest에서 발생하는 SpringSecurity 의존성 문제 해결
@WebMvcTest(controllers = PublicController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) })
@MockBean(JpaMetamodelMappingContext.class)
public class PublicTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    private WebApplicationContext webApplicationContext;
    // @BeforeEach
    // void setUp(WebApplicationContext webApplicationContext) {
    // this.mockMvc = MockMvcBuilders
    // .webAppContextSetup(webApplicationContext)
    // .apply(springSecurity())
    // .defaultRequest(post("/**").with(csrf()))
    // .defaultRequest(put("/**").with(csrf()))
    // .defaultRequest(delete("/**").with(csrf()))
    // .defaultRequest(get("/**").with(csrf()))
    // .build();
    // }

    @BeforeEach
    public void setup() {
        // Init MockMvc Object and build
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("회원가입 테스트 - 이메일")
    public void joinUserValidationFailed() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setNickname("testuser");
        userDto.setEmail("testemail@com"); // 잘못된 이메일 형식
        String jsonRequest = objectMapper.writeValueAsString(userDto);
        // log.info("[PublicTest][joinUserValidationFailed] jsonRequest : "+
        // jsonRequest);

        mockMvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(jsonRequest)) // userDto를 직접 content()에 전달
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @DisplayName("loginPage 테스트")
    public void loginUserValidationFailed() throws Exception {
        mockMvc.perform(get("/loginPage"))
                .andExpect(status().isOk()) 
                .andDo(print());

    }
}