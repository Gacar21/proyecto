package main;


import gameObjects.Constants;
import graphics.Assets;
import input.KeyBoard;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import states.GameState;

// extends JFrame (para abrir una ventana
// public static con width y height tamaño de ventanas
// constructor windows
//, set (asignar titulo, tamaño
//y defaulcloseoperation(JFrame. exit) para cerrar la ventana con la X
public class Windows extends JFrame implements Runnable{
    public static final int WIDTH = 800, HEIGHT =600;
    //nos permitira dibujar
    private Canvas canvas;

    //crear un hilo, que es como un mini programa dentro de otro programa
    //no sobrecargar la ventana windows
    private Thread thread;
    private boolean running=true;
    private BufferStrategy bs;
    private Graphics g;
    //Velocidad de fotogramas fijos, si esto no se pone ira mas rapido o mas lento segun la pc
    private final int FPS=60;
    // tiempo medido en nanosegundos (mas precision)
    private double TARGETTIME = 1000000000/FPS;
    //delta cambio con respecto al tiempo /almacenar el tiempo
    private double delta = 0;
    //nos permite saber a cuantos fps correa actualmente
    private int AVERAGEFPS = FPS;

    private GameState gameState;
    private KeyBoard keyBoard;
    
    public Windows()
    {
        setTitle("Juego espacial");
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //permite redimenzionar la pantalla setresizable
        setResizable(false);
        //permite que la ventana se despliegue en el centro de la pantalla
        setLocationRelativeTo(null);



        canvas = new Canvas();
        keyBoard = new KeyBoard();
        canvas.setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        canvas.setMaximumSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        canvas.setMinimumSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        //permite recibir entradas por parte del teclado
        canvas.setFocusable(true);
         add(canvas);
         
         canvas.addKeyListener(keyBoard);
         setVisible(true);
          //hace visible la ventana
       
    }
    public static void main(String[] args) {
        //para visualizarlo
        new Windows().start();


    }
    //movimiento de nuestro dibujo
    //int x = 0;
    
    private void update(){
        keyBoard.update();
        gameState.update();
    }

    private void draw(){
        bs = canvas.getBufferStrategy();

        if(bs == null){
            canvas.createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        //---------inicio del dibujo------------------

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        
        gameState.draw(g);
        
        
        g.drawString(""+AVERAGEFPS, 10, 10 );

        //----------final del dibujo---------------
        g.dispose();
        bs.show();
    }

    private void init(){
        try {
            Assets.init();
            gameState = new GameState();
        } catch (IOException ex) {
            Logger.getLogger(Windows.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

     //registro del tiempo
     long now = 0;
    //registro en nanosegundos
     long lastTime = System.nanoTime();
     int frames = 0;
     long time = 0;

     init();
    while(running)
    {
        now = System.nanoTime();
        delta += (now - lastTime)/TARGETTIME;
       time+= (now - lastTime);
       lastTime = now;

       if (delta >= 1){
           update();
           draw();
           delta --;
           frames ++;
         // ver frames por segundos  System.out.println(frames);
       }
           if(time >= 1000000000){
               AVERAGEFPS = frames;
               frames = 0;
               time = 0;

           }

    }

     stop();
    }

    private void start(){

    thread = new Thread(this);
    thread.start();
    }

    private void stop(){
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Windows.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}