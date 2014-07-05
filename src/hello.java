/**
 * Created by wanghaizhou on 2014/7/4.
 */
public class Hello {
    public static void main(String[] args) {
        System.out.println("nihao");
        new Hello().domyself();
        new Hello().excute();
    }
    public void excute(){
        System.out.println("");
        excute1();
    }
    public void excute1(){
        System.out.println("");
        excute2();
    }
    public void excute2(){
        System.out.println("");
        System.out.println("s");
        System.out.println("s2");
    }
    public void domyself(){
        System.out.println("haohaoxians");
        excute2();;
    }
    public void domyself2(){
        System.out.println("haohaoxians");
        excute2();;
    }
    public void  wwww(){

    }
}
