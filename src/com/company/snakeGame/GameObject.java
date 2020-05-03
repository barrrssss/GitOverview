package com.company.snakeGame;

import java.util.List;
/*
Main interface for drawing objects interaction
 */
public interface GameObject {
    List<BaseObject.Point> getPoints();

    /*
    Draw this object on specified canvas
     */
    void draw (Canvas canvas);


    /*
    If return false, the game will "destroy" this object
     */
    boolean isAlive();

    /*
    Return false, if this object intersect with specified object
     */
    boolean isIntersectObject(GameObject baseObject);
}
