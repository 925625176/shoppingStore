package serviceImpl;

import entity.Orderinfo1;
import mapper.Orderinfo1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.Orderinfo1Service;
@Service
public class Orderinfo1ServiceImpl implements Orderinfo1Service {
    @Autowired
    Orderinfo1Mapper oim;

    @Override
    public int deleteByPrimaryKey(Integer oId) {
        return oim.deleteByPrimaryKey(oId);
    }

    @Override
    public int insert(Orderinfo1 record) {
        return oim.insert(record);
    }

    @Override
    public int insertSelective(Orderinfo1 record) {
        return oim.insertSelective(record);
    }

    @Override
    public Orderinfo1 selectByPrimaryKey(Integer oId) {
        return oim.selectByPrimaryKey(oId);
    }

    @Override
    public int updateByPrimaryKeySelective(Orderinfo1 record) {
        return oim.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Orderinfo1 record) {
        return oim.updateByPrimaryKey(record);
    }

    @Override
    public Orderinfo1 selectByuserid(Integer userid) {
        return oim.selectByuserid(userid);
    }
}
