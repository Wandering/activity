package com.power.yuneng.activity.entity.dto;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
public class UserAnswerDTO extends UserActivityDTO {

    List<Answer> answers;


    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


    public static class Answer {
        /**
         * 题号
         */
        private Integer questionNo;
        /**
         * 内容
         */
        private String content;

        public Integer getQuestionNo() {
            return questionNo;
        }

        public void setQuestionNo(Integer questionNo) {
            this.questionNo = questionNo;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
