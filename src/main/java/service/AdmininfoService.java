package service;

import entity.Admininfo;
import org.apache.ibatis.annotations.Param;

public interface AdmininfoService {
    int deleteByPrimaryKey(Integer adId);

    int insert(Admininfo record);

    int insertSelective(Admininfo record);

    Admininfo selectByPrimaryKey(Integer adId);

    int updateByPrimaryKeySelective(Admininfo record);

    int updateByPrimaryKey(Admininfo record);

    Admininfo selectByUsernameAndPassword(String username, String password);

}
