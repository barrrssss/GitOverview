package com.company.snakeGame;

public class Canvas {
    private int height;
    private int width;
    private char[][] matrix;

    public Canvas(int height, int width) {
        this.height = height;
        this.width = width;

        matrix = new char[height][width];
    }

//    public void setPoint(BaseObject.Point point){
//        matrix[point.getY()][point.getX()] = point.getC();
//    }

    public char[][] getMatrix() {
        return matrix;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void clear() {
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = '.';
            }
        }
    }

    public void print() {
        System.out.println();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(" ");
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }

            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

}
