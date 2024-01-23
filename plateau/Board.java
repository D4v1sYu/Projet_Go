package plateau;

public class Board<T> {
    private int sizeW;
    private int sizeH;
    private T[][] plateau;


    public Board(int n, int m){
        sizeW = n;
        sizeH = m;
        plateau = (T[][]) new Object[n][m];
    }

    public Board(int n){
        this(n,n);
    }

    public boolean inBoard(int i, int j){
        return (i >= 0 && i < sizeW && j >= 0 && j < sizeH);
    }

    public T getCase(int i, int j){
        if (!inBoard(i,j))
            return null;
        return plateau[i][j];
    }

    public boolean setCase(int i, int j, T value){
        if (!inBoard(i,j))
            return false;
        plateau[i][j] = value;
        return true;
    }

    public void fill(T value){
        for(int i = 0; i < sizeW; ++i){
            for(int j = 0; j < sizeH; ++j){
                plateau[i][j] = value;
            }
        }
    }

    public int getSizeW(){
        return sizeW;
    }

    public int getSizeH(){
        return sizeH;
    }
}
