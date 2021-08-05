package com.exam.proxyPattern.staticAgent;

/**
 * @ClassName : DocumentEditor
 * @Description : 文档编辑器类
 * @Author : fmx
 * @Date: 2021-08-05 14:54
 */
public class DocumentEditor {

    public static void main(String[] args) {

        //Graphic proxy = new Image();//引用代码
        Graphic proxy = new ImageProxy();//引用代码

        proxy.load();

        proxy.Draw();

        proxy.getExtent();

        proxy.Store();
    }
}
