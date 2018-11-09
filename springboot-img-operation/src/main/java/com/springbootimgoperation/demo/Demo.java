package com.springbootimgoperation.demo;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    /**
     * 读取一张图片的RGB值
     */
    public static void getImagePixel(String imgPath) throws IOException {
        int[] rgb = new int[3];
        File file = new File(imgPath);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        System.out.println("width=" + width + ",height=" + height + ".");
        System.out.println("minx=" + minx + ",miniy=" + miny + ".");
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
//                int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
//                rgb[0] = (pixel & 0xff0000) >> 16;
//                rgb[1] = (pixel & 0xff00) >> 8;
//                rgb[2] = (pixel & 0xff);
                if ((i >= 300 && i <= 600) && (j >= 300 && j <= 600))
                    bi.setRGB(i, j, new Color(195, 232, 72).getRGB());
            }
        }
        // 保存图片
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        String path = "C:\\Ysg\\new_img.jpg";
        FileImageOutputStream outputStream = new FileImageOutputStream(new File(path));
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        outputStream.write(bytes, 0, bytes.length);
        outputStream.close();
        System.err.println("ok");

    }

    public static void main(String[] args) throws IOException {
        List<CartEntity.Pixel> list = new ArrayList<>();
        CartEntity cartEntity = new CartEntity();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                new Color(2, 4, 23);
                CartEntity.Pixel pixel = cartEntity.new Pixel(i, j, (int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
                list.add(pixel);
            }
        }
        // 生成图片
        InputStream inputStream = createImg(1000, 1000, list, "png");
        File file = new File("C:\\Ysg\\create_img.png");
        if (file.exists()) {
            file.delete();
        }
        saveImg(file.getPath(), inputStream);
        System.err.println("ok");
    }


    /**
     * 生成图片，返回图片流
     *
     * @param width
     * @param height
     * @param list
     * @param imgType
     * @return
     * @throws IOException
     */
    public static InputStream createImg(int width, int height, List<CartEntity.Pixel> list, String imgType) throws IOException {
        // 创建图片实例对象
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        // 开始绘图
        Graphics g = bi.getGraphics();
        for (CartEntity.Pixel p : list) {
            g.setColor(new Color(p.getR(), p.getG(), p.getB()));
            g.drawLine(p.getX(), p.getY(), p.getX(), p.getY());
        }
        g.dispose();
        // 获得图片二进制流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, imgType, os);
        return new ByteArrayInputStream(os.toByteArray());
    }

    /**
     * 获取byte数据
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] getByte(InputStream inputStream) throws IOException {
        byte[] by = new byte[inputStream.available()];
        inputStream.read(by);
        return by;
    }

    /**
     * 保存图片
     *
     * @throws IOException
     */
    public static void saveImg(String path, InputStream inputStream) throws IOException {
        byte[] by = new byte[inputStream.available()];
        inputStream.read(by);
        FileImageOutputStream outputStream = new FileImageOutputStream(new File(path));
        outputStream.write(by, 0, by.length);
        outputStream.close();
    }


}
