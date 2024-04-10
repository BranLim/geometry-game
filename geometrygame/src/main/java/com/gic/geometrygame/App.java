package com.gic.geometrygame;


public class App 
{
    public static void main( String[] args )
    {
        var newGame = new Game();
        var player = new Player();
        player.play(newGame);

    }
}
