package com.exam.classLoadExam;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @ClassName : DefinedClassLoaderExam
 * @Description : 测试类加载器
 * @Author : fmx
 * @Date: 2021-08-03 15:01
 */
public class DefinedClassLoaderExam extends ClassLoader {

    public DefinedClassLoaderExam() {

    }

    public DefinedClassLoaderExam(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = getClassFile(name);
        try {
            byte[] bytes = getClassBytes(file);
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private File getClassFile(String name) {
        File file = new File("D:/Person.class");
        return file;
    }

    private byte[] getClassBytes(File file) throws IOException {
        //这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1) {
                break;
            }
            by.flip();
            wbc.write(by);
            by.clear();
        }

        fis.close();
        return baos.toByteArray();
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        DefinedClassLoaderExam classLoaderExam = new DefinedClassLoaderExam();
        Class<?> cl = Class.
                forName("com.exam.classLoadExam.Person",
                        true, classLoaderExam);
        Object obj = cl.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());

        /*StringBuilder stringBuilder = new StringBuilder("abdcdc");
        String string = stringBuilder.toString();
        byte[] bytes = string.getBytes();
        for (byte b :
                bytes) {
            System.out.println(b);
        }
        char[] chars = string.toCharArray();
        for (char c :
                chars) {
            System.out.println(c);
        }
        System.out.println(stringBuilder.reverse());*/
    }
}

class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        /*return "Person{" +
                "name='" + name + '\'' +
                '}';*/
        return "I am a person, my name is " + name;
    }
}
