package com.exam.proxyPattern.staticAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : ImageProxy
 * @Description : 代理类，实现缓存与记录的效果
 * @Author : fmx
 * @Date: 2021-08-05 14:42
 */
public class ImageProxy implements Graphic {

    private Image image;

    private Map<String, Image> cache = new HashMap<>();//缓存

    public ImageProxy() {

        init();
    }

    public void init() { //只需要初始化一次
        if (image == null) {
            image = new Image();
            cache.put("image", image); //放入缓存
        } else {
            image = cache.get("image");
        }
    }

    @Override
    public void load() {
        System.out.println("----第一步开始----");

        image.load();

        System.out.println("----第一步结束-----");
    }

    @Override
    public void Draw() {
        System.out.println("----第二步开始----");

        image.Draw();

        System.out.println("----第二步结束----");
    }

    @Override
    public Extent getExtent() {
        System.out.println("----第三步开始----");

        Extent extent = image.getExtent();

        System.out.println("----第三步结束----");

        return extent;
    }

    @Override
    public void Store() {
        System.out.println("----第四步开始----");

        image.Store();

        System.out.println("----第四步结束----");
    }
}
