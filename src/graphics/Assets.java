package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;


public class Assets {
    public static BufferedImage player;

    //efectos
    public static BufferedImage speed;
    public static BufferedImage blueLaser, greenLaser, redLaser;
    
    public static void init() throws IOException{
            player = loader.ImageLoader("/ships/player.png");
            speed = loader.ImageLoader("/effects/fire08.png");
            blueLaser = loader.ImageLoader("/lasers/laserBlue01.png");
            greenLaser = loader.ImageLoader("/lasers/laserGreen11.png");
            redLaser = loader.ImageLoader("/lasers/laserRed01.png");
        }
}