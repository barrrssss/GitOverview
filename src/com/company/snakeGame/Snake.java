package com.company.snakeGame;

import java.util.LinkedList;
import java.util.Queue;

public class Snake extends BaseObject implements Blockable, Movable {
    private int x;
    private int y;
    private Point headPoint;

//    Count of segments, which must be adding to the snake;
    private Queue<Point> snakePoints;
    private int foodCount = 0;

//    Direction, which the snake will move in the next iteration;;
    private Direction direction = Direction.UP;

//    List of Points is sending to superclass constructor to drawing the snake there. Also it is saving here as
//    Queue to moving the snake.
    private Snake(LinkedList<Point> points, Canvas canvas) {
        super(points);
        this.snakePoints = points;
        this.y = canvas.getHeight() / 2;
        this.x = canvas.getWidth() / 2;

        addNewHeadPoint();
    }

    public Snake(Canvas canvas){
        this(new LinkedList<>(), canvas);
    }

    public void eat(Eatable food){
        foodCount += food.getScores();
    }

    private void addNewHeadPoint(){
        headPoint = new HeadSnakePoint(x, y);
        snakePoints.add(headPoint);
    }

    private void setDirection(Direction direction) {
        if (this.direction.opposite == direction) return;

        this.direction = direction;
    }

    @Override
    public void move() {
        if (foodCount <= 0) {
            snakePoints.remove();
        }
        else {
            foodCount--;
        }

        switch (direction){
            case UP: {
                y--;
                break;
            }
            case DOWN: {
                y++;
                break;
            }
            case RIGHT: {
                x++;
                break;
            }
            case LEFT: {
                x--;
                break;
            }
            default:{
                throw new IllegalArgumentException("Incorrect direction!");
            }
        }

        addNewHeadPoint();
    }

    public void moveUp(){
        setDirection(Direction.UP);
    }

    public void moveDown(){
        setDirection(Direction.DOWN);
    }

    public void moveRight(){
        setDirection(Direction.RIGHT);
    }

    public void moveLeft(){
        setDirection(Direction.LEFT);
    }

    public boolean isSnakeHeadIntersect(GameObject gameObject){
        for (Point point : gameObject.getPoints()) {
            if (headPoint.isIntersect(point)){
                return true;
            }
        }

        return false;
    }

//    An enumeration of snakes moving directions. Snake can't moving by opposite direction, so it was added;
    public enum Direction {UP, DOWN, LEFT, RIGHT;
        Direction opposite;

        static{
            UP.opposite = DOWN;
            DOWN.opposite = UP;
            LEFT.opposite = RIGHT;
            RIGHT.opposite = LEFT;
        }
    }

    class SnakePoint extends Point {
        SnakePoint(int x, int y) {
            super(x, y);
        }

        @Override
        char getC() {
            return 'T';
        }
    }

    class HeadSnakePoint extends SnakePoint {
        HeadSnakePoint(int x, int y) {
            super(x, y);
        }

        @Override
        char getC() {
            if (this == headPoint){
                return 'H';
            }

            return super.getC();
        }
    }
}
