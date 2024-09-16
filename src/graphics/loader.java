package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class loader {

    public static BufferedImage ImageLoader(String path) throws IOException{

        try{
             return ImageIO.read(loader.class.getResource(path));
        } catch (IOException e){
            e.printStackTrace();
        }
      return null;
    }

}