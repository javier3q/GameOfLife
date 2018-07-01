import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ViewBoard extends JFrame implements Runnable{
    private Board theBoard;
    private JPanel panel1;
    private JButton[][] theButton;
    private final int verticalSize =70;
    private final int horizontalSize =60;
    private JButton startButton;
    private JButton stopButton;
    private int gameState;
    private JPanel panel2;
    public ViewBoard() {

        theBoard = new Board(verticalSize, horizontalSize);

        initFrame();

    }

    private void initFrame(){

        Dimension dimension = new Dimension();
        dimension.width = 960;
        dimension.height =700;

        this.getContentPane().setLayout(null);
        this.setSize(dimension.width, dimension.height);
        this.setTitle("Game of life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        panel1=new JPanel();
        panel1.setBounds(new Rectangle(60, 20, dimension.width, dimension.height));
        panel1.setLayout(null);
        this.getContentPane().add(panel1);
        theButton =new JButton[verticalSize][horizontalSize];


        startButton = new JButton();
        startButton.setBounds(730,100,120,30);
        panel1.add(startButton);
        gameState=0;
        startButton.setText("START");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameState==0){
                    startButton.setText("STOP");
                    gameState=1;
                }
                else{
                    startButton.setText("START");
                    gameState=0;
                }
            }
        });

        inicializeViewBoard();

        stopButton = new JButton();
        startButton.setBounds(730,200,120,30);
        stopButton.setText("STOP");
        panel1.add(stopButton);

    }


    public class ClicButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i=1;i<verticalSize;i++){
                for (int j=1;j<horizontalSize;j++) {
                    if(e.getSource()==theButton[i][j]){

                        if(theButton[i][j].getBackground()==Color.black){
                            theButton[i][j].setBackground(Color.white);

                        }
                        else{
                            theButton[i][j].setBackground(Color.white);

                        }

                        theBoard.changeStateOfCell(i,j);
                    }
                }
            }
        }

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

                theButton[x][y].addActionListener(new ClicButton());
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

            while (gameState==1){
                updateView();

                theBoard.updateBoard();

                Thread.sleep(100);
            }

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

    }
}
