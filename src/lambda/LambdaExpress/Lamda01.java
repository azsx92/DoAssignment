package lambda.LambdaExpress;

interface A { //입력X, 출력x
    void method1();
}

interface B { //입력O, 출력x
    void method2(int a);
}

interface C { //입력X, 출력O
    int method3();
}

interface D { //입력O, 출력O
    double method4(int a, double b);
}

public class Lamda01 {

    //#1. 입력X, 출력x의 함수 //@1-1. 익명이너클래스 표현
    A a1 = new A() {
        @Override
        public void method1() {
            System.out.println("입력X, 출력x의 함수");
        }
    };

    public static void main(String[] args) {


//@1-2. 람다식 표현
        A a2 = () -> {
            System.out.println("입력X, 출력x의 함수");
        };
        A a3 = () -> System.out.println("입력X, 출력x의 함수");
//#2.
// 입력O, 출력x의 함수
// @2-1. 익명이너클래스 표현
        B b1 = new B() {
            public void method2(int a) {
                System.out.println(a);
            }

            ;
        };
//@2-2. 람다식 표현
        B b2 = (int a) -> {
            System.out.println(a);
        };
        B b3 = (a) -> {
            System.out.println(a);
        };
        B b4 = (a) -> System.out.println(a);
        B b5 = a -> System.out.println(a);
    }
};