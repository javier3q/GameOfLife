import javax.swing.*;
import java.awt.*;
import java.io.Closeable;

public class ViewBoard extends JFrame implements Runnable{
    private Board theBoard;
    private JPanel panel1;
    private JButton[][] theButton;
    private final int verticalSize =70;
    private final int horizontalSize =60;

    public ViewBoard() {

        theBoard = new Board(verticalSize, horizontalSize);

        theBoard.stateTenLines();

        initFrame();

        this.getContentPane().setVisible(true);
    }

    private void initFrame(){

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        this.getContentPane().setLayout(null);

        this.setSize((dimension.width/5)*3, (dimension.height/9)*8);

        this.setTitle("Game of life");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1=new JPanel();

        panel1.setBounds(new Rectangle(60, 20, (dimension.width/7)*4, dimension.height));

        panel1.setLayout(null);

        this.getContentPane().add(panel1);

        theButton =new JButton[verticalSize][horizontalSize];

        inicializeViewBoard();

    }

    private void inicializeViewBoard(){
        //insert the buttons at the jpanel
        for(int x = 1; x< verticalSize -1; x++){

            for(int y = 1; y< horizontalSize -1; y++){

                theButton[x][y]=new JButton();

                panel1.add(theButton[x][y]);

                theButton[x][y].setBounds(x*10,y*10,10,10);

                theButton[x][y].setMargin(new Insets(0, 0, 0, 0));

                theButton[x][y].setBackground(Color.black);
            }
        }
    }

    private void changeViewOfCell(int posX, int posY){

        if(theBoard.getTheBoard()[posX][posY].equals(" "))

            theButton[posX][posY].setBackground(Color.black);

        else

            theButton[posX][posY].setBackground(Color.white);
    }

    private void updateView(){

        for (int i = 1; i< verticalSize -1; i++){

            for (int j = 1; j< horizontalSize -1; j++){

                changeViewOfCell(i,j);
            }
        }
    }

    @Override
    public void run() {

        try {

            while (true){
                updateView();

                theBoard.updateBoard();

                Thread.sleep(500);
            }

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

    }
}
