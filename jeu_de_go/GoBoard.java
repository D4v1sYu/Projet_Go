package jeu_de_go;

import plateau.Board;

public class GoBoard extends Board<Color> {

    public GoBoard(int n){
        super(n);
        fill(Color.EMPTY);
    }

    public String show(int i, int j){
        if (getCase(i,j) == Color.EMPTY)
            return ".";
        if (getCase(i,j) == Color.BLACK)
            return "X";
        else if (getCase(i,j) == Color.WHITE)
            return "O";
        throw new IllegalArgumentException("Out of board");
    }

    public boolean playable(int i, int j){
        if (!(getCase(i,j) == Color.EMPTY) && !(getCase(i,j) == null))
            return false;
        return true;
    }

    //méthode redondante avec getSizeW() de Board
    public int getSize(){
        return getSizeW();
    }

}
