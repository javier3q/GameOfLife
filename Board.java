public class Board {
    private int verticalSize;
    private int horizontalSize;
    private String[][] theBoard;

    public Board(int horizontalSize, int verticalSize) {
        this.verticalSize = horizontalSize;
        this.horizontalSize = verticalSize;
        this.theBoard = new String[this.verticalSize][this.horizontalSize];
        inicializeTheBoard();
    }

    private void inicializeTheBoard(){
        for(int i = 0; i< verticalSize; i++){
            for(int j = 0; j< horizontalSize; j++){
                theBoard[i][j]=" ";
            }
        }
    }

    void inicialStateOne(){
        for(int y=29;y<31;y++){
            for(int x=30;x<40;x++)

                theBoard[x][y]="*";
        }
    }

    void stateTenLines(){
        for(int i=20;i<40;i++)
            theBoard[i][30]="*";

        for(int j=36;j<63;j++)
            theBoard[j][35]="*";

        for(int i=20;i<40;i++)
            theBoard[40][i]="*";

        for(int i=2;i<40;i++){
            for(int j=40;j>2;j--){
                theBoard[i][j]="*";
            }
        }

        for(int i=50;i<60;i++)
            theBoard[40][i]="*";

    }

    boolean isCellAlive(int posX, int posY){
        if(theBoard[posX][posY].equals("*")) return true;
        return false;
    }


    private int cellsAliveAround(int posX, int posY){
        int count=8;
        //System.out.println(posX+" "+posY+" ");
        if(theBoard[posX-1][posY-1].equals(" ")) count--;
        if(theBoard[posX-1][posY].equals(" ")) count--;
        if(theBoard[posX-1][posY+1].equals(" ")) count--;
        if(theBoard[posX][posY-1].equals(" ")) count--;
        if(theBoard[posX][posY+1].equals(" ")) count--;
        if(theBoard[posX+1][posY-1].equals(" ")) count--;
        if(theBoard[posX+1][posY].equals(" ")) count--;
        if(theBoard[posX+1][posY+1].equals(" ")) count--;
        return count;
    }

    void updateBoard(){

        String auxBoard[][] = new String[verticalSize][horizontalSize];

        for(int i = 0; i< verticalSize; i++){

            for(int j = 0; j< horizontalSize; j++){

                auxBoard[i][j]=" ";
            }
        }


        for (int i = 1; i< verticalSize -1; i++){

            for(int j = 1; j< horizontalSize -1; j++){

                int cellsAround=cellsAliveAround(i,j);

                if(isCellAlive(i,j)  ) {

                    auxBoard[i][j] = (cellsAround == 2 || cellsAround == 3) ? "*" : " ";

                }

                if(!isCellAlive(i,j)) {

                    auxBoard[i][j] = cellsAround == 3 ? "*" : " ";
;
                }
            }
        }

        theBoard = auxBoard;

    }

    String[][] getTheBoard() {
        return theBoard;
    }

    @Override
    public String toString() {
        String board ="";

        for(int i = 1; i< verticalSize -1; i++){
            for (int j = 1; j< horizontalSize -1; j++){

                board = board.concat(theBoard[i][j]);
               // System.out.print(board);
            }
            board=board.concat("\n");
        }

        return board;
    }
}
