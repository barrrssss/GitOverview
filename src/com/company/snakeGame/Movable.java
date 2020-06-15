package com.company.snakeGame;

	/*
	Movable objects can move. In every iteration Game invoke "move" method and object change
	its coordinates. Now only snake is movable object.
	*/
public interface Movable extends GameObject {

	/*
	In every iteration Game invoke "move" method and object change its coordinates.
	*/
    void move();
}
