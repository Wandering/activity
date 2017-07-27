package com.power.service.impl;

import java.util.Date;

import com.power.api.ISurveyService;
import com.power.dao.UserQuestionnaireDao;
import com.power.entity.UserQuestionnaireEntity;

public class ISurveyImpl  implements ISurveyService {
	private boolean isAdd = true;

	private UserQuestionnaireDao userQuestionnaireDao ;
	@Override
	public Boolean addSurvey(UserQuestionnaireEntity survey) {
		survey.setCreateDt(new Date().getTime()/1000);
		userQuestionnaireDao.save(survey);
		return isAdd;
	}

}
