package package1;

public class SuperTicTacToeGame {
    private Cell[][] board;
    private GameStatus status;
    private int size;
    private int win;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public SuperTicTacToeGame(int size)
    {
     this.size= size;
        status = GameStatus.IN_PROGRESS;
        board = new Cell[size][size];

        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                board[row][col] = Cell.EMPTY;
    }
    public void select (int row, int col){

    }
    public void reset() {
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                board[row][col] = Cell.EMPTY;

    }
    public GameStatus getGameStatus(){
return null;
    }
    public Cell[][] getBoard(){
return board;
    }
}
