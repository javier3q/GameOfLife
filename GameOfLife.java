public class GameOfLife {

    public static void main(String []args){

        Runnable r = new ViewBoard();

        ((ViewBoard) r).setVisible(true);

        Thread hilo = new Thread(r);

        hilo.start();
    }

}
