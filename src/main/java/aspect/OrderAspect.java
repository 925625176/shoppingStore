package aspect;
import entity.*;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.taglibs.standard.tag.common.fmt.ParseNumberSupport;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import serviceImpl.Orderdetal1ServiceImpl;
import serviceImpl.Orderinfo1ServiceImpl;
import serviceImpl.ProductinfoServiceImpl;
import serviceImpl.UserinfoServiceImpl;
//import serviceimpl.OrderdetailServiceImpl;
//import serviceimpl.OrderinfoServiceImpl;
//import serviceimpl.UserServiceImpl;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
public class OrderAspect {
    @Autowired
    UserinfoServiceImpl usi;
    @Autowired
    ProductinfoServiceImpl psi;
    @Autowired
    Orderinfo1ServiceImpl osi;
    @Autowired
    Orderdetal1ServiceImpl odsi;
    /*
     * 前置通知
     * */
    public void beforeCheck(JoinPoint joinPoint) {
//        Signature sig=joinPoint.getSignature();
//        System.out.println("before at "+sig.getName()+"and arg[0] is ");

    }

    /*
     *后置通知
     *无法获取返回值 。可以通过返回通知获取返回值
     *且后置通知无论方法是不是异常都会执行
     * */
    public void afterCheck(JoinPoint joinPoint) {
//        Signature sig=joinPoint.getSignature();
//joinPoint.getArgs()[0]
//        System.out.println("After at "+sig.getName()+"and arg[0] is ");

    }

    /*
     * 返回通知
     * */
    public void afterReturn(JoinPoint joinPoint,Object res) {

        if(res.toString().equals("true")) {
            System.out.println("**********" + joinPoint.getArgs()[0]);//username
            System.out.println("*********" + joinPoint.getArgs()[1]);//pid
            System.out.println("***********" + joinPoint.getArgs()[2]);//pnum
            //根据username获取Userinfo表的信息
            Userinfo ui = usi.selectByName((String)joinPoint.getArgs()[0]);
            System.out.println("=============uId"+ui.getuId());
            Productinfo pi = psi.selectByPrimaryKey(Integer.parseInt((String)joinPoint.getArgs()[1]));
            System.out.println(pi);
            Orderinfo1 oi = osi.selectByuserid(ui.getuId());
            if(oi==null){
                System.out.println("1234");
                //订单表中插入数据(oid主键自增,userid值为userinfo中的uId)
                oi=new Orderinfo1();
                oi.setUserid(ui.getuId());
                osi.insert(oi);
                //获取oid的值用于存入附表的wj
                System.out.println("=============oId"+osi.selectByuserid(ui.getuId()).getoId());
                System.out.println("=============oId"+oi.getoId());
//                订单详情表中插入数据
                Orderdetail1 od = new Orderdetail1();
                od.setUserid(ui.getuId());
                od.setpId(pi.getpId());
                od.setOrderbh(System.currentTimeMillis()+ui.getUsername());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                od.setOrdertime(sdf.format(new Date()));
                od.setpName(pi.getpName());
                od.setpPrice(pi.getPrice());
                od.setOdNum(Integer.parseInt((String)joinPoint.getArgs()[2]));
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&"+oi.getoId());
                od.setWj(oi.getoId());
                odsi.insert(od);
            }else {

            }


        }



//            Orderinfo oi=new Orderinfo();
//            Orderdetail od=new Orderdetail();
//            Userinfo ui=new Userinfo();
//            oi.setUserid(usi.selectUidByUsername((String) joinPoint.getArgs()[0]));
//            oi.setStatus(0);
//            Date date=new Date();
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            oi.setOrdertime(sdf.format(date));
//
//
//            int randomNum=(int)((Math.random()*9+1)*100000);
//            String realNum=""+randomNum;
//            oi.setPid(Integer.parseInt(realNum));
//
//            od.setOdId(Integer.parseInt(realNum));
//            od.setpId((Integer)joinPoint.getArgs()[1]);
//            od.setOdNum((Integer)joinPoint.getArgs()[2]);
//
//            osi.insert(oi);
//            odsi.insert(od);
//



        Signature sig=joinPoint.getSignature();
        System.out.println("After at "+sig.getName()+"return. res= "+res);

   }


    /*
     * 异常通知
     * 注意如果这个方法的参数：假设异常类型为数学除零的异常
     * afterThrow(JoinPoint joinPoint,空指针异常类 ex) 然后我这里写了空指针异常
     * 那afterThrow这个方法就执行不了 因为类型不对
     * */
    public void afterThrow(JoinPoint joinPoint,Exception ex) {

        Signature sig=joinPoint.getSignature();
        System.out.println("After at "+sig.getName()+"Throw. message= ");
        System.out.println(ex.getMessage());
    }

    /*
     * 环绕通知
     * 环绕通知就等于整个代理过程交给你自己处理  连被代理对象的要执行的目标方法要不要执行也取决你
     * 上面几个通知比较像 处理目标方法调用的某个时刻的 处理过程
     * */
    public Object aroundMethod(ProceedingJoinPoint point){

        Object result = null;
        String methodName = point.getSignature().getName();
        try {
            //前置通知
            System.out.println("我是前置通知,即将调用"+ methodName);
            //执行目标方法
            result = point.proceed();
            //后置通知
            System.out.println("我是后置通知");
        } catch (Throwable e) {
            //异常通知
            System.out.println("我是异常通知,异常信息是"+e);
            throw new RuntimeException(e);
        }
        //返回通知
        System.out.println("我是返回通知,方法返回值是"+result);
        return result;
    }
}
