package com.exam.proxyPattern.staticAgent;

/**
 * @ClassName : Image
 * @Description : 对操作进行具体的控制，Extent是对宽度和长度的封装
 * @Author : fmx
 * @Date: 2021-08-05 14:33
 */
public class Image implements Graphic {

    public Image () {
        try {
            Thread.sleep(2000); //模拟创建需要花费很久的时间
            System.out.println("正在创建对象");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void load() {
        System.out.println("进行加载...");
    }

    @Override
    public void Draw() {
        System.out.println("进行绘画...");
    }

    @Override
    public Extent getExtent() {
        Extent extent = new Extent("100", "200");

        System.out.println("获取图片的属性是：" + extent.toString());

        return extent;
    }

    @Override
    public void Store() {
        System.out.println("图片进行存储在硬盘里");
    }
}
