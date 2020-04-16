package mapper;

import entity.Userinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);


    /**
     * 根据用户名和密码查找
     * @param username
     * @param password
     * @return
     */
    List<Userinfo> selectOne(@Param("username1") String username, @Param("password1") String password);

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    Userinfo selectByName(String username);

}