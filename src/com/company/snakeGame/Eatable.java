package com.company.snakeGame;

	/*
	Snake can eat Eatable objects (food), and the snake take score from them by "getScores" method.
	*/
public interface Eatable extends GameObject {

	// Return score that eatable object (food) include
    int getScores();
}
