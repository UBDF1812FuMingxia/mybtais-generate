package com.exam;



import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @ClassName : ExamStreamMethod
 * @Description : 测试stream的方法的使用
 * @Author : fmx
 * @Date: 2021-05-19 15:12
 */
public class ExamStreamMethod {

    static List<Integer> numbers = Arrays.asList(1,5,3,2,4);

    static List<String> strs =
            Arrays.asList("b2","b1","a2","c","b","a");

    static List<List<String>> strsList = Arrays.asList(strs);

    static Person p1 =
            new Person(GenderEnum.MALE,20,"JACK");

    static Person p2 =
            new Person(GenderEnum.FEMALE,20,"Lily");

    static Person p3 =
            new Person(GenderEnum.MALE,10,"John");

    static Person p4 =
            new Person(GenderEnum.FEMALE,10,"Lucy");

    static Person p5 =
            new Person(GenderEnum.MALE,20,"Eason");

    static List<Person> persons =
            Arrays.asList(p1,p2,p3,p4,p5);

    //常用数据结构转流的方式
    //Collection接口 提供了stream()方法
    static void method1() {
        //1.List转流
        List<String> list = new ArrayList<>();
        Stream<String> streamA = list.stream();

        //------------------------------------------

        //2.Set转流
        Set<String> set = new HashSet<>();
        Stream<String> streamB = set.stream();

        //-------------------------------------------

        //3.Map转流
        Map<String,Integer> map = new HashMap<>();
        Stream<Map.Entry<String, Integer>> streamC = map.entrySet().stream();
        Stream<Integer> streamD = map.values().stream();

        //---------------------------------------------

        //4.数组转流i
        String[] arr = {"A","B","C"};
        Stream<String> stream1 = Arrays.stream(arr);
        System.out.println(stream1);

        //---------------------------------------------

        //5.Iterable转流
        Iterable<String> iterable = new ArrayList<>();
        Stream<String> stream2 = StreamSupport.stream(iterable.spliterator(), false);
        System.out.println(stream2);

    }
    //流拼接
    static void method2() {

        List<String> listA = Arrays.asList("s1", "s2", "s3");
        List<String> listB = Arrays.asList("t1", "t2", "t3");

        //Stream.concat()实现多个流拼接
        Stream<String> streamA = Stream
                .concat(listA.stream(), listB.stream());

        //stream.of()实现拼接
        //多个元素
        Stream<String> streamB = Stream.of("A", "B", "C");
        //多个LIst
        Stream<String> StreamC = Stream
                .of(listA, listB).flatMap(Collection::stream);
        //多个Stream
        Stream<String> StreamD = Stream
                .of(listA.stream(), listB.stream())
                .flatMap(Function.identity());

        // flatMap实现子元素拼接
        List<Item> items = new ArrayList<>();
        Stream<String> StreamE = items.stream()
                .flatMap(i ->
                        i.getSubItems().stream());
    }

    //高级收集器
    static void method3() {
        //1.字符串拼接
        List<String> strs = Arrays.asList("s1","s2","s3");

        //无分隔符
        String str1 = strs.stream().collect(Collectors.joining());

        //单个分隔符
        strs.stream().collect(Collectors.joining(","));

        //请后缀及分隔符
        String str3 = strs.stream().collect(Collectors.joining(",", "[", "]"));

        //----------------------------------------------------------

        //2.返回数组

        // Object数据（无参默认）
        Object[] arr1 = strs.stream().toArray();
        //特定类型数据
        String[] arr2 = strs.stream().toArray(String[]::new);
        //数组指定长度
        String ids = "1, 2, 3, 4";
        String[] arr3 = Arrays.asList(ids.split(","))
                .stream()
                .map(String::trim)
                .toArray(size ->
                        new String[size]);

        //----------------------------------------------------

        //3.返回list
        //默认list
        List<String> list1 = strs.stream()
                .filter(str ->
                        str.length() > 5)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        //返回特定类型如ArrayList
        ArrayList<String> list2 = strs.stream()
                .filter(str ->
                        str.length() > 5)
                .map(String::toUpperCase)
                .collect(Collectors
                        .toCollection(ArrayList::new));

        //-------------------------------------------------------

        //4.返回Map

        //list转Map
        List<Item> items = Arrays
                .asList(new Item("0001", "name")
                        , new Item("0002", "name2"));
        //成员作为value
        Map<String, String> map1 = items.stream()
                .collect(Collectors
                        .toMap(Item::getItemId,
                                Item::getName));

        //元素成为value
        Map<String, Item> map2 = items.stream()
                .collect(Collectors
                        .toMap(Item::getItemId,
                                Function.identity()));
        //value重复取第一个
        Map<String, Item> map3 = items.stream()
                .collect(Collectors
                        .toMap(Item::getItemId,
                                Function.identity(),
                                (n1, n2) -> n1));
        //value重复自定义异常，指定返回类型
        IdentityHashMap<String, Item> map4 =
                items.stream()
                        .collect(Collectors
                                .toMap(Item::getItemId,
                                        Function.identity(),
                                        (a, b) -> {
            throw new
                    IllegalStateException("cannot add " +
                    "duplicate key: " + a);
        },IdentityHashMap::new));
        //Map转Map
        Map<String,Integer> map = new HashMap<>();
        Map<String, Integer> map_ = map.entrySet()
                .stream().filter(e ->
                        e.getValue() > 10)
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue));
    }

    //排序 sorted
    static void method4() {


        //数字排序
        List<Integer> numbers = Arrays.asList(1, 5, 3, 2, 4);

        //1.升序（默认）
        numbers.stream().sorted();//1、2、3、4、5

        //2.降序
        numbers.stream().sorted(Comparator.reverseOrder());//5,4,3,2,1

        //--------------------------------------------------------

        //字符排序
        List<String> strs = Arrays.asList("b2", "b1", "a2", "c", "b", "a");

        //1.默认
        String strSorted1 = strs.stream().sorted().collect(Collectors.joining());

        //2.自定义：先比较长度
        strs.stream().sorted((s1,s2) -> s1.length() -s2.length());
        List<String> strSorted2 =
                strs.stream()
                        .sorted(Comparator
                                .comparing(String::length)
                                .thenComparing(Comparator
                                        .naturalOrder()))
                        .collect(Collectors.toList());

        //---------------------------------------------------------

        //自定义类型排序
        //1.可实现Comparable<T>接口
        List<Person> pVal = persons.stream().sorted().collect(Collectors.toList());

        //List可直接排序，传入compareTo方法引用
        List<Person> personsNew = new ArrayList<>(persons);
        personsNew.sort(Person::compareTo);

        //2.使用比较起链，排序方式更灵活
        persons.sort(Comparator.comparing(Person::getAge).thenComparing(Person::getGenderEnum).thenComparing(Person::getName));
    }

    //分组与分区
    static void method5() {
        //分组
        Map<GenderEnum,List<Person>> grouped1 =
                persons.stream().
                        collect(Collectors
                                .groupingBy(Person
                                        ::getGenderEnum));
        //子分组，多个分组
        LinkedHashMap<GenderEnum, LinkedHashMap<Integer, List<Person>>> grouped2
                = persons.stream()
                .collect(Collectors
                        .groupingBy(Person::getGenderEnum,
                                LinkedHashMap::new,
                                Collectors
                                        .groupingBy(Person::getAge,
                                                LinkedHashMap::new,
                                                Collectors.toList())));

        //--------------------------------------------------------

        //分区
        Map<Boolean, List<Person>> partitioned1
                = persons.stream()
                .collect(Collectors
                        .partitioningBy(person ->
                                person.getAge() <= 6));
        //分区并计数
        Map<Boolean, Long> partitioned2
                = persons.stream()
                .collect(Collectors.
                        partitioningBy(person ->
                                person.getAge() <= 6,
                                Collectors.counting()));

    }

    //归约 reduce
    static <T> void method6() {
        //数字
        Integer reduced1 =
                numbers.stream().reduce((x, y) -> x + y).get();
        //数字，指定初始值
        Integer reduced2 =
                numbers.stream().reduce(0, (x, y) -> x + y);

        //字符串拼接
        String reduced3 =
                strs.stream().reduce((s1, s2) -> s1 + s2).get();
        //字符串品几个，指定前缀
        String reduced4 =
                strs.stream().reduce("prefix", (s1, s2) -> s1 + s2);

        //-----------------------------------------------

        //数字，求最小值
        Integer min = numbers.stream().reduce(Math::min).get();
        //数字，求最大值
        Integer max = numbers.stream().reduce(Math::max).get();
        //数字，求和
        Integer sum = numbers.stream().reduce(Integer::sum).get();

        //------------------------------------------------------

        //实现流的拼接
        Stream<String> reduced5 =
                strsList.stream().map(list ->
                        list.stream())
                        .reduce(Stream::concat).get();

        Iterable<T>[] inputs = null;
        Iterator<T> iterator
                = Stream.of(inputs)
                .map(it ->
                        StreamSupport
                                .stream(it.spliterator(),
                                        false))
                .reduce(Stream::concat)
                .orElseGet(Stream::empty).iterator();
    }

    static void method7() {
        //findFirst返回第一个符合条件
        String s1 =
                strs.stream()
                        .filter(str -> str.length() > 10)
                        .findFirst().get();
        //findAny返回任意一个符合条件，串行流和findFirsh相同，并行流返回最先有值的线程
        String s2 =
                strs.stream()
                        .filter(str -> str.length() > 10)
                        .findAny().get();
        // anyMatch 任意符合
        boolean flag1 =
                strs.stream()
                        .anyMatch(str -> str.length() > 10);
        //allMatch全部符合
        boolean flag2 =
                strs.stream()
                        .allMatch(str -> str.length() > 10);
        //noneMatch 全部符合
        boolean flag3 =
                strs.stream()
                        .noneMatch(str -> str.length() > 10);

        //----------------------------------------------------

        //distinct去重
        Stream<String> distinct = strs.stream().distinct();
        //count计数
        long count =
                strs.stream()
                        .filter(str ->
                                str.length() > 10).count();
        //skip跳过
        Stream<String> skip =
                strs.stream()
                        .filter(str ->
                                str.length() > 10).skip(3);
        //limit限定数量
        Stream<String> limit =
                strs.stream()
                        .filter(str ->
                                str.length() > 10).limit(3);

        //---------------------------------------------------

        //遍历
        strs.stream().forEach(System.out::println);
    }

    //IntStream
    static void method8() {
        //使用IntStream生成50个连续字符
        String intStream1 =
                IntStream.range(0, 50)
                        .mapToObj(s -> "*")
                        .collect(Collectors.joining());
        String intStream2 =
                IntStream.range(0, 50)
                        .mapToObj(s -> "*")
                        .collect(Collectors.joining(""));
        String intStream3 =
                IntStream.rangeClosed(1, 50)
                        .mapToObj(s -> "*")
                        .reduce((s1, s2) -> s1 + s2).get();
        StringJoiner intStream4 =
                IntStream.rangeClosed(1, 50)
                        .mapToObj(s -> "*")
                        .reduce(new StringJoiner("",
                                "[", "]"),
                                StringJoiner::add,
                                StringJoiner::merge);

        //生成随机数
        Random random = new Random();
        IntStream generate1 = IntStream.generate(random::nextInt);

        //生成特定元素
        IntStream iterate1 = IntStream.iterate(1, i -> i * 2);

    }

    static class Person implements Comparable<Person>{
        private GenderEnum genderEnum;
        private int age;
        private String name;

        public Person(GenderEnum genderEnum, int age, String name) {
            this.genderEnum = genderEnum;
            this.age = age;
            this.name = name;
        }

        public GenderEnum getGenderEnum() {
            return genderEnum;
        }

        public void setGenderEnum(GenderEnum genderEnum) {
            this.genderEnum = genderEnum;
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

        @Override
        public int compareTo(Person o) {
            if(this.genderEnum != o.genderEnum){
                return this.genderEnum.compareTo(o.genderEnum);
            }
            if(this.age != o.age){
                return this.age - o.age;
            }
            return this.name.compareTo(o.name);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "genderEnum=" + genderEnum +
                    ", age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    enum GenderEnum{
        MALE,FEMALE
    }

    static class Item{
        private String itemId;
        private String name;
        private String value;
        private List<String> subItems;

        public Item(String itemId, String name) {
            this.itemId = itemId;
            this.name = name;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<String> getSubItems() {
            return subItems;
        }

        public void setSubItems(List<String> subItems) {
            this.subItems = subItems;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "itemId='" + itemId + '\'' +
                    ", name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    ", subItems=" + subItems +
                    '}';
        }

        public static void main(String[] args) {
            method5();
        }
    }

    public static void main(String[] args) {
        method1();
    }
}
