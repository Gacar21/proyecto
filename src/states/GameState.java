
package states;

import gameObjects.Laser;
import gameObjects.MovingObject;
import gameObjects.Player;
import graphics.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import math.Vector2D;


public class GameState {
    
    private Player player;
    private ArrayList <MovingObject> movingObjects = new ArrayList<MovingObject>();
    
    
    public GameState(){
        
        player = new Player(new Vector2D(100,500), new Vector2D(), 4, Assets.player, this);
        movingObjects.add(player);
    }
    
    public void update(){
        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).update();
        }
        
    }
    public void draw(Graphics g){
        
        Graphics2D g2d = (Graphics2D )g;
        
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);
            
         }
     }

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }


    }
    
    

