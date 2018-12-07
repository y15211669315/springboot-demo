package com.springbootimgoperation;

import com.springbootimgoperation.entity.Img;
import com.springbootimgoperation.rep.ImgRep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootImgOperationApplicationTests {

    @Autowired
    private ImgRep imgRep;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void finall() {
        List<Img> imgs = imgRep.selectAll();
        for (Img img : imgs) {
            System.err.println(img.toString());
        }
    }

    @Test
    public void save_two() throws IOException {
        byte[] bytes = getByte();
        redisTemplate.opsForValue().set("img", bytes);
        System.err.println("ok");
    }

    @Test
    public void find() throws IOException {
        Object img = redisTemplate.opsForValue().get("img");
        byte[] by = (byte[]) img;
        String path = "C:\\Ysg\\wokao_copy.jpg";
        FileImageOutputStream outputStream = new FileImageOutputStream(new File(path));
        outputStream.write(by, 0, by.length);
        outputStream.close();
        System.err.println("ok");
    }

    @Test
    public void save_one() throws IOException {
        byte[] bytes = getByte();
        System.err.println("数据长度：" + bytes.length);
        // 存储图片
        imgRep.saveAndFlush(new Img(bytes));
        System.err.println("ok");
    }

    private byte[] getByte() throws IOException {
        System.err.println("开始存储图片");
        // 获得图片流
        String path = "C:\\Ysg\\wokao.jpg";
        FileInputStream inputStream = new FileInputStream(new File(path));
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        return bytes;
    }
}