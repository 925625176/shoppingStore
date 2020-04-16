package serviceImpl;

import entity.Orderdetail1;
import mapper.Orderdetail1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.Orderdetal1Service;
@Service
public class Orderdetal1ServiceImpl implements Orderdetal1Service {
    @Autowired
    Orderdetail1Mapper odm;

    @Override
    public int deleteByPrimaryKey(Integer odId) {
        return odm.deleteByPrimaryKey(odId);
    }

    @Override
    public int insert(Orderdetail1 record) {
        return odm.insert(record);
    }

    @Override
    public int insertSelective(Orderdetail1 record) {
        return odm.insertSelective(record);
    }

    @Override
    public Orderdetail1 selectByPrimaryKey(Integer odId) {
        return odm.selectByPrimaryKey(odId);
    }

    @Override
    public int updateByPrimaryKeySelective(Orderdetail1 record) {
        return odm.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Orderdetail1 record) {
        return odm.updateByPrimaryKey(record);
    }
}
