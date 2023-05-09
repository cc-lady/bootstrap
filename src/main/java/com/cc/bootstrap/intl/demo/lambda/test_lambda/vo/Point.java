package com.cc.bootstrap.intl.demo.lambda.test_lambda.vo;

/**
 * @Description: Point
 * @author: ChenChen
 * @date: 2023/2/7 9:08
 */
public class Point {
    private final int x;
    private final int y;
    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public Point moveRightBy(int x){
        return new Point(this.x + x, this.y);
    }
}
