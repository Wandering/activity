package com.power.yuneng.activity.test;

import com.alibaba.fastjson.JSON;
import com.power.yuneng.activity.api.IActivityNotify;
import com.power.yuneng.activity.entity.dto.UserActivityExDTO;
import com.power.yuneng.activity.entity.dto.UserAnswerDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Created by Administrator on 2017/7/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiUserQuestionAnswerControllerTest {

    @Test
    public void test1(){}
    @Autowired
    private MockMvc mvc;
    @Test
    public void testSaveAnswers() throws Exception {

        UserAnswerDTO userAnswerDTO = new UserAnswerDTO();
        userAnswerDTO.setUserId(10L);
        userAnswerDTO.setQuestionnaireId(1);
        userAnswerDTO.setActivityId(1);
        List<UserAnswerDTO.Answer> userAnswer = new ArrayList<>();
        for (int i=10;i>0;i--){
            UserAnswerDTO.Answer answer = new UserAnswerDTO.Answer();
            answer.setContent(""+i);
            answer.setQuestionNo(i);
            userAnswer.add(answer);
        }
        userAnswerDTO.setAnswers(userAnswer);
        System.out.println(JSON.toJSONString(userAnswerDTO));
        this.mvc.perform(post("http://activity.dev.popularpowers.com/api/userquestionanswer/saveAnswers")
//                .param("accountId","10")
                .param("answers",JSON.toJSONString(userAnswerDTO)).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        ).andDo(new ResultHandler() {
            @Override
            public void handle(MvcResult mvcResult) throws Exception {

            }
        });
    }

    @Test
    public void testCreateQuestion() throws Exception {

        UserAnswerDTO userAnswerDTO = new UserAnswerDTO();
        userAnswerDTO.setUserId(10L);
        userAnswerDTO.setQuestionnaireId(1);
        userAnswerDTO.setActivityId(1);
        System.out.println(JSON.toJSONString(userAnswerDTO));
        this.mvc.perform(post("/api/question/create")
//                .param("accountId","10")
                        .param("question",JSON.toJSONString(userAnswerDTO)).accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        ).andDo(new ResultHandler() {
            @Override
            public void handle(MvcResult question) throws Exception {
                System.out.println(question.getResponse().getContentAsString());
            }
        });
    }

    @Autowired
    private IActivityNotify activityNotify;
    @Test
    public void testGiveVip() throws Exception {
        UserActivityExDTO userAnswerDTO = new UserActivityExDTO();
        userAnswerDTO.setUserId(10L);
        userAnswerDTO.setQuestionnaireId(1);
        userAnswerDTO.setActivityId(1);
        userAnswerDTO.setUniqueKey("powertest");
        userAnswerDTO.setOpenId("oqoGE0UJGJSuFrSbJX-SwcpVhLYY");
        activityNotify.giveBonuses(userAnswerDTO);
//        while (true){
//            System.out.println(123);
//        }
    }


}
