package controller;

import entity.Admininfo;
import entity.WebInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import serviceImpl.AdmininfoServiceImpl;
import serviceImpl.WebInfoServiceImpl;

import java.util.List;

@RestController
public class AdmininfoController {
    @Autowired
    AdmininfoServiceImpl asi;
    @Autowired
    WebInfoServiceImpl wsi;
    @RequestMapping("/adminLogin")
    public boolean adminLogin(@RequestParam String username, @RequestParam String password){

        Admininfo ai = asi.selectByUsernameAndPassword(username, DigestUtils.md5Hex(password.getBytes()));
        if (ai != null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 查询所有webinfo登录人数
     * @return
     */
    @RequestMapping("/getWebData")
    public List<WebInfo> selectAll(){
       return wsi.selectAll();
    }
}
