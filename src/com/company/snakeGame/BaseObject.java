package com.company.snakeGame;

import java.util.Collections;
import java.util.List;

public abstract class BaseObject implements GameObject {
    private List<Point> points;
    private boolean isAlive;

    BaseObject(List<Point> points) {
        this.points = points;
        this.isAlive = true;
    }

    @Override
    public List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

    @Override
    public void draw(Canvas canvas) {
        for (Point point : points){
            point.draw(canvas);
        }
    };

    void die(){
        isAlive = false;
    };

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean isIntersectObject(GameObject gameObject){
        for (Point point : getPoints()){
            for (Point oPoint : gameObject.getPoints()){
                if (point.isIntersect(oPoint)){
                    return true;
                }
            }
        }

        return false;
    }



    abstract class Point{
        private int x;
        private int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;

        }

        /*
        Draw the point on specified canvas
         */
        private void draw(Canvas canvas){
            canvas.getMatrix()[y][x] = getC();
        }

        boolean isIntersect(Point point){
            if (x == point.x && y == point.y && point != this){
                return true;
            }

            return false;
        }

//        int getX() {
//            return x;
//        }
//
//        int getY() {
//            return y;
//        }

        abstract char getC();

    }
}
