package com.power.yuneng.activity.dao.ex;

import com.power.yuneng.activity.entity.UserQuestionAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/28.
 */
public interface IVipExDAO {
    boolean giveVip(@Param("ids") List<Long> ids,@Param("type")Integer type,@Param("start")Long start,@Param("end")Long end);
    boolean updateBonusesVip(@Param("ids") List<Long> ids,@Param("activityId")Integer activityId,@Param("status")Integer status);
    boolean updateActivityUser(@Param("currProgress") Integer currProgress,@Param("nextProgress") Integer nextProgress,@Param("activityId")Integer activityId);
    Map<String,String> getSendMsg(@Param("uniqueKey")String uniqueKey,@Param("msgName")String msgName);
}
