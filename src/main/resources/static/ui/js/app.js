var tabCounter = 0;
var $tab = $('#doc-tab');
var $nav = $tab.find('.am-tabs-nav');
var $bd = $tab.find('.am-tabs-bd');


function loadPage(url){

        window.location = "/login";

        /*$(".content").load(url,function(response,status,xhr){
            if(xhr.getResponseHeader('sessionstatus') == 'timeout'){
                window.location = "/login";
            }
        });*/

}

/**
 * 表单提交通用
 * @param formId
 * @param commitUrl
 * @param jumpUrl
 */

function commitNotice(data, commitUrl, jumpUrl) {
    //组装表单数据
    var index;
    $.ajax({
        type : "POST",
        url : commitUrl,
        data : data,
        beforeSend : function() {
            index = layer.load();
        },
        success : function(resultdata) {
            console.log(resultdata);
            layer.close(index);
            if (resultdata.status == 0) {

                    if(jumpUrl!=""){
                        window.location.href = jumpUrl;
                    }

            } else {
                layer.msg(resultdata.message, {
                    icon : 5
                });
            }
        },
        error : function(data, errorMsg) {
            layer.close(index);
            layer.msg(data.responseText, {
                icon : 2
            });
        }
    });
}

function commit(data, commitUrl, jumpUrl) {
    //组装表单数据
    var index;
    $.ajax({
        type : "POST",
        url : commitUrl,
        data : data,
        beforeSend : function() {
            index = layer.load();
        },
        success : function(resultdata) {
        	console.log(resultdata);
        	layer.msg(resultdata.status);
            layer.close(index);
            if (resultdata.status == 0) {

                layer.alert(resultdata.message,{
                    closeBtn: 0
                } ,function(index){

                    if(jumpUrl!=""){
                        window.location.href = jumpUrl;
                    }

                    layer.close(index);
                });

            } else {
                layer.msg(resultdata.message, {
                    icon : 5
                });
            }
        },
        error : function(data, errorMsg) {
            layer.close(index);
            layer.msg(data.responseText, {
                icon : 2
            });
        }
    });
}
/**
 * 删除通用
 * @param nav
 * @param jumpUrl
 */
function del(nav,callback){
	layer.confirm('确认删除吗？', {
        icon : 3,
        title : '删除提示'
    }, function(index, layero) {
        $.ajax({
            type : "DELETE",
            url :  nav,
            dataType : "json",
            success : function(resultdata) {
                if (resultdata.status=="0") {
                    layer.msg(resultdata.message, {
                        icon : 1
                    });
                    if (callback) {
                        callback();
                    }
                } else {
                    layer.msg(resultdata.message, {
                        icon : 0
                    });
                }
            },
            error : function(errorMsg) {
                layer.msg('服务器未响应,请稍后再试', {
                    icon : 3
                });
            }
        });
        layer.close(index);
    });
}
/**
 * 编辑通用
 * @param nav
 */
function edit(nav){
	  $.ajax({
          type : "PUT",
          url :  nav,
          dataType : "json",
          error : function(errorMsg) {
              layer.msg('服务器未响应,请稍后再试', {
                  icon : 3
              });
          }
      });
}

/**
 * 获取多选选中
 * @returns
 */
function getCheckAll(data){
	 var checkIds = "";
     $(data).each(function(i){
	      if(0==i){
	    	  checkIds = data[i].id;
	      }else{
	    	  checkIds += (","+data[i].id);
	      }
     });
     return checkIds;
}

function initUpFileOne(id,showId,label,initImg){
	$('#'+id+'').fileinput({
		 showUpload : true,
	     showRemove : false,
	     showCaption: false,
		 showPreview : true, //是否显示预览'
	     language : 'zh',
		 uploadUrl: 'upload/uploadImg',
		 allowedPreviewTypes : [ 'image' ],
	     allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
	     browseClass: "am-btn am-btn-danger am-btn-sm", //按钮样式
	     dropZoneEnabled: false,//是否显示拖拽区域
		 uploadAsync:false,
		 uploadClass:'btn btn-default uploadBtnColor',
		 browseIcon:'<i class="am-icon-cloud-upload"></i>',
		 browseLabel:label,
		 removeIcon: '<i class="am-icon-trash"></i>',
		 layoutTemplates:{'footer':''},
		 initialPreview:initImg,
	}).on("filebatchuploadsuccess", function(event, data) {
	    if(data.response.status==0){
	    	$('#'+showId+'').val(data.response.data);
	    	layer.msg('上传成功', {
	    		  icon: 1,
	    		  time: 2000 //2秒关闭（如果不配置，默认是3秒）
	    		}); 
	    } else{
	    	layer.msg('服务器错误，请稍后重试~', {
	    		  icon: 3,
	    		  time: 3000 //2秒关闭（如果不配置，默认是3秒）
	    		}); 
	    }
	}); 
}


/**
 * 锁定通用
 * @param nav
 * @param jumpUrl
 */
function lock(nav,callback){
	layer.confirm('确认锁定该账号吗？', {
        icon : 3,
        title : '锁定提示'
    }, function(index, layero) {
        $.ajax({
            type : "POST",
            url :  nav,
            dataType : "json",
            success : function(resultdata) {
                if (resultdata.status=="0") {
                    layer.msg(resultdata.message, {
                        icon : 1
                    });
                    if (callback) {
                        callback();
                    }
                } else {
                    layer.msg(resultdata.message, {
                        icon : 0
                    });
                }
            },
            error : function(errorMsg) {
                layer.msg('服务器未响应,请稍后再试', {
                    icon : 3
                });
            }
        });
        layer.close(index);
    });
}


/**
 * 解锁通用
 * @param nav
 * @param jumpUrl
 */
function unlock(nav,callback){
	layer.confirm('确认解锁该账号吗？', {
        icon : 3,
        title : '解锁提示'
    }, function(index, layero) {
        $.ajax({
            type : "POST",
            url :  nav,
            dataType : "json",
            success : function(resultdata) {
                if (resultdata.status=="0") {
                    layer.msg(resultdata.message, {
                        icon : 1
                    });
                    if (callback) {
                        callback();
                    }
                } else {
                    layer.msg(resultdata.message, {
                        icon : 0
                    });
                }
            },
            error : function(errorMsg) {
                layer.msg('服务器未响应,请稍后再试', {
                    icon : 3
                });
            }
        });
        layer.close(index);
    });
}


/**
 * 绑定通用
 * @param nav
 * @param jumpUrl
 */
function unbing(nav,callback){
	layer.confirm('确认要解绑该玩家吗？', {
        icon : 3,
        title : '解绑提示'
    }, function(index, layero) {
        $.ajax({
            type : "POST",
            url :  nav,
            dataType : "json",
            success : function(resultdata) {
                if (resultdata.status=="0") {
                    layer.msg(resultdata.message, {
                        icon : 1
                    });
                    if (callback) {
                        callback();
                    }
                } else {
                    layer.msg(resultdata.message, {
                        icon : 0
                    });
                }
            },
            error : function(errorMsg) {
                layer.msg('服务器未响应,请稍后再试', {
                    icon : 3
                });
            }
        });
        layer.close(index);
    });
}


/**
 * 重置密码通用
 * @param nav
 * @param jumpUrl
 */
function restPwd(nav,callback){
	layer.confirm('确认重置密码吗？', {
        icon : 3,
        title : '重置密码提示'
    }, function(index, layero) {
        $.ajax({
            type : "POST",
            url :  nav,
            dataType : "json",
            success : function(resultdata) {
                if (resultdata.status=="0") {
                    layer.msg(resultdata.message, {
                        icon : 1
                    });
                    if (callback) {
                        callback();
                    }
                } else {
                    layer.msg(resultdata.message, {
                        icon : 0
                    });
                }
            },
            error : function(errorMsg) {
                layer.msg('服务器未响应,请稍后再试', {
                    icon : 3
                });
            }
        });
        layer.close(index);
    });
}

function general(nav,callback,remind,remindTitle){
                	layer.confirm(remind, {
                        icon : 3,
                        title : remindTitle
                    }, function(index, layero) {
                        $.ajax({
                            type : "POST",
                            url :  nav,
                            dataType : "json",
                            success : function(resultdata) {
                                if (resultdata.status=="0") {
                                    layer.msg(resultdata.message, {
                                        icon : 1
                                    });
                                    if (callback) {
                                        callback();
                                    }
                                } else {
                                    layer.msg(resultdata.message, {
                                        icon : 0
                                    });
                                }
                            },
                            error : function(errorMsg) {
                                layer.msg('服务器未响应,请稍后再试', {
                                    icon : 3
                                });
                            }
                        });
                        layer.close(index);
                    });
}


//登陆
function login(name,password,rememberMe){

    if(name==null || name==""){
        layer.msg("请输入邮箱或者手机号码进行登陆！", {
            icon :2
        });
        return false;
    }

    if(password == null || password == ""){
        layer.msg("请输入登陆密码！", {
            icon :2
        });
        return false;
    }

    $.ajax({
        type : "POST",
        url :  "/dologin",
        data : {
            username : name,
            password : password,
            rememberMe : rememberMe
        },
        success : function(resultdata) {
            if(resultdata.msg!=null){
                layer.msg(resultdata.msg, {
                    icon : 6
                });
            }else{
                /*layer.msg("登陆成功", {
                    icon : 1
                });*/
                window.location.href = resultdata;
                /*layer.alert('登陆成功',{
                    closeBtn: 0
                } ,function(index){

                    layer.close(index);
                });*/

            }

        },
        error : function(errorMsg) {
            layer.msg('服务器未响应,请稍后再试', {
                icon : 3
            });
        }
    });
}