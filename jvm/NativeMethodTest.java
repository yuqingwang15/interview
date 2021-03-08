package jvm;

public class NativeMethodTest {

    public native void  Natival(int x);

    public native static long  Natival(long x);

    native  void NativeEx() throws Exception;

    public static void main(String[] args) {

//        Class<? extends NativeMethodTest> nativetest  =
//                (Class<? extends NativeMethodTest>) NativeMethodTest.getClass();

    }
}
