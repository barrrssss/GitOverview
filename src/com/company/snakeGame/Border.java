package com.company.snakeGame;

import java.util.ArrayList;
import java.util.List;

public class Border extends BaseObject implements Blockable {
    private Border(List<Point> points) {
        super(points);
    }

    public static Border createMainBorder(Canvas canvas){
        List<Point> list = new ArrayList<>();

        Border border = new Border(list);

        for (int y = 1; y < canvas.getHeight() - 1; y++){
            list.add(border.new BorderPoint(0, y, '|'));
        }
        for (int y = 1; y < canvas.getHeight() - 1; y++){
            list.add(border.new BorderPoint(canvas.getWidth() - 1, y, '|'));
        }
        for (int x = 1; x < canvas.getWidth() - 1; x++){
            list.add(border.new BorderPoint(x, 0, '-'));
        }
        for (int x = 1; x < canvas.getWidth() - 1; x++){
            list.add(border.new BorderPoint(x, canvas.getHeight() - 1, '-'));
        }

        list.add(border.new BorderPoint(0, 0, '+'));
        list.add(border.new BorderPoint(0, canvas.getHeight() - 1, '+'));
        list.add(border.new BorderPoint(canvas.getWidth() - 1, 0, '+'));
        list.add(border.new BorderPoint(canvas.getWidth() - 1, canvas.getHeight() - 1, '+'));

        return border;
    }

    class BorderPoint extends Point {
        private char c;

        BorderPoint(int x, int y, char c) {
            super(x, y);

            this.c = c;
        }

        @Override
        char getC() {
            return c;
        }
    }
}
