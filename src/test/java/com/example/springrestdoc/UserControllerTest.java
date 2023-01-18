package com.example.springrestdoc;

import com.example.springrestdoc.user.dto.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletException;
import java.util.HashMap;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@EnableWebMvc
@Import(RestDocsAutoConfiguration.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    private RestDocumentationResultHandler document;

    @Before
    public void setup() throws ServletException {

    }

    @DisplayName("User Search : GET")
    @Test
    void searchUser() throws Exception{
        UserRequest userRequest = new UserRequest();
        userRequest.setLoginId("jjk0237");

        String requestJson = objectMapper.writeValueAsString(userRequest);

        //api 요청 부분
        MvcResult resultMock = mockMvc.perform(
                    get("/user")
                            .content(requestJson)
                )
                .andExpect(status().isOk())
                .andDo(
                        //api 요청시 request, response docs 작성
                        document("UserController/search",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("loginId").description("사용자 아이디").attributes(Attributes.key("type").value("String"))
                                ),
                                responseFields(//response 문서 작성
                                        fieldWithPath("id").description("사용자 pk").attributes(Attributes.key("type").value("Number")),
                                        fieldWithPath("loginId").description("사용자 아이디").attributes(Attributes.key("type").value("String")),
                                        fieldWithPath("email").description("생성된 사용자 이메일").attributes(Attributes.key("type").value("String")),
                                        fieldWithPath("statusMsg").description("조회 성공 여부").attributes(Attributes.key("type").value("String")),
                                        fieldWithPath("phoneNumber").description("생성된 사용자 휴대폰번호").attributes(Attributes.key("type").value("String"))

                                )
                        )
                ).andReturn();

        HashMap<String, Object> result = objectMapper.readValue(resultMock.getResponse().getContentAsString(),HashMap.class);
        System.out.println("result  end  ===>  " + result);
        Assert.assertTrue(
                "success".equals(result.get("statusMsg")) || "fail".equals(result.get("statusMsg"))
        );
    }

    @DisplayName("User join : POST 예시")
    @Test
    void createUser() throws Exception{

        UserRequest userRequest = new UserRequest();
        userRequest.setLoginId("jjk0237");
        userRequest.setPwd("golfzon1!");
        userRequest.setEmail("asdfg0237@naver.com");
        userRequest.setPhoneNumber("01050907845");

        String requestJson = objectMapper.writeValueAsString(userRequest);

        //api 요청 부분
        MvcResult resultMock = mockMvc.perform(
                        post("/user")
                                .content(requestJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(
                        //api 요청시 request, response docs 작성
                        document("UserController/join",
                                preprocessRequest(prettyPrint()), //prettyPrint : request, response json 보기 좋게 보정
                                preprocessResponse(prettyPrint()),
                                requestFields( //request 문서 작성
                                        fieldWithPath("loginId")
                                                .description("사용자 아이디")
                                                .attributes(Attributes.key("type").value("String")) //타입 지정
                                                .optional(), //필수여부
                                        fieldWithPath("pwd").description("사용자 비밀번호").attributes(Attributes.key("type").value("String")),
                                        fieldWithPath("email").description("사용자 이메일").attributes(Attributes.key("type").value("String")),
                                        fieldWithPath("phoneNumber").description("사용자 휴대폰 번호").attributes(Attributes.key("type").value("String"))
                                ),
                                responseFields(//response 문서 작성
                                        fieldWithPath("id").description("사용자 pk").attributes(Attributes.key("type").value("Number")),
                                        fieldWithPath("loginId").description("사용자 아이디").attributes(Attributes.key("type").value("String")),
                                        fieldWithPath("email").description("생성된 사용자 이메일").attributes(Attributes.key("type").value("String")),
                                        fieldWithPath("phoneNumber").description("생성된 사용자 휴대폰번호").attributes(Attributes.key("type").value("String")),
                                        fieldWithPath("statusMsg").description("회원가입 성공 여부").attributes(Attributes.key("type").value("String"))
                                )
                        )
                ).andReturn();

        HashMap<String, Object> result = objectMapper.readValue(resultMock.getResponse().getContentAsString(),HashMap.class);

        Assert.assertTrue("success".equals(result.get("statusMsg")));
    }



}