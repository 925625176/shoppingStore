package controller;

import entity.Userinfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import serviceImpl.UserinfoServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class UserinfoController {
    @Autowired
    UserinfoServiceImpl usi;


    @RequestMapping("/register")
    public int registerInsert(@RequestParam String username,@RequestParam String password,@RequestParam String email){

           Userinfo ui = new Userinfo();
           ui.setUsername(username);
           String md5pwd = DigestUtils.md5Hex(password.getBytes());
           ui.setPassword(md5pwd);
           ui.setEmail(email);
           Date date= new Date();
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           ui.setRegisterTime(sdf.format(date));
           return usi.insert(ui);



    }

    @RequestMapping("/selectOne")
    public List<Userinfo> selectOne(@RequestParam String username, @RequestParam String password){
        String md5pwd = DigestUtils.md5Hex(password.getBytes());
        return usi.selectOne(username,md5pwd);
    }

    /**
     * 通过用户名查找
     * @param username
     * @return
     */
    @RequestMapping("/selectByName")
    public Userinfo selectByName(@RequestParam String username){
        return usi.selectByName(username);
    }





    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, @RequestParam String flag, HttpServletResponse response){
        Userinfo ui = usi.selectByName(username);
        if(ui==null){
            return "empty";
        }else {
            if(ui.getPassword().equals(DigestUtils.md5Hex(password.getBytes()))){
                if("yes".equals(flag)){
                    Cookie cookie1 = new Cookie("flag","true");
                    Cookie cookie2 = new Cookie("username",username);
                    Cookie cookie3 = new Cookie("password",password);
                    cookie1.setMaxAge(10000);
                    cookie2.setMaxAge(10000);
                    cookie3.setMaxAge(10000);
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                    response.addCookie(cookie3);
                }else{
                    Cookie cookie1 = new Cookie("flag","false");
                    cookie1.setMaxAge(10000);
                    response.addCookie(cookie1);
                }
                return "ok";
            }else {
                return "error";
            }
        }
    }



}


