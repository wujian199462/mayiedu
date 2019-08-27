package com.mayi02.may02;

//rpc返回code msg
public enum HttpEnum {
    http_200(200,"请求成功"),
    http_500(500,"请求失败");
    HttpEnum(Integer httpCode,String httpMsg){
        //执行基础
        System.out.println("httpENum 初始化");
        this.httpCode = httpCode;
        this.httpMsg = httpMsg;
    }
    private Integer httpCode;
    private String httpMsg;

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getHttpMsg() {
        return httpMsg;
    }

    public void setHttpMsg(String httpMsg) {
        this.httpMsg = httpMsg;
    }

    public static void main(String[] args) {
        System.out.println(HttpEnum.http_200.httpCode);
        System.out.println(HttpEnum.http_200.httpMsg);
    }
}
