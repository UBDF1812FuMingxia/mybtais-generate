package com.test;

import com.utils.JacksonHelper;
import com.utils.Log4jHelper;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @ClassName : TestResourceBundle
 * @Description : 国际化资源绑定测试
 * @Author : fmx
 * @Date: 2021-07-29 11:26
 */
public class TestResourceBundle {
    public static void main(String[] args) {
        /**
         * Properties类：这个类提供软件国际化的捷径。通过该类，可以使编写的程序可以：
         *                  1、轻松的本地化或翻译成不同的语言
         *                  2、一次处理多个语言环境
         *                  3、以后轻松地进行修改，支持更多的语言环境
         * 简单说，这个类的作用就是读取资源属性文件，然后根据.properties文件的名称信息，
         * 匹配当前系统的国别语言信息，然后获取响应的properties文件内容
         *
         * 需要注意的时：这个properties文件的名字是有规范的：一般的命名规范是：
         *              自定义名_语言代码_国别代码.properties
         */
        Log4jHelper.info("测试完成");
        Locale locale = new Locale("zh", "CN");
        ResourceBundle myres = ResourceBundle.getBundle("myres", locale);
        System.out.println(myres.getString("aaa"));

        ResourceBundle myres1 = ResourceBundle.getBundle("myres", Locale.getDefault());
        System.out.println(myres1.getString("aaa"));

        Locale locale1 = new Locale("en", "US");
        ResourceBundle myres2 = ResourceBundle.getBundle("myres", locale1);
        System.out.println(myres2.getString("aaa"));
        JacksonHelper.toJSON(myres2);

    }

}
