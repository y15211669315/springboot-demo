package com.springbootgcz;

import java.util.Iterator;
import java.util.Vector;

public class TenxunOne implements Tenxun {

    private String name = "腾讯一号";

    private String mess = "明日10点整，登陆在线免费送永久神奇...";

    private static Vector<UserObServer> vector = new Vector<>();

    @Override
    public void add(UserObServer userObServer) {
        vector.add(userObServer);
    }

    @Override
    public void del(UserObServer userObServer) {
        vector.remove(userObServer);
    }

    @Override
    public void tongzhi() {
        Iterator<UserObServer> it = vector.iterator();
        while (it.hasNext()) {
            it.next().update(name, mess);
        }
    }
}
