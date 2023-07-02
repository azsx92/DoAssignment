package lambda.ramdaAssignment;
@FunctionalInterface
interface MyFunction3 {
    void myMethod();
}
public class Outer {
    int val =10; // Outer.this.val
    class  Inner {
        int val=10; // Outer.this.val

        void method(int i) { // void method(final int i)
            int val=30;    // final int val =30;
            //i = 10;     // 에러.  상수의 값을 변경할 수 없음.

            MyFunction3 f = () -> {
                System.out.println("                i :" + i);
                System.out.println("                val :" + val);
                System.out.println("                this.val :" +  ++this.val);
                System.out.println("                Outher.this.val :" +  ++Outer.this.val);
            };
            f.myMethod();
        }
    } //Inner end
} //Outer end

class LamdaEx3 {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(100);
    }
}