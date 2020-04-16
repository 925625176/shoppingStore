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
    <title>添加商品</title>
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
        table{
            text-align: center;
            margin: auto;
            border-collapse: collapse;
        }
        table tr td{
            border: blue 1px solid;

            height: 30px;
        }
        .intro{
            height: 30px;
        }
        input{
            width: 120px;
            height: 30px;
        }
       #div1{
           position: absolute;
           top: 460px;
           left: 1190px;
        }
    </style>
</head>
<body>
<form method="post" action="/addproducts">
    <h3>添加商品</h3>
    </hr>
    <table >
        <tr>
            <td>商品码</td>
            <td>商品名称</td>
            <td>商品类型</td>
            <td>商品品牌</td>
            <td>商品图片</td>
            <td>商品数量</td>
            <td>商品价格</td>
            <td>折价商品</td>
            <td>商品简介</td>
            <td>商品状态</td>
        </tr>
        <tr>
        <td><input type="text" name="pCode"></td>
        <td><input type="text" name="pName"></td>
        <td><input type="text" name="pType"></td>
        <td><input type="text" name="brand"></td>
        <td>
            <input type="file" name="pic">
        </td>
        <td><input type="text" name="pNum"></td>
        <td><input type="text" name="price"></td>
        <td><input type="text" name="sale"></td>
        <td>
            <textarea name="intro" class="intro"></textarea>
        </td>
        <td><input type="text" name="status"></td>
    </tr>
        <tr>
            <td><input type="text" name="pCode"></td>
            <td><input type="text" name="pName"></td>
            <td><input type="text" name="pType"></td>
            <td><input type="text" name="brand"></td>
            <td>
                <input type="file" name="pic">
            </td>
            <td><input type="text" name="pNum"></td>
            <td><input type="text" name="price"></td>
            <td><input type="text" name="sale"></td>
            <td>
                <textarea name="intro" class="intro"></textarea>
            </td>
            <td><input type="text" name="status"></td>
        </tr>
        <tr>
            <td><input type="text" name="pCode"></td>
            <td><input type="text" name="pName"></td>
            <td><input type="text" name="pType"></td>
            <td><input type="text" name="brand"></td>
            <td>
                <input type="file" name="pic">
            </td>
            <td><input type="text" name="pNum"></td>
            <td><input type="text" name="price"></td>
            <td><input type="text" name="sale"></td>
            <td>
                <textarea name="intro" class="intro"></textarea>
            </td>
            <td><input type="text" name="status"></td>
        </tr>
        <tr>
            <td><input type="text" name="pCode"></td>
            <td><input type="text" name="pName"></td>
            <td><input type="text" name="pType"></td>
            <td><input type="text" name="brand"></td>
            <td>
                <input type="file" name="pic">
            </td>
            <td><input type="text" name="pNum"></td>
            <td><input type="text" name="price"></td>
            <td><input type="text" name="sale"></td>
            <td>
                <textarea name="intro" class="intro"></textarea>
            </td>
            <td><input type="text" name="status"></td>
        </tr>
        <tr>
            <td><input type="text" name="pCode"></td>
            <td><input type="text" name="pName"></td>
            <td><input type="text" name="pType"></td>
            <td><input type="text" name="brand"></td>
            <td>
                <input type="file" name="pic">
            </td>
            <td><input type="text" name="pNum"></td>
            <td><input type="text" name="price"></td>
            <td><input type="text" name="sale"></td>
            <td>
                <textarea name="intro" class="intro"></textarea>
            </td>
            <td><input type="text" name="status"></td>
        </tr>
        <tr>
            <td><input type="text" name="pCode"></td>
            <td><input type="text" name="pName"></td>
            <td><input type="text" name="pType"></td>
            <td><input type="text" name="brand"></td>
            <td>
                <input type="file" name="pic">
            </td>
            <td><input type="text" name="pNum"></td>
            <td><input type="text" name="price"></td>
            <td><input type="text" name="sale"></td>
            <td>
                <textarea name="intro" class="intro"></textarea>
            </td>
            <td><input type="text" name="status"></td>
        </tr>
        <tr>
            <td><input type="text" name="pCode"></td>
            <td><input type="text" name="pName"></td>
            <td><input type="text" name="pType"></td>
            <td><input type="text" name="brand"></td>
            <td>
                <input type="file" name="pic">
            </td>
            <td><input type="text" name="pNum"></td>
            <td><input type="text" name="price"></td>
            <td><input type="text" name="sale"></td>
            <td>
                <textarea name="intro" class="intro"></textarea>
            </td>
            <td><input type="text" name="status"></td>
        </tr>
        <tr>
            <td><input type="text" name="pCode"></td>
            <td><input type="text" name="pName"></td>
            <td><input type="text" name="pType"></td>
            <td><input type="text" name="brand"></td>
            <td>
                <input type="file" name="pic">
            </td>
            <td><input type="text" name="pNum"></td>
            <td><input type="text" name="price"></td>
            <td><input type="text" name="sale"></td>
            <td>
                <textarea name="intro" class="intro"></textarea>
            </td>
            <td><input type="text" name="status"></td>
        </tr>
        <tr>
            <td><input type="text" name="pCode"></td>
            <td><input type="text" name="pName"></td>
            <td><input type="text" name="pType"></td>
            <td><input type="text" name="brand"></td>
            <td>
                <input type="file" name="pic">
            </td>
            <td><input type="text" name="pNum"></td>
            <td><input type="text" name="price"></td>
            <td><input type="text" name="sale"></td>
            <td>
                <textarea name="intro" class="intro"></textarea>
            </td>
            <td><input type="text" name="status"></td>
        </tr>
        <tr>
            <td><input type="text" name="pCode"></td>
            <td><input type="text" name="pName"></td>
            <td><input type="text" name="pType"></td>
            <td><input type="text" name="brand"></td>
            <td>
                <input type="file" name="pic">
            </td>
            <td><input type="text" name="pNum"></td>
            <td><input type="text" name="price"></td>
            <td><input type="text" name="sale"></td>
            <td>
                <textarea name="intro" class="intro"></textarea>
            </td>
            <td><input type="text" name="status"></td>
        </tr>

    </table>
    <div id="div1">
        <input type="button" value="添加商品" id="button">
    </div>
</form>


<script >
    $(function () {
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
