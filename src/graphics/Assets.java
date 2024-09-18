package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;


public class Assets {
    public static BufferedImage player;

    //efectos
    public static BufferedImage speed;
    
    
    public static void init() throws IOException{
            player = loader.ImageLoader("/ships/player.png");
            speed = loader.ImageLoader("/effects/fire08.png");
        }
}