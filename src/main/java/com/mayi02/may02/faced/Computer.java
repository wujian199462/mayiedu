package com.mayi02.may02.faced;

public class Computer {
    EmailMsgService emailMsgService;
    SmsMsgService smsMsgService;
    WeiXinMsgService weiXinMsgService;

    public Computer() {
        emailMsgService = new EmailMsgServiceImpl();
        smsMsgService = new SmsMsgServiceImpl();
        weiXinMsgService = new WeiXinMsgServiceImpl();
    }

    public void sendMsg(){
        emailMsgService.sendMsg();
        smsMsgService.send();
        weiXinMsgService.sendMsg();
    }
}
