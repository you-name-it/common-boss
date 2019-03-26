//引入新的插件
layui.config({
    base: '/ui/js/'//拓展模块的根目录
}).extend({
    pca: 'pca'
});

/*layui.config({
    base: '../ui/js/'//拓展模块的根目录
}).extend({
    pca: 'pca'
});*/


//使用自定义的插件pca
layui.use(['form', 'layedit', 'laydate', 'upload', "jquery", "pca"], function () {
    var $ = layui.$
        , form = layui.form
        , pca = layui.pca;
    //带初始值进行初始化
    var initProvince = $("#initProvince").val();
    var initCity = $("#initCity").val();
    var initCounties = $("#initCounties").val();


    pca.init('#province', '#city', '#area', initProvince, initCity, initCounties);

    //不带初始值
     //pca.init('#province', '#city', '#area');

    //输入提示
    $("#address").bind('input propertychange', function () {
        var address = document.getElementById("address").value;
        if (address == "") {
            document.getElementById("addressTip").style.display = "none";
            return;
        }
        var html = '';
        var province = document.getElementById("province").value;
        if (province == "--请选择--") {
            province = '';
        }
        var city = document.getElementById("city").value;
        if (city == "--请选择--") {
            city = '';
        }
        var area = document.getElementById("area").value;
        if (area == "--请选择--") {
            area = '';
        }

        //查询关键字
        var keywords = province + city + area + address;

        //此处使用的是自定义高德地图服务，可以根据情况进行修改
        $.ajax({
            type: "POST",
            url: "http://127.0.0.1:5050/map/inputTip.json",
            cache: false,
            async: false,
            data: {
                "keywords": keywords
            },
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded',
            headers: { 'Authorization': 'bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50IjoiMTg4MTQ4NjgzOTUiLCJ1c2VySWQiOjYyMH0.llXwj27uSclj1ZcZ9nnzZVqBF7yI_LcS8PDCdUXzTBU' },
            success: function (json) {
                var data = json.data.tips;//json数组
                for (var i = 0; i < data.length; i++) {
                    html += '<dd lay-value="" class="addressDd" onclick="ddclick(this)">' + data[i].name + '<span style="color:#9c9a9a;font-size:5px;">&nbsp' + data[i].address + '</span>' + '</dd>';
                }
            }
        });

        document.getElementById("addressTip").innerHTML = html;
        var s = document.getElementById("addressTip").innerHTML;
        if (html == "") {
            document.getElementById("addressTip").style.display = "none";
        } else {
            document.getElementById("addressTip").style.display = "block";
        }

        var lis = document.getElementById("addressDetail").getElementsByTagName("dd");
        for (var i = 0; i < lis.length; i++) {
            if (lis[i].tagName == "DD") {
                lis[i].onclick = (function () {//增加单击事件
                    return function () {
                        document.getElementById("address").value = this.innerText.trim().split(/\s+/)[0];
                        document.getElementById("addressTip").style.display = "none";
                    }
                }
                )
                    (i);
            }

        }

    }
    );

});