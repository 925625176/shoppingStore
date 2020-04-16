package controller;

import entity.Productinfo;
import jdk.internal.util.xml.impl.Input;
import jdk.net.SocketFlow;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import serviceImpl.ProductinfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductinfoController {
    @Autowired
    ProductinfoServiceImpl psi;

    @RequestMapping("/getPtype")
    public List<Productinfo> getPtype(){
        return psi.getPtype();
    }


    /**
     * 根据类型查找
     * @param pType
     * @return
     */
    @RequestMapping("/selectByP_type")
    public List<Productinfo> selectByP_type(@RequestParam String pType) {
        List<Productinfo> a = psi.selectByP_type(pType);
        return a;
    }

//    @RequestMapping("/deleteProductNum")
//    public int deleteProductNum(@RequestParam String username,@RequestParam String pid,@RequestParam String pnum){
//        Productinfo productinfo = psi.selectByPrimaryKey(Integer.parseInt(pid));
//        return productinfo.getpNum()-Integer.parseInt(pnum);
//
//
//
//    }

    /**
     * 增加商品
     */
    //接收form表单数据
    @RequestMapping("/addProduct")
    public String addproducts(@RequestParam Integer p_code, @RequestParam String p_name, @RequestParam String p_type, @RequestParam String brand,
                              @RequestParam String p_num, @RequestParam String price, @RequestParam String intro,
                              @RequestParam(value = "pic")MultipartFile multipartFile, /*图片格式接受pic为jsp页面中相应的name值*/HttpServletRequest req) throws Exception {
        Productinfo p = new Productinfo();
        p.setpCode(p_code);
        p.setpName(p_name);
        p.setpType(p_type);
        p.setBrand(brand);
        p.setpNum(Integer.parseInt(p_num));
        p.setPrice(Double.valueOf(price));


        //获取文件的原始名
        System.out.println(multipartFile.getName());
        System.out.println("文件名为:========"+multipartFile.getOriginalFilename());
        //文件新名字为当前时间的毫秒值+原始名,防止上传的文件重名
        String nname = System.currentTimeMillis()+multipartFile.getOriginalFilename();

        //获取项目路径
        String path = req.getSession().getServletContext().getRealPath("/");
        InputStream input = multipartFile.getInputStream();//获取输入流用于读取图片
        //创建用于储存图片的文件路径
        String lastName = path+"\\image\\"+nname;
        File file = new File(path+"/image/");
        //如果文件夹不存在则创建一个
        if(!file.exists()){
            file.mkdir();
        }

        //multipartFile不为空
        if(!multipartFile.isEmpty()){
            BufferedInputStream br = new BufferedInputStream(input);//字符流转缓冲字符流
            OutputStream pw = new BufferedOutputStream(new FileOutputStream(lastName));
            int str;
            while((str = br.read()) != -1){
                pw.write(str);
            }
            pw.flush();
            pw.close();
            br.close();
        }


        p.setPic("/image/"+nname);
        p.setIntro(intro);
        psi.insert(p);

        return "Ok";
    }

    @RequestMapping("/takeoffProduction")
    public List<Productinfo> getallProductinfoOrderByType(){
        return psi.getallProductinfoOrderByType();
    }

    //在方法上直接接收ajax传过来的参数
    @RequestMapping("/fixStatus")
    public void fixStstus(@RequestParam Integer pCode){

        Productinfo p = psi.selectByPCode(pCode);
        if (p.getStatus()==0){
            p.setStatus(1);
        }else {
            p.setStatus(0);
        }
        psi.updateByPrimaryKey(p);

    }




}
