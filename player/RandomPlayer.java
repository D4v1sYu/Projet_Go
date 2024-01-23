package player;

import java.util.Random;

import ihm.IPlayer;
import jeu_de_go.Go;
import jeu_de_go.Go.Coord;

public class RandomPlayer implements IPlayer{

    @Override
    public Coord play(Go jeu, String color) {
        Random r = new Random();
        int i = Math.abs(r.nextInt());
        int j = Math.abs(r.nextInt());
        for (int l = 0; l < jeu.boardSize(); ++l){
            for (int c = 0; c < jeu.boardSize(); ++c){
                if (jeu.checkEndGame((i+l)%jeu.boardSize(), (j+c)%jeu.boardSize(), color)) continue;
                if (jeu.play((i+l)%jeu.boardSize(), (j+c)%jeu.boardSize(), color))
                    return new Coord((i+l)%jeu.boardSize(), (j+c)%jeu.boardSize());
            }
        }
        return null;
    }
    
}
