package serviceImpl;

import entity.Productinfo;
import mapper.ProductinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ProductinfoService;

import java.util.List;
import java.util.Map;

@Service
public class ProductinfoServiceImpl implements ProductinfoService {
   @Autowired
    ProductinfoMapper pim;

    @Override
    public int deleteByPrimaryKey(Integer pId) {
        return pim.deleteByPrimaryKey(pId);
    }

    @Override
    public int insert(Productinfo record) {
        return pim.insert(record);
    }

    @Override
    public int insertSelective(Productinfo record) {
        return pim.insertSelective(record);
    }

    @Override
    public Productinfo selectByPrimaryKey(Integer pId) {
        return pim.selectByPrimaryKey(pId);
    }

    @Override
    public int updateByPrimaryKeySelective(Productinfo record) {
        return pim.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Productinfo record) {
        return pim.updateByPrimaryKey(record);
    }

    @Override
    public List<Productinfo> getPtype() {
        return pim.getPtype();
    }

    /**
     * 根据商品类型查找
     * @param pType
     * @return
     */
    @Override
    public List<Productinfo> selectByP_type(String pType) {
        return pim.selectByP_type(pType);
    }

    @Override
    public List<Productinfo> getallProductinfoOrderByType() {
        return pim.getallProductinfoOrderByType();
    }

    @Override
    public Productinfo selectByPCode(Integer pCode) {
        return pim.selectByPCode(pCode);
    }
}
