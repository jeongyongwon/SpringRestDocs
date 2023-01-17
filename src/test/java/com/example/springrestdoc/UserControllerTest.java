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

    @DisplayName("회원 가입 성공")
    @Test
    void createUser2() throws Exception{
        HashMap<String, Object> inputMap = new HashMap<>();
        inputMap.put("id",10);
        inputMap.put("pwd","golfzon1!");
        inputMap.put("email","asdfg0237@naver.com");
        inputMap.put("phone","010-4111-7845");

        String requestJson = objectMapper.writeValueAsString(inputMap);

        MvcResult resultMock = mockMvc.perform(
                        MockMvcRequestBuilders.post("/users")
                                .content(requestJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
//                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(
                        MockMvcRestDocumentation.document("test-user-create",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        PayloadDocumentation.requestFields(
                                            PayloadDocumentation.fieldWithPath("id")
                                                    .description("Request ID 필드입니다"),
                                            PayloadDocumentation.fieldWithPath("pwd")
                                                    .description("Request 비밀번호입니다"),
                                            PayloadDocumentation.fieldWithPath("email")
                                                    .description("Request 이메일입니다"),
                                            PayloadDocumentation.fieldWithPath("phone")
                                                        .description("Request 폰 번호입니다")
                                        ),
                                        PayloadDocumentation.responseFields(
                                                PayloadDocumentation.fieldWithPath("id")
                                                        .description("Response ID 필드입니다"),
                                                PayloadDocumentation.fieldWithPath("pwd")
                                                        .description("Response 비밀번호입니다"),
                                                PayloadDocumentation.fieldWithPath("email")
                                                        .description("Response 이메일입니다"),
                                                PayloadDocumentation.fieldWithPath("phoneNumber")
                                                        .description("Response 폰 번호입니다")
                                        )
                                )
                ).andReturn();

        HashMap<String, Object> result = objectMapper.readValue(resultMock.getResponse().getContentAsString(),HashMap.class);

        Assert.assertTrue(result.containsKey("id"));
    }

    @DisplayName("회원 가입 테스트")
    @Test
    void createUser() throws Exception{


        MultiValueMap<String, String> inputData = new LinkedMultiValueMap<>();
        MultiValueMap<String, String> outputData = new LinkedMultiValueMap<>();
        inputData.add("id","10");
        inputData.add("pwd","golfzon1!");
        inputData.add("email","asdfg0237@naver.com");

        outputData.add("id","10");
        outputData.add("pwd","golfzon1!");
        outputData.add("email","asdfg0237@naver.com");

        HashMap<String, Object> inputMap = new HashMap<>();
        HashMap<String, Object> outputMap = new HashMap<>();

        inputMap.put("id",10);
        inputMap.put("pwd","golfzon1!");
        inputMap.put("email","asdfg0237@naver.com");

        outputMap.put("id",10);
        outputMap.put("pwd","golfzon1!");
        outputMap.put("email","asdfg0237@naver.com");

        String requestJson = objectMapper.writeValueAsString(inputMap);
        String responseJson = objectMapper.writeValueAsString(outputMap);

//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/users")
//                        .params(inputData)
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());

//                .andExpect(MockMvcResultMatchers.jsonPath("id").exists())
//                .andDo(MockMvcRestDocumentation.document("test-yonwon"));
//                .andDo(MockMvcResultHandlers.print());


        MvcResult resultMock = mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
            .content(requestJson)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(MockMvcRestDocumentation.document("test-yongwon"))
            .andReturn();

        HashMap<String, Object> result = objectMapper.readValue(resultMock.getResponse().getContentAsString(),HashMap.class);

//        System.out.println("resultDOW   ===>  "  +result);
        Assert.assertTrue(result.containsKey("id"));

    }


}