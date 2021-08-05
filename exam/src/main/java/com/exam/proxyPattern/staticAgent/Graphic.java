package com.exam.proxyPattern.staticAgent;

import sun.font.ExtendedTextLabel;

/**
 * @ClassName : Graphic
 * @Description : 通过文件编辑器对一个图像文件的操作步骤来测试静态代理
 *                  步骤分为：加载，绘制，获取长度和宽度，存储
 * @Author : fmx
 * @Date: 2021-08-05 14:20
 */
public interface Graphic {
    void load();//加载

    void Draw();//绘制

    Extent getExtent();//获取长度和宽度

    void Store();//存储
}
