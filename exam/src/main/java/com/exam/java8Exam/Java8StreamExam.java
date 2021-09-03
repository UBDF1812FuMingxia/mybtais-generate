package com.exam.java8Exam;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : Java8StreamExam
 * @Description : Java 8 新特性 流Stream的测试
 * @Author : fmx
 * @Date: 2021-08-19 14:31
 */
public class Java8StreamExam {
    private List<String> strings;

    public static void main(String[] args) {
        //1、简单使用测试
        List<String> strings = Arrays
                .asList("abc", "", "bc",
                        "efg", "abcd",
                        "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.stream().forEach(System.out::println);
//        strings.stream().filter(string -> !string.isEmpty()).forEach(System.out::println);


        //2、使用forEach（以及limit方法）输出10个随机数
        System.out.println("===================使用forEach输出10个随机数===================");
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        //3、使用map输出了元素对应的平方数
        System.out.println("===================使用map输出了元素对应的平方数===================");
        List<Integer> numbers = Arrays.asList(3,2,2,3,7,3,5);
        //获取对应的平方数
        numbers.stream().map(integer -> integer * integer).distinct().collect(Collectors.toList()).forEach(System.out::println);
        numbers.stream().map(num->Integer.toString(++num)).distinct().forEach(System.out::println);


        //4、使用filter方法过滤出空字符串：
        System.out.println("====================使用filter方法过滤出空字符串====================");
        long count = strings.stream().filter(s -> s.isEmpty()).count();
        System.out.println(count);

        //5、使用sorted方法对输出的10个随机数进行排序
        System.out.println("===================使用sorted方法对输出的10个随机数进行排序==================");
        random.ints().limit(10).sorted().forEach(System.out::println);

        //6、并行（parallel），使用parallelStream来输出空字符串的数量
        System.out.println("============================并行（parallel）=============================");
        long count1 = strings.parallelStream().filter(s -> s.isEmpty()).count();
        System.out.println(count1);

        //7、Collectors,返回列表或者字符串
        System.out.println("========================Collectors,返回列表或者字符串============================");
        List<String> collect = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选列表：" + collect);
        String collect1 = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(","));
        System.out.println("合并字符串：" + collect1);

        //8、统计
        System.out.println("========================统计=======================");
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数：" + stats.getMax());
        System.out.println("列表中最小的数：" + stats.getMin());
        System.out.println("所有数的和：" + stats.getSum());
        System.out.println("平均数：" + stats.getAverage());

    }

    /**
     * 完整测试流
     */
    @Test
    public void test() {
        System.out.println("使用 Java 7：");
        //计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        System.out.println("列表：" + strings);
        long count = getCountEmptyStringUsingJava7(strings);
        System.out.println("空字符数量为：" + count);
        count = getCountLength3UsingJava7(strings);
        System.out.println("字符串长度为3的数量为：" + count);
        //删除空字符串
        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        System.out.println("筛选后的列表：" + filtered);
        //删除空字符串，并使用逗号把他们合并起来
        String mergedString = getMergedStringUsingJava7(strings, ", ");
        System.out.println("合并字符串：" + mergedString);
        List<Integer> numbers = Arrays.asList(3,2,2,3,7,3,5);
        //获取列表元素平方数
        List<Integer> squaresList = getSquares(numbers);
        System.out.println("平方数列表：" + squaresList);
        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
        System.out.println("列表：" + integers);
        System.out.println("列表中最大的数：" + getMax(integers));
        System.out.println("列表中最小的数：" + getMin(integers));
        System.out.println("所有数之和：" + getSum(integers));
        System.out.println("平均数：" + getAverage(integers));
        System.out.println("随机数：");
        //输出10个随机数
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt());
        }

        System.out.println("使用 Java 8：");
        System.out.println("列表："+ strings);
        count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串数量为：" + count);
        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为3的数量为：" + count);
        filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表：" + filtered);
        mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串：" + mergedString);
        squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("Squares List：" + squaresList);
        System.out.println("列表：" + integers);
        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数：" + stats.getMax());
        System.out.println("列表中最小的数：" + stats.getMin());
        System.out.println("所有数之和：" + stats.getSum());
        System.out.println("平均数：" + stats.getAverage());
        System.out.println("随机数：");
        random.ints().limit(10).forEach(System.out::println);
        //并行处理
        count = strings.parallelStream().filter(s -> s.isEmpty()).count();
        System.out.println("空字符串的数量为：" + count);

    }

    private static int getCountEmptyStringUsingJava7(List<String> strings) {
        int count = 0;
        for (String string : strings) {
            if (string.isEmpty()) {
                count ++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings) {
        int count = 0;
        for (String string : strings) {
            if (string.length() == 3) {
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
        List<String> filteredList = new ArrayList<>();
        for (String string : strings) {
            if (!string.isEmpty()) {
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string :
                strings) {
            if (!string.isEmpty()) {
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length() - 1);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        List<Integer> squareList = new ArrayList<>();
        for (Integer number :
                numbers) {
            Integer square = new Integer(number.intValue() * number.intValue());
            if (!squareList.contains(squareList)) {
                squareList.add(square);
            }
        }
        return squareList;
    }

    private static int getMax(List<Integer> numbers) {
        int max = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            if (number.intValue() > max) {
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers) {
        int min = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            if (number.intValue() < min) {
                min = number.intValue();
            }
        }
        return min;
    }

    private static int getSum(List numbers) {
        int sum = (int) numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            sum += (int)numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers) {
        return getSum(numbers) / numbers.size();
    }

    @Test
    public void GroupByTest() {
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orange", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );

        /*Map<String, List<Item>> counting = items.stream().collect(Collectors.groupingBy(item -> item.getName()+ "_" + item.getQty()));
        Set<Map.Entry<String, List<Item>>> entries = counting.entrySet();
        for (Map.Entry<String, List<Item>> map : entries) {
            List<Item> value = map.getValue();
            System.out.print("key：" + map.getKey() + "-----value：");

            value.stream().forEach(item -> System.out.println(item.toString()));
            System.out.println();
        }*/
        items.stream().map(Item::getName).distinct().forEach(this::test1);

    }

    public void test1(String name) {
        System.out.println(name);
    }
}

class Item {
    private String name;
    private int qty;
    private BigDecimal price;

    public Item(String name, int qty, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
