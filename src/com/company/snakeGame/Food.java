package com.company.snakeGame;

import java.util.ArrayList;
import java.util.List;

public abstract class Food extends BaseObject implements Eatable {
    private int score;

    private Food(List<Point> points, int score) {
        super(points);
        this.score = score;
    }

    private Food(int score){
        this(new ArrayList<>(), score);
    }

    public static Food getInstance(Canvas canvas){
        int random = (int) (Math.random() * 10);
        List<Point> points;
        Food food;

        int x = (int) (Math.random() * canvas.getWidth());
        int y = (int) (Math.random() * canvas.getHeight());

        if (random < 1) {
            points = new ArrayList<>(4);
            food = new BigFood(points);

            points.add(food.new FoodPoint(x, y));
            points.add(food.new FoodPoint(x, y - 1));
            points.add(food.new FoodPoint(x + 1, y));
            points.add(food.new FoodPoint(x + 1, y - 1));

        } else {
            points = new ArrayList<>(1);
            food = new SimpleFood(points);
            points.add(food.new FoodPoint(x, y));
        }

        return food;
    }

    @Override
    public int getScores() {
        die();

        return score;
    }

    private static class SimpleFood extends Food {
        private SimpleFood(List<Point> points) {
            super(points, 1);
        }
    }

    private static class BigFood extends Food{

        private BigFood(List<Point> points) {
            super(points, 4);
        }
    }

    class FoodPoint extends Point {
        public FoodPoint(int x, int y) {
            super(x, y);
        }

        @Override
        char getC() {
            return 'F';
        }
    }
}
