import java.util.*;
interface Canplay{
    public void attack(Actor a);
    public void defense();
}
class Actor implements Canplay{
    public String name;
    int blood;
    int state=0;//0为初始状态，1为进攻状态，2为防守状态
    int Avalue=0;//进攻指数
    int Dvalue=0;//防守指数
    public void attack(Actor a){
        this.state=1;
         a.blood=a.blood-this.Avalue/a.Dvalue;
     }
     public void defense(){
        this.state=2;
        this.Dvalue*=2;
     }
}
class Master extends Actor{
    Master(){
        name="Master";
        blood=100;
        Dvalue=1;
    }
    public void attack(Actor a){
        state=1;
        Avalue=300;
        if(a.name.equals("Master")) a.blood=a.blood-this.Avalue/a.Dvalue;
        else a.blood=a.blood-2*this.Avalue/a.Dvalue;
    }
    public void defense(){
        this.state=2;
        Avalue=0;
        Dvalue=2;
    }
}
class Warrior extends Actor{
    Warrior(){
        name="Warrior";
        blood=300;
        Dvalue=2;
    }
    public void attack(Actor a){
        state=1;
        Avalue=100;
        if(a.name.equals("Warrior")) a.blood=a.blood-2*this.Avalue/a.Dvalue;
        else a.blood=a.blood-this.Avalue/a.Dvalue;
    }
    public void defense(){
        this.state=2;
        Avalue=0;
        Dvalue=4;
    }
}
class Game{
    Game(String user){
        if(user.equals("Master")) use=new Master();
        else if(user.equals("Warrior")) use=new Warrior();
        int rom=(int)(Math.random()*2);
        if(rom==0) com=new Master();//rom=0为大师 1为武士
        else com=new Warrior();
    }
    Actor use;
    Actor com;
    void run(String operate){
        int romd=(int)(Math.random()*2);//romd=0为进攻 1为防守
        if(operate.equals("Attack")){
            System.out.println(use.name+"PlayerAttack!");
            if(romd==1){
                System.out.println(com.name+"RobotDefense!");
                com.defense();
                use.attack(com);
            }
            else {
                System.out.println(com.name+"RobotAttack!");
                use.attack(com);
                com.attack(use);
            }
        }
        else if(operate.equals("Defense")){
            System.out.println(use.name+"MasterDefense!");
            use.defense();
            if(romd==0) {
                System.out.println(com.name+"RobotAttack!");
                com.attack(use);
            }
            else {
                System.out.println(com.name+"RobotDefense!");
                com.defense();
            }
        }
        if(use.blood<0&&com.blood<0) System.out.println("Drew! "+use.name+"PlayerBlood:0 "+com.name+"RobotBlood:0");//判断游戏结果
        else if(use.blood>com.blood) System.out.println("You win! "+use.name+"PlayerBlood:"+use.blood);
        else if(use.blood<com.blood) System.out.println("You lose! "+com.name+"RobotBlood:"+com.blood);
        else if(use.blood==com.blood) System.out.println("Drew! "+use.name+"PlayerBlood: "+use.blood+" "+com.name+"RobotBlood:"+com.blood);
    }
}
public class fightgame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String Job;
        String Opera;
        while (true) {
                System.out.println("请选择角色：Warrior或者Master");
            while (true) {
                Job = scan.next();
                if (Job.equals("Warrior") || Job.equals("Master")) break;
                else System.out.println("请输入正确的角色内容");
            }
            Game game = new Game(Job);
            System.out.println("请选择操作：Defense或者Attack");
            while (true) {
                Opera = scan.next();
                if (Opera.equals("Attack") || Opera.equals("Defense")) break;
                else System.out.println("请输入正确的操作内容");
            }
            game.run(Opera);

        }
    }
}

