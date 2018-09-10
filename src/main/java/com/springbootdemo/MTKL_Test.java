package com.springbootdemo;

import java.sql.Timestamp;
import java.util.Date;

public class MTKL_Test {

    public static class Te {
        private Long id = 5748420L;
        private Timestamp timestamp = Timestamp.valueOf("2017-06-01 09:34:30");
        private Integer loginCount = 9;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

        public Integer getLoginCount() {
            return loginCount;
        }

        public void setLoginCount(Integer loginCount) {
            this.loginCount = loginCount;
        }
    }

    public static void getVip() {
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int vip[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        String gt = "";
        //
        for (int k = 0; k < 3; k++) {
            //百分之几率50等于0
            for (int i = 0; i < data.length / 2; i++) {
                vip[i * 2] = 0;
                vip[(i + 1) * 2 - 1] = 1;
            }
            for (int i = 0; i < vip.length; i++) {
                System.err.println(gt + vip[i]);
            }
            gt += "\t";
        }
    }

    public static double montePI(int n) {
        double PI = 0.0;
        double x, y;
        int res = 0;
        for (int i = 0; i < n; i++) {
            // 通过函数获取随机的x,y
            // get random x,y
            x = Math.random();
            y = Math.random();
            // 如果距离小于1，这说明在圆圈内，结果加1
            // if the distance is small than 1,it turns the point is in the
            // cycle,res+1
            if ((x * x + y * y) < 1) {
                res++;
            }
        }
        // 通过比例算出PI
        // calculate PI
        PI = 4.0 * res / n;
        return PI;
    }
}
