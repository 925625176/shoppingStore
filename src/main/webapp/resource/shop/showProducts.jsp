<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>商品展示</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=basePath%>resource/css/style.css" type="text/css" media="all">
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

    <link href="http://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/demo.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/component.css" />
    <!-- Modernizr is used for flexbox fallback -->
    <script src="<%=basePath%>resource/js/modernizr.custom.js"></script>
<style>
    #tips{
border: solid 2px blue;
        width: 180px;
        height: 150px;
        font-size: 18px;
        position: fixed;
        top: 410px;
        left: 610px;
        display: none;
    }
</style>
</head>
<body>

<!-- Compare basket -->
<div class="compare-basket">
    <button class="action action--button action--compare"><i class="fa fa-check"></i><span class="action__text">Compare</span></button>
</div>
<!-- Main view -->
<div class="view">
    <!-- Product grid -->
    <section class="grid">
        <!-- Products -->



        

    </section>
</div><!-- /view -->
<!-- product compare wrapper -->
<section class="compare">
    <button class="action action--close"><i class="fa fa-remove"></i><span class="action__text action__text--invisible">Close comparison overlay</span></button>
</section>
<div id="tips"></div>



<script >
    $(function () {
        $.ajax({
            url:"selectByP_type",
            type:"post",
            data:{
                "pType":getQueryString("pType")
            },
            success:function(data) {
                for(var i = 0;i<data.length;i++){

                    var a = "<div class='product'>\n" +
                        "            <div class='product__info'>\n" +
                        "                <img class='product__image' src='<%=basePath%>"+data[i].pic+"' alt='Product 1' />\n" +
                        "                <h3 class='product__title'>"+data[i].pName+"</h3>\n" +
                        "                <span class='product__region extra highlight'>"+data[i].intro+"</span>\n" +
                        "                <span class='product__price highlight'>"+data[i].price+"</span>\n" +
                        "                <button class='action action--button action--buy' pid='"+data[i].pId+"'><i class='fa fa-shopping-cart'></i><span class='action__text'>Add to cart</span></button>\n" +
                        "            </div>\n" +
                        "            <label class='action action--compare-add'><input class='check-hidden' type='checkbox' /><i class='fa fa-plus'></i><i class='fa fa-check'></i><span class='action__text action__text--invisible'>Add to compare</span></label>\n" +
                        "        </div>";
                        
                    $(".grid").prepend(a);
                }
                loadjscssfile("<%=basePath%>resource/js/classie.js","js");
                loadjscssfile("<%=basePath%>resource/js/main.js","js");
            }
        });
        $(".grid").on("click",".action--buy",function () {
            chickLogin();
            $.ajax({
                url:"addCar",
                type:"post",
                data:{
                    "username":getQueryString("username"),
                    "pid":$(this).attr("pid")
                },
                success:function (data) {

                    if(data=="yes"){
                        $("#tips").html("加入购物车成功!");

                    }else{
                        $("#tips").html("加入购物车失败!");
                    }
                    $("#tips").show();
                    window.setTimeout(showTips,3000);
                }
            });


        });
        //
        function showTips() {
            $("#tips").hide();
        }


        //    检查是否登录
        function chickLogin() {
            var username=getQueryString("username");
            <%--if(username==null){--%>
            <%--    window.location.href="<%=basePath%>resource/shop/navigator.jsp;"--%>
            <%--}else{--%>
            <%--    window.location.href="<%=basePath%>resource/login/login.jsp;"--%>
            <%--}--%>
            if(getQueryString("username")!="null"){
                //$("#myframe").attr("src","<%=basePath%>resource/shop/showProducts.jsp?pType="+$(this).children().children().children().text()+"&username="+getQueryString("username"));
            }else{
                window.location.href="<%=basePath%>resource/login/login.jsp";
            }
        }
//根据参数名称获取url传过来的参数
        function getQueryString(name){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)
                return  decodeURI(r[2]);
            return null;
        }
//执行顺序动态加载
        function loadjscssfile(filename, filetype) {
            if (filetype == "js") { //判定文件类型
                var fileref = document.createElement('script');//创建标签
                fileref.setAttribute("type", "text/javascript");//定义属性type的值为text/javascript
                fileref.setAttribute("src", filename);//文件的地址
            }
            else if (filetype == "css") { //判定文件类型
                var fileref = document.createElement("link");
                fileref.setAttribute("rel", "stylesheet");
                fileref.setAttribute("type", "text/css");
                fileref.setAttribute("href", filename);
            }
            if (typeof fileref != "undefined")
                document.getElementsByTagName("head")[0].appendChild(fileref);
        }

    });

</script>
</body>
</html>
