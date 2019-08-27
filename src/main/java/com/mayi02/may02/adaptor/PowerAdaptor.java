package com.mayi02.may02.adaptor;

//电源适配器
public class PowerAdaptor implements JP110VInterface{
    private CN220VInterface cn220VInterface;

    public PowerAdaptor(CN220VInterface cn220VInterface) {
        this.cn220VInterface = cn220VInterface;
    }

    @Override
    public void content() {
        cn220VInterface.content();
    }
}
