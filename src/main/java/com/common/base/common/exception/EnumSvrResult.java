package com.common.base.common.exception;

/**
 *
 */
public enum EnumSvrResult {

    /**
     * 操作成功
     * */
    OK("0", "成功！"),
    /**
     *喔唷！服务器错误！
     * */
    ERROR("1", "喔唷！服务器错误."),

    ERROR_PHONE_IS_USED("2", "该账号已被绑定."),

    ERROR_PARAMS("4","参数错误."),

    ERROR_ACCOUNT_PASSWORD_NULL("5","用户名或密码不能为空."),

    ERROR_ACCOUNT_PASSWORD_ERROR("6","用户名或密码错误."),

    ERROR_INTERFACE("7","接口调用失败."),

    ERROR_RESOURCE_DELETE("8","请先删除该资源的子项."),

    ERROR_UPLOAD_IMG("9","图片上传失败."),

    SEND_HTTP_ERROR("10","服务器未响应,请稍后再试")
    ;



    private String val;
    private String name;

    EnumSvrResult(String val, String name) {
        this.val = val;
        this.name = name;
    }

    public String getVal() {
        return this.val;
    }

    public String getName() {
        return this.name;
    }
}
