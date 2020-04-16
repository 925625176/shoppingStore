package mapper;

import entity.Orderdetail1;

public interface Orderdetail1Mapper {
    int deleteByPrimaryKey(Integer odId);

    int insert(Orderdetail1 record);

    int insertSelective(Orderdetail1 record);

    Orderdetail1 selectByPrimaryKey(Integer odId);

    int updateByPrimaryKeySelective(Orderdetail1 record);

    int updateByPrimaryKey(Orderdetail1 record);
}