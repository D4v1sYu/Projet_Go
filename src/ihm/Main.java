package ihm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import jeu_de_go.Go;
import jeu_de_go.Go.Coord;
import player.RandomPlayer;
import jeu_de_go.Color;

public class Main {
    public static void main(String[] args) {
        Go jeu = new Go();
        ArrayList<String> cmd;
        String ln;
        Map<Color, IPlayer> players;
        players = new HashMap<>();
        players.put(Color.WHITE, new ConsolePlayer());
        players.put(Color.BLACK, new ConsolePlayer());

        Scanner sc = new Scanner(System.in);

        do{
            if(!(players.get(jeu.getCurrent()) instanceof ConsolePlayer)){
                Color color = jeu.getCurrent();
                Coord c = players.get(color).play(jeu, color.toString());
                if (c == null){
                    System.out.println(color.toString() + " : skip");

                    if (!jeu.skip()){ //fin de jeu
                        System.out.println(jeu.showboard());
                        System.out.println("Fin de jeu");
                        break;
                    }
                }
                else{
                    char letter = (char)('A' + c.j());
                    if (letter >= 'I') ++letter;
                    int pos = c.i()+1;
                    System.out.println(color.toString() + " : " + letter + pos);
                    System.out.println(jeu.showboard());
                }
                continue;
            }

            cmd = new ArrayList<String>(Arrays.asList(sc.nextLine().split(" ")));
            ln = "";
            if (jeu.isNumeric(cmd.get(0))){
                ln = cmd.get(0);
                cmd.remove(0);
            }

            if (cmd.get(0).equals("showboard")){
                System.out.println("=" + ln);
                System.out.println(jeu.showboard());
            }
            else if(cmd.get(0).equals("clearboard")){
                System.out.println("=" + ln);
                jeu.clearBoard();
            }
		    else if (cmd.get(0).equals("boardsize")){
                if (cmd.size() > 1 && jeu.isNumeric(cmd.get(1))){
                    int n = Integer.parseInt(cmd.get(1));
                    if ( n >= 2 && n <= 25){
                        jeu.setBoard(n);
                        System.out.println("=" + ln);
                    }
                    else
                        System.out.println("?" + ln + " unacceptable size");
                }
                else{
                    System.out.println("?" + ln + " boardsize not an integer");
                }
            }
		    else if (cmd.get(0).equals("play")){
                if (cmd.size() < 3 || (!cmd.get(1).toUpperCase().equals("WHITE") && !cmd.get(1).toUpperCase().equals("BLACK"))){
                    System.out.println("?" + ln + " invalid color or coordinates");
                }
                else{
                    char j = Character.toUpperCase(cmd.get(2).charAt(0));
                    int i = -1;
                    if (jeu.isNumeric(cmd.get(2).substring(1)))
                        i = Integer.parseInt(cmd.get(2).substring(1));
                    if ((j == 'I') || !jeu.inBoard(i, j) || !jeu.currentTurn(cmd.get(1).toUpperCase()))
                        System.out.println("?" + ln + " invalid color or coordinates");
                    else{
                        System.out.println(jeu.play(i-1, j, cmd.get(1).toUpperCase() + ln) ? "=" + ln : "?" + ln + " illegal move");
                    }
                }
            }
            else if (cmd.get(0).equals("player")){
                if (cmd.size() < 3 || (!cmd.get(1).toUpperCase().equals("WHITE") && !cmd.get(1).toUpperCase().equals("BLACK"))){
                    System.out.println("?" + ln + " invalid color");
                }
                else{
                    if (cmd.get(2).toUpperCase().equals("CONSOLE")){
                        players.put(cmd.get(1).toUpperCase().equals("WHITE") ? Color.WHITE : Color.BLACK, new ConsolePlayer());
                        System.out.println("=" + ln);
                    }
                    else if (cmd.get(2).toUpperCase().equals("RANDOM")){
                        players.put(cmd.get(1).toUpperCase().equals("WHITE") ? Color.WHITE : Color.BLACK, new RandomPlayer());
                        System.out.println("=" + ln);
                    }
                    else{
                        System.out.println("?" + ln + " invalid player type");
                    }
                }
            }
            else if (cmd.get(0).equals("skip")){
                System.out.println("=" + ln);
                if (!jeu.skip()){ //fin de jeu
                    System.out.println(jeu.showboard());
                    System.out.println("Fin de jeu");
                    break;
                }
            }
            else if (cmd.get(0).equals("quit")){ 
                System.out.print("=" + ln);
                break;
            }
            else {
                System.out.println("?" + ln + " unknown command");
            }
        }while(true);
        sc.close();
    }
}