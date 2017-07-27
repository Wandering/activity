package com.power.service;

import com.power.entity.UserQuestionnaireEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-26 19:39:25
 */
public interface UserQuestionnaireService {
	
	UserQuestionnaireEntity queryObject(Integer id);
	
	List<UserQuestionnaireEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserQuestionnaireEntity userQuestionnaire);
	
	void update(UserQuestionnaireEntity userQuestionnaire);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
