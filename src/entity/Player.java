package entity;

import com.javalearning.GamePanel;
import com.javalearning.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKeys = 0;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 16;
        solidArea.height = 16;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 4;
        direction = "down";
    }


    public void getPlayerImage(){

        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        }catch(IOException e){

            e.printStackTrace();
        }
    }
    public void update() {
        if(keyH.rightPressed || keyH.downPressed || keyH.leftPressed || keyH.upPressed){
            if(keyH.upPressed) {
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }
            else {
                direction = "right";
            }

            //CHECK COLLISION
            collisionIsOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);

            //PICK UP OBJECTS
            pickUPObject(objIndex);

            // IF COLLISION IS FALSE< PLAYER CAN MOVE

            if (collisionIsOn == false) {

                switch (direction) {
                    case "up" -> worldY = worldY - speed;
                    case "down" -> worldY = worldY + speed;
                    case "left" -> worldX = worldX - speed;
                    case "right" -> worldX = worldX + speed;
                }
            }


            spriteCounter++;
            if (spriteCounter >10) {
                if (spriteNum == 1) {
                    spriteNum =2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void pickUPObject(int index){
        if (index != 999){
            String objectName = gp.obj[index].name;
            switch(objectName){
                case "Key":
                    hasKeys ++ ;
                    gp.obj[index] = null;
                    gp.playSoundEffect(1); //play coin sound effect
                    gp.ui.showMessage("You got a Key!");
                    break;

                case "Door":
                    if(hasKeys > 0){
                        gp.playSoundEffect(3); // play unlock effect
                        gp.obj[index] = null;
                        hasKeys --;
                        gp.ui.showMessage("Door Opened");
                    }
                    else {
                        gp.ui.showMessage("You need a Key");
                    }
                    break;

                case "Boots":
                    gp.playSoundEffect(2);
                    gp.obj[index] = null;
                    gp.ui.showMessage("Speed up!");
                    this.speed = 6;
                    break;

                case "Chest":
                    gp.ui.isGameFinished = true;
                    gp.stopMusic();
                    gp.playSoundEffect(4);
                    break;


            }
        }

    }

    public void draw(Graphics2D g2) {
/*
        g2.setColor(Color.white);
        g2.fillRect(x,y,gp.tileSize,gp.tileSize);
*/

        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }


        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
