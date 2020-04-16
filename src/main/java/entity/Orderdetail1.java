package entity;

public class Orderdetail1 {
    private Integer odId;

    private Integer userid;

    private Integer pId;

    private String orderbh;

    private String ordertime;

    private String pName;

    private Double pPrice;

    private Integer odNum;
    private Integer wj;

    public Integer getOdId() {
        return odId;
    }

    public void setOdId(Integer odId) {
        this.odId = odId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getOrderbh() {
        return orderbh;
    }

    public void setOrderbh(String orderbh) {
        this.orderbh = orderbh == null ? null : orderbh.trim();
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime == null ? null : ordertime.trim();
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public Double getpPrice() {
        return pPrice;
    }

    public void setpPrice(Double pPrice) {
        this.pPrice = pPrice;
    }

    public Integer getOdNum() {
        return odNum;
    }

    public void setOdNum(Integer odNum) {
        this.odNum = odNum;
    }

    public Integer getWj(Integer wj) {
        return wj;
    }

    public void setWj(Integer wj) {
        this.wj = wj;
    }

    @Override
    public String toString() {
        return "Orderdetail1{" +
                "odId=" + odId +
                ", userid=" + userid +
                ", pId=" + pId +
                ", orderbh='" + orderbh + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", pName='" + pName + '\'' +
                ", pPrice=" + pPrice +
                ", odNum=" + odNum +
                ", wj=" + wj +
                '}';
    }
}