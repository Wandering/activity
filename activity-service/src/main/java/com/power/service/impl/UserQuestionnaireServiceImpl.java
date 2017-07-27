package com.power.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.power.dao.UserQuestionnaireDao;
import com.power.entity.UserQuestionnaireEntity;
import com.power.service.UserQuestionnaireService;



@Service("userQuestionnaireService")
public class UserQuestionnaireServiceImpl implements UserQuestionnaireService {
	@Autowired
	private UserQuestionnaireDao userQuestionnaireDao;

	@Override
	public UserQuestionnaireEntity queryObject(Integer id){
		return userQuestionnaireDao.queryObject(id);
	}

	@Override
	public List<UserQuestionnaireEntity> queryList(Map<String, Object> map){
		return userQuestionnaireDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return userQuestionnaireDao.queryTotal(map);
	}

	@Override
	public void save(UserQuestionnaireEntity userQuestionnaire){
		userQuestionnaireDao.save(userQuestionnaire);
	}

	@Override
	public void update(UserQuestionnaireEntity userQuestionnaire){
		userQuestionnaireDao.update(userQuestionnaire);
	}

	@Override
	public void delete(Integer id){
		userQuestionnaireDao.delete(id);
	}

	@Override
	public void deleteBatch(Integer[] ids){
		userQuestionnaireDao.deleteBatch(ids);
	}

}
