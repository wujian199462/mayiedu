package com.mayi02.may02.strategy;

//策略模式dinginess抽象公共算法
abstract class Strategy {
    abstract void  algorithmInterface();
}

//初级会员，针对算法A算法
class StrategyA extends Strategy{

    @Override
    void algorithmInterface() {
        System.out.println("初级会员，针对算法A算法");
    }
}

//中级会员，针对算法B算法
class StrategyB extends Strategy{

    @Override
    void algorithmInterface() {
        System.out.println("中级会员，针对算法B算法");

    }
}

class Context{
    private Strategy strategy;
    Context(Strategy strategy){
        this.strategy = strategy;
    }
    void algorithmInterface(){
        strategy.algorithmInterface();
    }
}

//高级会员，针对算法C算法
class StrategyC extends Strategy{

    @Override
    void algorithmInterface() {
        System.out.println("高级会员，针对算法C算法");
    }
}

public class StrategyTest {
    public static void main(String[] args) {
        Context  context = null;

        Strategy strategyA = new StrategyA();
        context = new Context(strategyA);
        context.algorithmInterface();

        Strategy strategyB = new StrategyB();
        context = new Context(strategyB);
        context.algorithmInterface();

        Strategy strategyC = new StrategyC();
        context = new Context(strategyC);
        context.algorithmInterface();
    }

    public void getPrice(String leve){
        Long price = null;
        if(leve.equals("铂金会员")){
            price = (long)100*100;
        }else if(leve.equals("黄金会员")){
            price = (long) 100*150;
        }else if(leve.equals("普通会员")){
            price = (long)100*200;
        }
    }
}
