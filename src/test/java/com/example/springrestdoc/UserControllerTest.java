package com.example.springrestdoc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.PayloadDocumentation;

import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import java.util.HashMap;


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

//    public UserControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
//        this.mockMvc = mockMvc;
//        this.objectMapper = objectMapper;
//    }

    @DisplayName("회원 가입 성공")
    @Test
    void createUser2() throws Exception{
        HashMap<String, Object> inputMap = new HashMap<>();
        inputMap.put("loginId","jjk0237");
        inputMap.put("pwd","golfzon1!");
        inputMap.put("email","asdfg0237@naver.com");
        inputMap.put("phoneNumber","010-5090-7845");

        String requestJson = objectMapper.writeValueAsString(inputMap);

        MvcResult resultMock = mockMvc.perform(
                        MockMvcRequestBuilders.post("/users")
                                .content(requestJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(
                        MockMvcRestDocumentation.document("UserController/join",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        PayloadDocumentation.requestFields(
                                            PayloadDocumentation.fieldWithPath("loginId")
                                                    .description("사용자 아이디"),
                                            PayloadDocumentation.fieldWithPath("pwd")
                                                    .description("사용자 비밀번호"),
                                            PayloadDocumentation.fieldWithPath("email")
                                                    .description("사용자 이메일"),
                                            PayloadDocumentation.fieldWithPath("phoneNumber")
                                                        .description("사용자 휴대폰 번호")
                                        ),
                                        PayloadDocumentation.responseFields(
                                                PayloadDocumentation.fieldWithPath("id")
                                                        .description("사용자 pk"),
                                                PayloadDocumentation.fieldWithPath("loginId")
                                                        .description("사용자 아이디"),
                                                PayloadDocumentation.fieldWithPath("pwd")
                                                        .description("생성된 사용자 비밀번호"),
                                                PayloadDocumentation.fieldWithPath("email")
                                                        .description("생성된 사용자 이메일"),
                                                PayloadDocumentation.fieldWithPath("phoneNumber")
                                                        .description("생성된 사용자 휴대폰번호"),
                                                PayloadDocumentation.fieldWithPath("statusMsg")
                                                        .description("회원가입 성공 여부")
                                        )
                                )
                ).andReturn();

        HashMap<String, Object> result = objectMapper.readValue(resultMock.getResponse().getContentAsString(),HashMap.class);

        Assert.assertTrue(result.get("statusMsg").equals("success"));
    }

    @Test
    void createUser() throws Exception{


//        MultiValueMap<String, String> inputData = new LinkedMultiValueMap<>();
//        MultiValueMap<String, String> outputData = new LinkedMultiValueMap<>();
//        inputData.add("id","10");
//        inputData.add("pwd","golfzon1!");
//        inputData.add("email","asdfg0237@naver.com");
//
//        outputData.add("id","10");
//        outputData.add("pwd","golfzon1!");
//        outputData.add("email","asdfg0237@naver.com");
//
//        HashMap<String, Object> inputMap = new HashMap<>();
//        HashMap<String, Object> outputMap = new HashMap<>();
//
//        inputMap.put("id",10);
//        inputMap.put("pwd","golfzon1!");
//        inputMap.put("email","asdfg0237@naver.com");
//
//        outputMap.put("id",10);
//        outputMap.put("pwd","golfzon1!");
//        outputMap.put("email","asdfg0237@naver.com");
//
//        String requestJson = objectMapper.writeValueAsString(inputMap);
//        String responseJson = objectMapper.writeValueAsString(outputMap);
//
//
//        MvcResult resultMock = mockMvc.perform(
//            MockMvcRequestBuilders.post("/users")
//            .content(requestJson)
//            .contentType(MediaType.APPLICATION_JSON)
//            .accept(MediaType.APPLICATION_JSON)
//            )
//            .andDo(MockMvcRestDocumentation.document("test-yongwon"))
//            .andReturn();
//
//        HashMap<String, Object> result = objectMapper.readValue(resultMock.getResponse().getContentAsString(),HashMap.class);
//
//        Assert.assertTrue(result.containsKey("id"));
        Assert.assertTrue(true);

    }


}