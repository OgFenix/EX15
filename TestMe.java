
/**
 * Write a description of class TestMe here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestMe
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class TestMe
     */
    public TestMe()
    {
        // initialise instance variables
        x = 0;
    }

    public static void main(String args[]){
        IntNode test40 = new IntNode(15,null,null);
        IntNode test25 = new IntNode(25,test40,null);
        IntNode test50 = new IntNode(50,null,null);
        IntNode test5 = new IntNode(5,test50,test25);
        IntNode test60 = new IntNode(60,null,null);
        IntNode test45 = new IntNode(45,null,test60);
        IntNode test30 = new IntNode(30,test45,null);
        IntNode test20 = new IntNode(20,test30,test5);
        IntNode test10 = new IntNode(10,null,null);
        IntNode test402 = new IntNode(40,test20,test10);
        
        //System.out.print(what(test402));
        //System.out.print(g(test402));
        System.out.print(f(test402));
    }
    public static int what(IntNode t){
        if(t==null){
            return -1;
        }
        if((t.getLeftSon()==null ) && (t.getRightSon()==null)){
            return 0;
        }
        //return Math.max(g(t),(Math.max(what(t.getRightSon()),what(t.getRightSon()))));
        return myMax(g(t),what(t.getRightSon()),what(t.getRightSon()));
    }
    public static int myMax(int a,int b, int c){
        if(a>b){
            if(a>c){
                return a;
            }
            else return b;
        }
        else{
            if(b>c){
                return b;
            }
            else return c;
        }
    }
    public static int f(IntNode t){
        if(t==null){
            return 0;
        }
        //return 1+Math.max(f(t.getLeftSon()),(f(t.getRightSon())));
        return f(t.getLeftSon()) + 1 + f(t.getRightSon());
    }
    public static int g(IntNode t){
        return (f(t.getLeftSon()) + f(t.getRightSon()));
    }
}
