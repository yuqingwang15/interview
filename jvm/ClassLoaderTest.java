package jvm;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

import java.io.IOException;

public class ClassLoaderTest
{

    public static void main(String[] args) throws IOException {
        System.out.println("ClassLoader.getSystemClassLoader() = " + ClassLoader.getSystemClassLoader());
        System.out.println("ClassLoader.getSystemResources() = " + ClassLoader.getSystemResources("./"));

    }
}
