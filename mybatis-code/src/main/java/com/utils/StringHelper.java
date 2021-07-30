package com.utils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName : StringHelper
 * @Description : String工具类（也可用StringUtils工具类）
 * @Author : fmx
 * @Date: 2021-07-29 17:37
 */
public class StringHelper {

    public static String concatByComma(String... strings) {
        if (Objects.isNull(strings) || strings.length == 0) {
            return "";
        }
        return Arrays.stream(strings).collect(Collectors.joining(","));
    }

    public static Boolean isEmpty(String string) {
        if (Objects.isNull(string)) {
            return true;
        }
        if (Objects.equals(0, string.length())) {
            return true;
        }
        return false;
    }

    public static Boolean notEmpty(String string) {
        return !isEmpty(string);
    }

    public static Boolean notEmpty(String[] string) {
        for (String tmp : string) {
            if (isEmpty(tmp)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public static Boolean isBlank(String string) {
        if (Objects.isNull(string)) {
            return true;
        }
        if (Objects.equals(0, string.trim().length())) {
            return true;
        }
        return false;
    }

    public static Boolean notBlank(String string) {
        return !isBlank(string);
    }

    public static Stream<String> split(String string, String split) {
        return Arrays.stream(string.split(split));
    }

    public static Stream<String> splitByComma(String string) {
        return split(string, ",");
    }

    public static Stream<String> splitBySemicolon(String string) {
        return split(string,";");
    }

    public static Stream<String> splitByUnderline(String string) {
        return split(string,"_");
    }

    public static Boolean isInteger(String string) {
        if (Objects.isNull(string)) {
            return Boolean.FALSE;
        }
        return string.matches("^\\d+$");
    }

    public static String concatByComma(List<String> strings) {
        if (Objects.isNull(strings) || strings.isEmpty()) {
            return "";
        }
        return strings.stream().collect(Collectors.joining(","));
    }

    /**
     * 转换数据库字符串
     * @param source
     * @return
     */
    public static String changeDatabaseStrToObj(String source) {
        if (isBlank(source) || isEmpty(source)) {
            return null;
        }
        if (source.indexOf("_") <= 0) {
            return source;
        }
        String[] words = source.split("_");
        StringBuffer sb = new StringBuffer(words[0]);
        for (int i = 1; i < words.length; i++) {
            StringBuilder sbulid = new StringBuilder(words[i]);
            sbulid.setCharAt(0, Character.toUpperCase(sbulid.charAt(0)));
            sb.append(sbulid.toString());
        }
        return sb.toString();
    }

    public static String upperCap(String name) {
        if (Objects.isNull(name) || StringHelper.isEmpty(name)) {
            return name;
        }
        return name.substring(0,1).toUpperCase()
                + name.substring(1, name.length());
    }

    public static String lowerCap(String name) {
        if (Objects.isNull(name) || StringHelper.isEmpty(name)) {
            return name;
        }
        return name.substring(0,1).toLowerCase()
                + name.substring(1, name.length());
    }

    public static String dbName2JavaName(String dbName) {
        String javaName = Stream.of(dbName).filter(StringHelper::notBlank)
                .flatMap(StringHelper::splitByUnderline)
                .map(StringHelper::upperCap)
                .collect(StringBuilder::new,
                        StringBuilder::append, StringBuilder::append)
                .toString();
        return lowerCap(javaName);
    }

    public static BigDecimal mapValues2BigDecimal(Map<Long, BigDecimal> map) {
        myBigDecimal sumBigDecimal = Stream.of(map.values())
                .collect(myBigDecimal::new,
                        myBigDecimal::add,
                        myBigDecimal::addAll);
        return sumBigDecimal.getTotal();
    }

    public static String javaName2DbName(String javaName) {
        return Arrays.stream(lowerCap(javaName).split("")).map(str -> {
            if (str.matches("^[A-Z]$")) {
                return "_" + str.toLowerCase();
            }
            return str;
        }).collect(StringBuilder::new, (sb, s) -> sb.append(s),
                StringBuilder::append).toString();
    }
}

class myBigDecimal {
    private BigDecimal total = new BigDecimal("0.00");

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void add(Collection<BigDecimal> item) {
        item.forEach(bigDecimal -> {
            this.total = this.total.add(bigDecimal);
        });
    }

    public void addAll(myBigDecimal myBigDecimal) {
        this.total = this.total.add(myBigDecimal.getTotal());
    }
}
