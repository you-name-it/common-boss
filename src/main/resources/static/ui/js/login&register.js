//register

$(function () {
    var $Name = $('#Name'); //用户名
    var $Phone = $('#Phone'); //手机号
    var $PassWord = $('#Password'); //密码
    var $Verifycode = $('#TelToken'); //手机验证码
    var $Protocol = $('#protocol'); //用户协议
    var $Email = $('#Email'); //邮箱
    var $emailVerifycode = $('#email-verifycode'); //邮箱验证码
    var $newPassword = $('#Password'); //新密码
    var $repeatPassword = $('#RePassword'); //重复新密码

    var i = 0;
    function reflashImg() {
        var img = $('#ImgToken');
        img.attr('src', img.attr('src') + "&exp=" + (i++));
    }

    var countdown = 60;
    function settime(e) {
        if (countdown == 0) {
            e.removeAttribute("disabled");
            e.text = "发送验证码";
            e.style.borderColor = "#ff7000";
            e.style.color = "#ff7000";
            countdown = 60;

            return;
        } else {
            e.setAttribute("disabled", "true");
            e.text = "重新发送(" + countdown + "s" + ")";
            e.style.borderColor = "#ccc";
            e.style.color = "#999";
            countdown--;
            //return;
        }

        var flag = setInterval(function () {
            $(e).text("重新发送(" + countdown + "s" + ")");
            if (countdown-- == 0) {
                clearInterval(flag);
                e.removeAttribute("disabled");
                e.text = "发送验证码";
                e.style.borderColor = "#ff7000";
                e.style.color = "#ff7000";
                countdown = 60;
            }
        }, 1000);

        $.ajax({
            url: $(e).data('posturl'),
            type: "post",
            data: { TelNo: $('#Phone').val(), Token: $('#imgCode').val() },
            success: function (data) {
                if (!data.result) {
                    clearInterval(flag);
                    e.removeAttribute("disabled");
                    e.text = "发送验证码";
                    e.style.borderColor = "#ff7000";
                    e.style.color = "#ff7000";
                    countdown = 60;
                }
                else {
                    //$('.modal').modal("hide");
                }
                alert(data.message);
            }
        });

    }

    //login
    var $log_username = $('#EmailOrTelNo'),
        $log_password = $('#Password'),
        $log_error = $('.error1');
    // console.log($username)

    $('.btn-submit').click(function () {
        if ($log_username.val() == "" && $log_password.val() !== "") {
            $log_error.show().html('<i class="iconfont icon-error"></i>请输入账号');
            return false;
        } else if ($log_username.val() !== "" && $log_password.val() == "") {
            $log_error.show().html('<i class="iconfont icon-error"></i>请输入密码');
            return false;
        } else if ($log_username.val() == "" && $log_password.val() == "") {
            $log_error.show().html('<i class="iconfont icon-error"></i>请输入账号密码');
            return false;
        }
        return true;
    });




    //用户名
    if ($Name) {
        $Name.blur(function () {
            checkName();
        });
    }
    function checkName() {
        var RegularName = /^[\u4e00-\u9fa5_a-zA-Z0-9\s]+$/;
        if ($Name.val() == "") {
            $Name.addClass('error-border');
            $Name.siblings('.error2').text("请设置用户名称");
            $Name.siblings('.error2').show();
            return false;
        } else if (!(RegularName.test($Name.val()))) {
            $Name.addClass('error-border');
            $Name.siblings('.error2').text("用户名不符合格式");
            $Name.siblings('.error2').show();
            return false;
        } else {
            $Name.removeClass('error-border');
            $Name.siblings('.error2').hide();
            return true;
        }
    }

    //手机号
    if ($Phone) {
        $Phone.blur(function () {
            checkPhone();
        });
    }
    function checkPhone() {
        var RegularPhone = /^1[3456789]\d{9}$/;
        if ($Phone.val() == "") {
            $Phone.addClass('error-border');
            $Phone.siblings('.error2').text("请输入手机号码");
            $Phone.siblings('.error2').show();
            $Phone.siblings('.text-danger').remove();
            return false;
        } else if (!(RegularPhone.test($Phone.val()))) {
            $Phone.addClass('error-border');
            $Phone.siblings('.error2').text("手机格式错误");
            $Phone.siblings('.error2').show();
            $Phone.siblings('.text-danger').remove();
            return false;
        } else {
            $Phone.removeClass('error-border');
            $Phone.siblings('.error2').hide();
            $Phone.siblings('.text-danger').remove();
            return true;
        }
    }

    //验证码
    if ($Verifycode) {
        $Verifycode.blur(function () {
            checkCode();
        });
    }
    function checkCode() {
        var RegularCode = /^1[34578]\d{9}$/;
        if ($Verifycode.val() == "") {
            $Verifycode.addClass('error-border');
            $Verifycode.siblings('.error2').text("请输入验证码");
            $Verifycode.siblings('.error2').show();
            $Verifycode.siblings('.text-danger').remove();
            return false;
            // }else if(!(RegularCode.test($Verifycode.val()))){
            //     $Verifycode.addClass('error-border');
            //     $Verifycode.siblings('.error2').text("验证码错误");
            //     $Verifycode.siblings('.error2').show();
            //     return false;
        } else {
            $Verifycode.removeClass('error-border');
            $Verifycode.siblings('.error2').hide();
            return true;
        }
    }
    //发送手机验证码
    $('.btn-send-phone').click(function () {
        if ($(this).attr("disabled")) return;
        if (!checkPhone()) return;
        settime(this);
    });


    //密码
    if ($PassWord) {
        $PassWord.keypress(function () {
            checkPassWord();
        });
        $PassWord.blur(function () {
            checkPassWord();
        });
    }
    function checkPassWord() {
        var RegularPassWord = /^([a-z0-9\.\@\!\#\$\%\^\&\*\(\)]){6,20}$/i;
        if ($PassWord.val() == "") {
            $PassWord.addClass('error-border');
            $PassWord.siblings('.error2').text("请设置6-20位密码");
            $PassWord.siblings('.error2').show();
            $PassWord.siblings('.text-danger').remove();
            return false;
        } else if (!(RegularPassWord.test($PassWord.val()))) {
            $PassWord.addClass('error-border');
            $PassWord.siblings('.error2').text("密码格式错误");
            $PassWord.siblings('.error2').show();
            $PassWord.siblings('.text-danger').remove();
            return false;
        } else {
            $PassWord.removeClass('error-border');
            $PassWord.siblings('.error2').hide();
            $PassWord.siblings('.text-danger').remove();
            return true;
        }
    }


    //用户协议
    $('#protocol').click(function () {
        setSelectUserNo(this);
    });
    function setSelectUserNo(radioObj) {
        var radioCheck = $(radioObj).val();
        if (radioCheck == "1") {
            $(radioObj).attr("checked", false);
            $(radioObj).val("0");
            return false;
        } else {
            $(radioObj).attr("checked",'checked');
            $(radioObj).val("1");
            return true;
        }
    }

    //注册按钮
    $('.btn-register').click(function () {
        var registerFlag = true;
        if (!checkName()) {
            registerFlag = false;
        }
        if (!checkPhone()) {
            registerFlag = false;
        }
        if (!checkCode()) {
            registerFlag = false;
        }
        if (!checkPassWord()) {
            registerFlag = false;
        }
        if (registerFlag) {
            if ($Protocol.val() == '0') {
                alert('请阅读并同意用户服务协议');
                registerFlag = false;
            } else {
                $(this).text("注册中...");
                registerFlag = true;
            }
        }
        return registerFlag;
    });


    //选择类型找回密码
    $('.forget-form input[type="radio"]').on("change", function () {
        // $(this).siblings('input').removeAttr('checked');
        // $(this).attr('checked','checked');
        // $('.way-wrap').hide();
        // $('.forget-form').find('.' + $(this).attr('data-radio')).show();
        window.location.href = $(this).data('radio');
    });


    //邮箱地址
    if ($Email) {
        $Email.blur(function () {
            checkEmail();
        });
    }
    function checkEmail() {
        var RegularEmail = /^([a-zA-Z0-9_-]+[_|\_|\.]?)+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if ($Email.val() == "") {
            $Email.addClass('error-border');
            $Email.siblings('.error2').text("请输入邮箱地址");
            $Email.siblings('.error2').show();
            return false;
        } else if (!(RegularEmail.test($Email.val()))) {
            $Email.addClass('error-border');
            $Email.siblings('.error2').text("邮箱地址格式错误");
            $Email.siblings('.error2').show();
            return false;
        } else {
            $Email.removeClass('error-border');
            $Email.siblings('.error2').hide();
            return true;
        }
    }

    //邮箱验证码
    if ($emailVerifycode) {
        $emailVerifycode.blur(function () {
            checkEmailCode();
        });
    }
    function checkEmailCode() {
        var RegularCode = /^1[34578]\d{9}$/;
        if ($emailVerifycode.val() == "") {
            $emailVerifycode.addClass('error-border');
            $emailVerifycode.siblings('.error2').text("请输入验证码");
            $emailVerifycode.siblings('.error2').show();
            return false;
            // }else if(!(RegularCode.test($Verifycode.val()))){
            //     $Verifycode.addClass('error-border');
            //     $Verifycode.siblings('.error2').text("验证码错误");
            //     $Verifycode.siblings('.error2').show();
            //     return false;
        } else {
            $emailVerifycode.removeClass('error-border');
            $emailVerifycode.siblings('.error2').hide();
            return true;
        }
    }
    //发送邮箱验证码
    $('.btn-send-email').click(function () {
        if ($(this).attr("disabled")) return;
        if (!checkEmail()) return;
        settime(this);
    });



    $('.btn-next').click(function () {
        var retrieveFlag = true;
        if ($('#retrieve-phone').attr('checked')) {
            if (!checkPhone()) {
                retrieveFlag = false;
            }
            if (!checkCode()) {
                retrieveFlag = false;
            }
        }
        if ($('#retrieve-email').attr('checked')) {
            if (!checkEmail()) {
                retrieveFlag = false;
            }
            if (!checkEmailCode()) {
                retrieveFlag = false;
            }
        }
        // if (retrieveFlag) {
        //     window.location.href = 'forget-2.html';
        // }
        return retrieveFlag;
    });

    //修改新密码
    if ($newPassword) {
        $newPassword.blur(function () {
            checkNewPassWord();
        });
    }
    function checkNewPassWord() {
        var RegularPassWord = /^([a-z0-9\.\@\!\#\$\%\^\&\*\(\)]){6,20}$/i;
        if ($newPassword.val() == "") {
            $newPassword.addClass('error-border');
            $newPassword.siblings('.error2').text("请设置6-20位密码");
            $newPassword.siblings('.error2').show();
            return false;
        } else if (!(RegularPassWord.test($newPassword.val()))) {
            $newPassword.addClass('error-border');
            $newPassword.siblings('.error2').text("密码格式错误");
            $newPassword.siblings('.error2').show();
            return false;
        } else {
            $newPassword.removeClass('error-border');
            $newPassword.siblings('.error2').hide();
            return true;
        }
    }
    //重复密码
    if ($repeatPassword) {
        $repeatPassword.blur(function () {
            checkRepeatPassWord();
        });
    }
    function checkRepeatPassWord() {
        if ($newPassword.val() !== $repeatPassword.val()) {
            $repeatPassword.addClass('error-border');
            $repeatPassword.siblings('.error2').text("两次密码不一致");
            $repeatPassword.siblings('.error2').show();
            return false;
        } else {
            $repeatPassword.removeClass('error-border');
            $repeatPassword.siblings('.error2').hide();
            return true;
        }
    }

    $('.btn-modify').click(function () {
        var modifyFlag = true;
        if (!checkNewPassWord()) {
            modifyFlag = false;
        }
        if (!checkRepeatPassWord()) {
            modifyFlag = false;
        }
        if (modifyFlag) {
            window.location.href = 'forget-3.html';
        }
        return modifyFlag;
    });

    $('.btn-home').click(function () {
        window.location.href = 'http://#alasin#/';
    });
});