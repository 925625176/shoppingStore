package mapper;

import entity.Productinfo;

import java.util.List;
import java.util.Map;

public interface ProductinfoMapper {
    int deleteByPrimaryKey(Integer pId);

    int insert(Productinfo record);

    int insertSelective(Productinfo record);

    Productinfo selectByPrimaryKey(Integer pId);

    int updateByPrimaryKeySelective(Productinfo record);

    int updateByPrimaryKey(Productinfo record);
	List<Productinfo> getPtype();


    /**
     * 根据商品类型查询
     * @return
     */
    List<Productinfo> selectByP_type(String pType);

    List<Productinfo> getallProductinfoOrderByType();

    Productinfo selectByPCode(Integer pCode);
}