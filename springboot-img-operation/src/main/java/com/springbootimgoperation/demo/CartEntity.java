package com.springbootimgoperation.demo;

import java.util.List;

/**
 * @author Jerry
 * @date 2018/11/8
 */
public class CartEntity {

    private String username;

    private List<Pixel> list;

    public class Pixel{

        private int x;
        private int y;
        private int r;
        private int g;
        private int b;

        public Pixel(int x, int y, int r, int g, int b) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public int getG() {
            return g;
        }

        public void setG(int g) {
            this.g = g;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Pixel> getList() {
        return list;
    }

    public void setList(List<Pixel> list) {
        this.list = list;
    }
}
