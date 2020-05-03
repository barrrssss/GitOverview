package com.company.snakeGame;

import java.awt.event.KeyEvent;
import java.util.*;

public class SnakeGame {
    private static SnakeGame game;
    private int height;
    private int width;

    private List<Eatable> allEatableItems = new ArrayList<>();
    private List<Movable> allMovableItems = new ArrayList<>();
    private List<Blockable> allBlockableItems = new ArrayList<>();

    private SnakeGame(int height, int width){
        this.height = height;
        this.width = width;
    }

    public static SnakeGame getInstance(int height, int width){
        if (game == null){
            game = new SnakeGame(height, width);
        }

        return game;
    }

    public static SnakeGame getGame() {
        return game;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private Set<GameObject> getAllItems(){
        Set<GameObject> set = new HashSet<>();
        set.addAll(allBlockableItems);
        set.addAll(allEatableItems);
        set.addAll(allMovableItems);

        return set;
    }

    private void move(){
        for (Movable movable : allMovableItems){
            movable.move();
        }
    }

    private void draw(Canvas canvas){
        canvas.clear();

        for (GameObject gameObject: getAllItems()){
            gameObject.draw(canvas);
        }
    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Border createMainBorder(Canvas canvas){
        return Border.createMainBorder(canvas);
    }

    private void createFood(Canvas canvas) {
        if (allEatableItems.size() >= 3) return;

        int random10 = (int) (Math.random() * 10);
        if (random10 == 0) {
            allEatableItems.add(createFood1(canvas));
        }
    }

    private Food createFood1(Canvas canvas){
        Food food = Food.getInstance(canvas);

        if (isObjectIntersectAnyAnotherObject(food)){
            food = createFood1(canvas);
        }

        return food;
    }

    private boolean isObjectIntersectAnyAnotherObject(GameObject gameObject){
        for (GameObject object : getAllItems()){
            if (gameObject.isIntersectObject(object)){
                return true;
            }
        }

        return false;
    }


    private void checkForEating(Snake snake) {
        for (Eatable eatable : allEatableItems){
            if (snake.isSnakeHeadIntersect(eatable)){
                snake.eat(eatable);
            }
        }
    }

    private void checkForBlocking(Snake snake) {
        for (Blockable blockable : allBlockableItems){
            if (snake.isSnakeHeadIntersect(blockable)){
                snake.die();
            }
        }
    }

    private void removeDead() {
        for (GameObject gameObject : new ArrayList<>(allBlockableItems)){
            if (!gameObject.isAlive()){
                allBlockableItems.remove(gameObject);
            }
        }

        for (GameObject gameObject : new ArrayList<>(allEatableItems)){
            if (!gameObject.isAlive()){
                allEatableItems.remove(gameObject);
            }
        }

        for (GameObject gameObject : new ArrayList<>(allMovableItems)){
            if (!gameObject.isAlive()){
                allMovableItems.remove(gameObject);
            }
        }
    }


    private void run(){
        Canvas canvas = new Canvas(height, width);
        Snake snake = new Snake(canvas);
        Border mainBorder = createMainBorder(canvas);
        allBlockableItems.add(mainBorder);
        allBlockableItems.add(snake);
        allMovableItems.add(snake);

        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (snake.isAlive()){
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                System.out.print(event.getKeyCode());
                if (event.getKeyCode() == KeyEvent.VK_UP)
                    snake.moveUp();
                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    snake.moveDown();
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    snake.moveRight();
                else if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    snake.moveLeft();
            }

            createFood(canvas);

            move();

            checkForEating(snake);

            checkForBlocking(snake);

            removeDead();

            draw(canvas);

            canvas.print();

            sleep(500);
        }

        System.out.println("Game Over!");
    }


    public static void main(String[] args) {
        SnakeGame snakeGame = SnakeGame.getInstance(20, 20);
        snakeGame.run();

    }
}
