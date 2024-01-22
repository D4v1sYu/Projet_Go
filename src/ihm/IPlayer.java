package ihm;

import jeu_de_go.Go;
import jeu_de_go.Go.Coord;

public interface IPlayer {
    Coord play(Go jeu, String color);
}
