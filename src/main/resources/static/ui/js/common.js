$(function() {
    //header
    //导航下拉隐藏
    var preTop = $(window).scrollTop();
    $(window).scroll(function() {
        var curTop = $(window).scrollTop();
        if (preTop < curTop) {
            $(".header").stop().removeClass("on");
        } else {
            $(".header").stop().addClass("on");
        }
        preTop = curTop;
    });

    //搜索点击显示隐藏
    var flag = true;
    $("#search").click(function() {
        if (flag) {
            search();
            flag = false;
        } else {
            close();
            flag = true;
        }
    });
    $(".searchleft, .searchright").click(function() {
        close();
        flag = true;
    });

    function search() {
        $("html,body").css({
            height: $(window).height(),
            overflow: "hidden"
        });
        $(".header").css("right", "8px");
        $("#search").find('.icon-search').hide();
        $(".icon-guanbi1").show();
        $("#searchDiv").show();
    }

    function close() {
        $("html,body").css({
            height: "auto",
            overflow: "auto"
        });
        $(".header").css("right", "0px");
        $('.icon-search').show();
        $(".icon-guanbi1").hide();
        $("#searchDiv").hide();
    }

    //footer
    //意见反馈弹窗
    function iframeBox(obj) {
        var str = '<div id="layer-feedBack" class="layer-feedBack">' +
            '<div id="layer-feedBack-cont" class="layer-feedBack-cont">' +
            '<div class="layer-feedBack-close">' +
            '<span class="iconfont icon-close_light"></span>' +
            '</div>' +
            '<iframe scrolling="no" src="' + obj.url + '" width="100%" height="100%" frameborder="0"></iframe>' +
            '</div>' +
            '</div>';
        if ($("#layer-feedBack").length <= 0) {
            $("body").append(str);
            $("#layer-feedBack").show();
        } else {
            $("#layer-feedBack").show();
        }
        $("#layer-feedBack-cont").click(function() {
            $("#layer-feedBack").hide();
        });
    }

    $("#feed").click(function() {
        iframeBox({
            url: 'http://#alasin#/feedback'
        });
    });

    //微信弹窗
    $(".weChat").click(function() {
        $(".weChat_show").fadeIn();
        $(".OpacityBackGround").fadeIn();
    });

    $(".weChat_close").click(function() {
        $(".weChat_show").fadeOut();
        $(".OpacityBackGround").fadeOut();
    });

    /*$("#button-right").click(function () {

       var classname = document.getElementById("button-right").className;

        alert(classname)

        if(classname == 'layui-inline button-right'){
            $("#button-right").setAttribute('class','layui-inline button-right');
        }else{
            $("#button-right").setAttribute('class','layui-inline button-right-click');
        }


    })*/

    
});
