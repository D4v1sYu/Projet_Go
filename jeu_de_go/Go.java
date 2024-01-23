package jeu_de_go;

import java.util.HashSet;

public class Go {

    private GoBoard board;
    private int[] scores; // avec taille = 2, [0] = WHITE & [1] = BLACK
    private Coord[] ko;

    private Color current;
    private int skip;

    public record Coord(int i, int j) {}

    public Go(){
        board = setBoard(19);
    }
    
    public GoBoard setBoard(int n){
        this.board = new GoBoard(n);
        this.scores = new int[2];
        this.ko = new Coord[2];
        this.skip = 0;
        this.current = Color.BLACK;
        return this.board;
    }

    public boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public boolean currentTurn(String color){
        return current.toString().equals(color);
    }

    public Color getCurrent() {
        return current;
    }
    public int boardSize(){
        return board.getSize();
    }

    // ajout d'affichage des scores à la fin
    public String showboard(){

        StringBuilder sb = new StringBuilder();

        sb.append("  ");
        char letter = 'A';
        for(int i = 0; i < board.getSize(); ++i){
            sb.append(" ");
            if (letter == 'I') ++letter;
            sb.append(letter++);
        }
        sb.append("\n");

        for(int i = board.getSize()-1; i >= 0 ; --i){
            if (i<9)
               sb.append(" ");
            sb.append(i+1);
            for(int j = 0; j < board.getSize(); ++j){
                sb.append(" ");
                sb.append(board.show(i,j));
            }
            sb.append(" ");
            sb.append(i+1);
            if (i<9)
                sb.append(" ");
            if (i == board.getSize()/2)
                sb.append(afficheScore("WHITE"));
            if (i == (board.getSize()/2)-1)
                sb.append(afficheScore("BLACK"));
            sb.append("\n");
        }

        sb.append("  ");
        letter = 'A';
        for(int i = 0; i < board.getSize(); ++i){
            sb.append(" ");
            if (letter == 'I') ++letter;
            sb.append(letter++);
        }
        sb.append("\n");
        // sb.append("\n");
        return sb.toString();
    }

    public void clearBoard(){
        board.fill(Color.EMPTY);
        scores[0] = 0;
        scores[1] = 0;
    }

    // add n à joueur de couleur
    public void addScore(String player, int n){
        if (player.equals("WHITE")){
            scores[0] += n;
        }
        else if(player.equals("BLACK")){
            scores[1] += n;
        }
        else throw new IllegalArgumentException("Player not found");
    }

    // affiche score de pl1 et pl2 vers la moitié de la taille du board
    public String afficheScore(String color) { //
        StringBuilder sb = new StringBuilder();
        sb.append("     ");
        //sb.append(" : ");
        if (color == "WHITE") {
            sb.append(color + " (O) has captured ");
            sb.append(scores[0]);
            sb.append(" stones");
            return sb.toString();
        } else { // si color = "BLACK"
            sb.append(color + " (X) has captured ");
            sb.append(scores[1]);
            sb.append(" stones");
            return sb.toString();
        }
    }

    public boolean inBoard(int i, char j){
        if (j>'I') --j;
        return (board.inBoard(i-1, j-'A'));
    }

    public int getLiberties(int i, int j, HashSet<Coord> visited){
        if (!board.inBoard(i,j) || visited.contains(new Coord(i,j)))
            return 0;
        if (board.getCase(i, j) == Color.EMPTY)
            return 1;
        visited.add(new Coord(i,j));
        int cpt = 0;
        for (Coord c : new Coord[]{new Coord(i+1,j), new Coord(i-1,j), new Coord(i,j+1), new Coord(i,j-1)}){
            if (board.getCase(c.i,c.j) == Color.EMPTY && !visited.contains(new Coord(c.i,c.j))){
                ++cpt;
                visited.add(new Coord(c.i,c.j));
            }
            if (board.getCase(c.i, c.j) == board.getCase(i, j)){
                cpt+=getLiberties(c.i, c.j, visited);
            }
        }
        return cpt;
    }

    public void capture(Color color, HashSet<Coord> visited){
        for (Coord c : visited)
            if (board.getCase(c.i, c.j) == Color.EMPTY) visited.remove(c);

        addScore(color == Color.WHITE ? "WHITE" : "BLACK", visited.size());
        for (Coord c : visited){
            board.setCase(c.i, c.j, Color.EMPTY);
        }
    }

    public boolean checkCapture(int i, int j, Color color){

        board.setCase(i, j, color);
        boolean res = false;
        HashSet<Coord> visited;

        for (Coord c : new Coord[]{new Coord(i+1,j), new Coord(i-1,j), new Coord(i,j+1), new Coord(i,j-1)}){
            visited = new HashSet<>();
            if (board.inBoard(c.i, c.j) && !(board.getCase(c.i, c.j)==color) && (getLiberties(c.i, c.j, visited)) == 0){
                capture(color, visited);
                res = true;
            }
        }

        board.setCase(i, j, Color.EMPTY);
        return res;
    }

    public int simulatedLiberties(int i, int j, Color color){
        if (!board.playable(i, j)) return 0;
        board.setCase(i, j, color);
        int cpt = getLiberties(i, j, new HashSet<>());
        board.setCase(i, j, Color.EMPTY);
        return cpt;
    }

    public boolean checkSuicide(int i, int j, Color color){
        return !(simulatedLiberties(i, j,color) == 0);
    }

    public boolean checkKo(int i, int j, Color color) {
        if(ko[0] == null || ko[1] == null) return true;
        if(color == Color.WHITE){
            if(ko[0].i == i && ko[0].j == j) return false;
        }
        else if(color == Color.BLACK){
            if(ko[1].i == i && ko[1].j == j) return false;
        }
        else{
            throw new IllegalArgumentException("Color not found");
        }
        return true;
    }

    public boolean play(int i, int j, String couleur){

        if (!board.playable(i, j) || !checkKo(i, j, couleur.equals("WHITE")?Color.WHITE:Color.BLACK))
            return false;
        checkCapture(i, j, couleur.equals("WHITE")?Color.WHITE:Color.BLACK);
        if (!checkSuicide(i, j, couleur.equals("WHITE")?Color.WHITE:Color.BLACK))
            return false;
        
        if (couleur.equals("WHITE")){
            current = Color.BLACK;
            board.setCase(i, j, Color.WHITE);
            ko[0] = new Coord(i,j);
        }
        else{
            current = Color.WHITE;
            board.setCase(i, j, Color.BLACK);
            ko[1] = new Coord(i,j);
        }
        skip = 0;
        return true;
    }

    public boolean play(int i, char j, String couleur){
        if (j>'I') --j;
        return play(i, j-'A', couleur);
    }

    public boolean skip(){
        if (skip == 1) return false;
        skip = 1;
        if (current == Color.WHITE){
            current = Color.BLACK;
            ko[0] = null;
        }
        else{
            current = Color.WHITE;
            ko[1] = null;
        }
        return true;
    }

    public boolean checkEndGame(int i, int j, String color){
        if (skip == 0) return false;
        if (!board.playable(i, j)) return false;
        if (checkCapture(i, j, color.equals("WHITE")?Color.WHITE:Color.BLACK)) return false;
        return simulatedLiberties(i, j, color.equals("WHITE")?Color.WHITE:Color.BLACK) < 2 ? true : false;
    }
}
