package design;

public abstract class Suanfa {
    abstract void addPrice(int orignPrice);
}

//A 高级会员打五折
class UserA extends Suanfa{
    @Override
    void addPrice(int orginPrice) {
        System.out.println(orginPrice*0.5);
    }
}

//B 中级会员打7折
class UserB extends Suanfa{
    @Override
    void addPrice(int orginPrice) {
        System.out.println(orginPrice*0.7);
    }
}

//C初级会员打9折
class UserC extends Suanfa{
    @Override
    void addPrice(int orginPrice) {
        System.out.println(orginPrice*0.9);
    }
}


class Context {
    private Suanfa suanfa;
    Context(Suanfa suanfa){
        this.suanfa = suanfa;
    }

    void getPrice(int orignPrice) {
        suanfa.addPrice(orignPrice);
    }
}


class main{
    public static void main(String[] args) {
        Suanfa userA = new UserA();
        Context context = new Context(userA);
        context.getPrice(100);

        Suanfa userB  = new UserB();
        new Context(userB).getPrice(1000);
    }
}