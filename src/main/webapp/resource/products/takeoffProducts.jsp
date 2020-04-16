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
    <title>上架下架商品</title>
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
        #table{
            border-collapse: collapse;
            text-align: center;
            margin: auto;
        }
        table tr td{
            border: blue 1px solid;
            width: 120px;
            height: 30px;
        }
    </style>
</head>
<body >
<form method="post" action="">
    <table id="table">
        <tr>
            <td>商品类型</td>
            <td>商品名称</td>
            <td>商品价格</td>
            <td>商品数量</td>
            <td>商品状态</td>
            <td>商品品牌</td>
            <td>商品图片</td>
            <td>商品简介</td>
            <td>商品码</td>
            <td>操作</td>
        </tr>

    </table>
</form>


<script >
    $(function () {
        //查询商品表
        $.ajax({
            url:"takeoffProduction",
            type:"post",
            success:function (data) {
                for (var i=0; i<data.length; i++){
                    if (data[i].status==0){
                       var a = "<tr><td>"+data[i].pType+"</td>"+
                            "<td>"+data[i].pName+"</td>"+
                            "<td>"+data[i].price+"</td>"+
                            "<td>"+data[i].pnum+"</td>"+
                            "<td class='status'>"+data[i].status+"</td>"+
                            "<td>"+data[i].prand+"</td>"+
                            "<td>"+data[i].pic+"</td>"+
                            "<td>"+data[i].intro+"</td>"+
                            "<td>"+data[i].pCode+"</td>"+
                            "<td>"+"<input type='button' value='上架' class='button' pCode='"+data[i].pCode+"'/></td><tr>";
                    }else{
                       var a = "<tr><td>"+data[i].pType+"</td>"+
                            "<td>"+data[i].pName+"</td>"+
                            "<td>"+data[i].price+"</td>"+
                            "<td>"+data[i].pnum+"</td>"+
                            "<td class='status'>"+data[i].status+"</td>"+
                            "<td>"+data[i].prand+"</td>"+
                            "<td>"+data[i].pic+"</td>"+
                            "<td>"+data[i].intro+"</td>"+
                            "<td>"+data[i].pCode+"</td>"+
                           "<td>"+"<input type='button' value='下架' class='button' pCode='"+data[i].pCode+"'/></td><tr>";
                    }
                    $("#table").append(a);
                }

            }
        });
        $("#table").on("click",".button",function () {
            $.ajax({
                url:"fixStatus",
                type:"post",
                data:{
                    "pCode":$(this).attr("pCode")
                },
            });
            if("上架"==$(this).val()){
                $(this).val("下架");
                $(this).parent().parent().children().eq(4).text("1");
            }else{
                $(this).val("上架");
                $(this).parent().parent().children().eq(4).text("0");
            }

        });


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
