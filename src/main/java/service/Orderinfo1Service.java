package service;

import entity.Orderinfo1;

public interface Orderinfo1Service {
    int deleteByPrimaryKey(Integer oId);

    int insert(Orderinfo1 record);

    int insertSelective(Orderinfo1 record);

    Orderinfo1 selectByPrimaryKey(Integer oId);

    int updateByPrimaryKeySelective(Orderinfo1 record);

    int updateByPrimaryKey(Orderinfo1 record);
    Orderinfo1 selectByuserid(Integer userid);
}
