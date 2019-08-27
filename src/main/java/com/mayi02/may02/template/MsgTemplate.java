package com.mayi02.may02.template;


//短信模板
public abstract class MsgTemplate {
    public void sendMsg(){
        //1.开始日志报文
        addHeadLog();
        //2.调用具体不同的运营商发送
        htppRequest();
        //3.结束日志报文
        addFootLog();
    }


    private void addHeadLog() {
        System.out.println("调用运营商开始日志");
    }

    public abstract void htppRequest();

    private void addFootLog(){
        System.out.println("调用运营商结束日志");
    }
}
