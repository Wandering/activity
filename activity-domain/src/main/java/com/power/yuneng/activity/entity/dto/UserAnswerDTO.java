package com.power.yuneng.activity.entity.dto;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
public class UserAnswerDTO {
    /** 用户id */
    private Integer activityId;
    /** 用户id */
    private Long accountId;

    /** 问卷ID */
    private Integer questionnaireId;

    List<Answer> answers;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public static class Answer{
        /** 题号 */
        private Integer questionNo;
        /** 内容 */
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
