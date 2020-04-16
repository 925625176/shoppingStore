package service;

import entity.Userinfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserinfoService {
    int deleteByPrimaryKey(Integer uId);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);


    /**
     * 根据用户名和密码查询
     * @param username1
     * @param password1
     * @return
     */
    List<Userinfo> selectOne(String username1, String password1);

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    Userinfo selectByName(String username);
}
