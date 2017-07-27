package com.power.api;

import com.power.entity.UserQuestionnaireEntity;

public interface ISurveyService {
  /*
   * 收集问卷调查的信息
   */
  Boolean addSurvey(UserQuestionnaireEntity survey);

}
