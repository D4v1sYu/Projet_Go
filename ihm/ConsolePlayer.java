package ihm;

import jeu_de_go.Go;
import jeu_de_go.Go.Coord;

public class ConsolePlayer implements IPlayer{

    @Override
    public Coord play(Go jeu, String color) {
        throw new IllegalStateException("Console player can't");
    }
    
}
