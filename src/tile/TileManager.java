package tile;

import com.javalearning.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum_Ground;
    public int[][] mapTileNum_Air;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[100];
        mapTileNum_Ground = new int [gp.maxWorldCol][gp.maxWorldRow];
        mapTileNum_Air = new int [gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/myWorld.txt", true);
        //loadMap("/maps/world02.txt", false);

    }

    public void getTileImage() {
        try{
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Grass_21.png")));
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Grass_22.png")));
            tile[23] = new Tile();
            tile[23].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Grass_23.png")));
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Ground_11.png")));
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Ground_12.png")));
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Ground_13.png")));
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Ground_14.png")));
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Ground_15.png")));
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Ground_16.png")));
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Ground_17.png")));
            tile[51] = new Tile();
            tile[51].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Pond_51.png")));
            tile[51].collision = true;
            tile[52] = new Tile();
            tile[52].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Pond_52.png")));
            tile[52].collision = true;
            tile[53] = new Tile();
            tile[53].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/River_53.png")));
            tile[54] = new Tile();
            tile[54].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/River_54.png")));
            tile[55] = new Tile();
            tile[55].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/River_55.png")));
            tile[56] = new Tile();
            tile[56].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/River_56.png")));
            tile[61] = new Tile();
            tile[61].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/River_61.png")));
            tile[61].collision = true;
            tile[62] = new Tile();
            tile[62].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/River_62.png")));
            tile[62].collision = true;
            tile[63] = new Tile();
            tile[63].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/River_63.png")));
            tile[63].collision = true;
            tile[64] = new Tile();
            tile[64].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/River_64.png")));
            tile[64].collision = true;
            tile[65] = new Tile();
            tile[65].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/River_65.png")));
            tile[65].collision = true;
            tile[66] = new Tile();
            tile[66].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/River_66.png")));
            tile[66].collision = true;
            tile[67] = new Tile();
            tile[67].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/River_67.png")));
            tile[67].collision = true;
            tile[68] = new Tile();
            tile[68].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/River_68.png")));
            tile[68].collision = true;
            tile[31] = new Tile();
            tile[31].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Tree_31.png")));
            tile[32] = new Tile();
            tile[32].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Tree_32.png")));
            tile[32].collision = true;




        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, Boolean isGround) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;


            while(col< gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine(); // read a line of content


                while(col < gp.maxWorldCol) {
                    // number = a number seperate by " "
                    String[] numbers = line.split(" "); // line.split: split this spring around matches of given regular expression
                    // using col as index
                    int num = Integer.parseInt(numbers[col]);
                    if(isGround){
                        mapTileNum_Ground[col][row] = num;
                    }else{
                        mapTileNum_Air[col][row] = num;
                    }
                    col++;
                }

                if (col == gp.maxWorldCol){

                    col = 0;
                    row++;
                }


            }

        }
        catch (Exception e){
            e.printStackTrace();

        }
    }

    public void draw(Graphics g2, boolean isGround) {

        int worldCol = 0;
        int worldRow = 0;
        int tileNum;


        while(worldCol< gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            if (isGround){
                tileNum = mapTileNum_Ground[worldCol][worldRow];
            }else {
                tileNum = mapTileNum_Air[worldCol][worldRow];
            }

            int worldX = worldCol *gp.tileSize;
            int worldY = worldRow *gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol ==gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
