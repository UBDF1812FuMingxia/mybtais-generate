package com.exam;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName : Exam
 * @Description : 随机测试
 * @Author : fmx
 * @Date: 2021-09-09 10:46
 */
public class Exam {
    public static void main(String[] args) {
        //==、equals区别
        String a = "abc";
        String b = "abc";
        System.out.println(a == b);  //true
        String a1 = new String("abc");
        String b1 = new String("abc");
        System.out.println(a1 == b1);  //false
        System.out.println(a1.equals(b1));  //true
        System.out.println(Objects.equals(a1,b1));  //true
        System.out.println(a == a1); //false

        List nameList = new ArrayList();
        nameList.add("11");
        nameList.add("11");
        nameList.add("22");
        nameList.add("33");

        //测试传参问题
        List<Student> students = new ArrayList<>();
        students.add(new Student(19, String.join(",",nameList),32));
        students.add(new Student(19,"zs",32));
        students.add(new Student(19,"zs",32));
        students.add(new Student(19,"zs",32));
        test1(students);

        long count = nameList.stream().map(name -> name).distinct().count();
        System.out.println(count);
        /*String join = String.join(",", nameList);
        System.out.println(join.split(",").length);
        join = join + ",";
        Arrays.stream(join.split(",")).forEach(System.out::println);

        List<Long> collect = Arrays.stream(join.split(",")).map(str -> Long.parseLong(str)).collect(Collectors.toList());
        collect.forEach(System.out::println);*/

        StringBuilder stringBuilder = new StringBuilder("");
        System.out.println(stringBuilder.toString());
        stringBuilder.append("测试");
        test3(stringBuilder);
        System.out.println(stringBuilder);

        //判断一个值是否是整数
        System.out.println(isNumeric("2323"));

        String s = ".a....b....";
        String[] split = s.split("\\.");
        System.out.println(split.length);

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(0,1);
        list.stream().forEach(System.out::print);
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");

        return pattern.matcher(str).matches();

    }

    private static void test3(StringBuilder stringBuilder) {
        //System.out.println(stringBuilder.append("23232323"));
        stringBuilder.append("43vfd");
        //return stringBuilder.toString();
    }

    private static void test1(List<Student> students) {
        for (Student s :
                students) {
            test2(s);
        }

        for (Student s :
                students) {
            System.out.println(s.toString());
        }
    }

    private static void test2(Student s) {
        s.setAge(20);
    }


}

class Student{
    private int age;
    private String name;
    private int score;

    public Student(int age, String name, int score) {
        this.age = age;
        this.name = name;
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
