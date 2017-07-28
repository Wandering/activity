package com.power.yuneng.activity.dao.ex;

import com.power.yuneng.activity.entity.UserQuestionAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
public interface IVipExDAO {
    boolean giveVip(@Param("ids") List<Long> ids,@Param("type")Integer type,@Param("start")Long start,@Param("end")Long end);
    boolean updateBonusesVip(@Param("ids") List<Long> ids,@Param("status")Integer status);
}
