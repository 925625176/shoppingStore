package controller;

import entity.Productinfo;
import entity.Userinfo;
import javafx.scene.control.Alert;
import org.apache.taglibs.standard.tag.common.fmt.ParseNumberSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import serviceImpl.ProductinfoServiceImpl;
import serviceImpl.UserinfoServiceImpl;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class ShopCarController {
    @Autowired
    JedisPool jedisPool;
    @Autowired
    ProductinfoServiceImpl psi;
    @Autowired
    UserinfoServiceImpl usi;

    @RequestMapping("/addCar")
    public String addCar(@RequestParam String username,@RequestParam String pid){
        Jedis jedis = jedisPool.getResource();
        Map<String,String> map = jedis.hgetAll(username);
        //判断购物车中是否有商品
        String str="";
        if(map.isEmpty()){
            //没有商品
            map.put(pid,"1");
           str = jedis.hmset(username,map);

        }else {
            //有商品,接着判断是否有当前商品
            if(map.containsKey(pid)){
                map.put(pid,String.valueOf(Integer.parseInt(map.get(pid))+1));
            }else {
                map.put(pid,"1");
            }
            str = jedis.hmset(username,map);
        }
        if ("OK".equals(str)){
            return "yes";
        }else {
            return "no";
        }
    }

    @RequestMapping("/getCar")
    public List<Productinfo> getCar(@RequestParam String username){
        //从redis取值
        Jedis jedis = jedisPool.getResource();
        Map<String,String> map = jedis.hgetAll(username);
        List<Productinfo> list = new ArrayList<Productinfo>();
        if(!map.isEmpty()){
            for(Map.Entry<String,String> entry:map.entrySet()){
                Productinfo pi = psi.selectByPrimaryKey(Integer.parseInt(entry.getKey()));
                pi.setpNum(Integer.parseInt(entry.getValue()));
                list.add(pi);
            }
            return list;
        }

        return null;
    }

    /**
     * 购物车中商品数量加减按钮,控制jides数据库中的数量
     * @param pnum:商品数量
     * @param username:用户名
     * @param pid:商品id
     */
    @RequestMapping("/plusItemsNum")
    public void plusItemsNum(@RequestParam String pnum,@RequestParam String username,@RequestParam String pid){
        System.out.println(pnum);
        System.out.println(pid);

        Jedis jedis = jedisPool.getResource();
        Map<String,String> map = jedis.hgetAll(username);
            if(Integer.parseInt(pnum)==0) {
                jedis.hdel(username,pid);
            }else{
                Set set = map.keySet();
                for (Object s :set){
                    if (pid.equals(s)) {
                        map.put(pid, pnum);
                    }
                }
                jedis.hmset(username,map);
            }


    }


    @RequestMapping("/sendEmail")
    public String sendEmail(@RequestParam String username,HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Userinfo ui = usi.selectByName(username);
        int randomNum = (int)((Math.random()*9+1)*100000);
        sendEmail1(ui.getEmail(),randomNum,req,resp);
        return String.valueOf(randomNum);
    }


    //发送验证码函数
    public  void sendEmail1(String emailCount, int randomNum, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String from="925625176@qq.com";//你自己的邮箱
        String host="smtp.qq.com";//本机地址
        //Properties可以确定系统的属性,就是为了寻找我们的host
        Properties properties=System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "25");
        properties.put("mail.smtp.auth","true");//开启代理

        Authenticator aut=new Authenticator() {//创建Authenticator内部类来填入代理的用户名前缀和密码

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("925625176","vjrxrzfelrymbbjf");//填用户名和代理密码

            }

        };

        //创建Session会话,Session是java.mail API最高入口
        Session session=Session.getDefaultInstance(properties,aut);
        //MimeMessage获取session对象,就可以创建要发送的邮箱内容和标题
        MimeMessage message=new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));//设置你自己的邮箱
            message.addRecipients(

                    Message.RecipientType.TO, emailCount);//设置接受邮箱
            message.setSubject("验证码");//设置邮箱标题
            message.setText("您本次的验证码是:"+randomNum);//邮箱内容
            Transport.send(message);//发送邮箱

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 付款后清除商品,删除rides数据库的内容
     * @param username
     * @param pid
     */
    @RequestMapping("/removeItems")
    //页面传过来的桑参数来自jedis数据库
    public boolean removeItems(@RequestParam String username,@RequestParam String pid,@RequestParam String pnum){
        //根据pid取sql数据库查询商品信息
        Productinfo productinfo = psi.selectByPrimaryKey(Integer.parseInt(pid));
        //创建jedis连接,获取用户的购物车所有商品的哈希Map<username,map>集合,map<pid,pnum>
        Jedis jedis = jedisPool.getResource();
        Map<String,String> map = jedis.hgetAll(username);
        //修改库存的值
        if((productinfo.getpNum()-Integer.parseInt(pnum))>=0){
            productinfo.setpNum(productinfo.getpNum()-Integer.parseInt(pnum));
            if(psi.updateByPrimaryKey(productinfo)>0){
                //删除购物车商品信息
                jedis.hdel(username,pid);
                return true;
            }else {
                System.out.println("该商品存在异常,购买失败!");
                return false;
            }
        }else{
            System.out.println("库存不足,购买失败!");
            return false;
        }
    }





}
