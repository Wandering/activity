package com.power.yuneng.activity.dao.ex;

import com.power.yuneng.activity.entity.UserQuestionAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
public interface IUserQuestionAnswerExDAO {
    boolean saveAnswers(@Param("answers") List<UserQuestionAnswer> answers);
}
