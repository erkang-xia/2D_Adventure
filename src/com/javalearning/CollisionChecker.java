package com.javalearning;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol =  entityLeftWorldX/gp.tileSize;
        int entityRightCol =  entityRightWorldX/gp.tileSize;
        int entityTopRow =  entityTopWorldY/gp.tileSize;
        int entityBottomRow =  entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                //Making a prediction with the spped and direction
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum_Ground[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum_Ground[entityRightCol][entityTopRow];
                //check if tile.collision is true
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionIsOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityTopWorldY + entity.speed * 6) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum_Ground[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum_Ground[entityRightCol][entityBottomRow];
                //check if tile.collision is true
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionIsOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum_Ground[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum_Ground[entityRightCol][entityBottomRow];
                //check if tile.collision is true
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionIsOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum_Ground[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum_Ground[entityRightCol][entityBottomRow];
                //check if tile.collision is true
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionIsOn = true;
                }
            }
        }



    }

    public int checkObject(Entity entity,boolean player) {
        //check if player hit any objects and return the index of the object
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){


                //Get entity's (player) solidArea position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get the object's solidArea position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision){
                                entity.collisionIsOn= true;
                            }
                            if(player){ // only player can pickup items
                                index = i;
                            }
                        }
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision){
                                entity.collisionIsOn= true;
                            }
                            if(player){ // only player can pickup items
                                index = i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision){
                                entity.collisionIsOn= true;
                            }
                            if(player){ // only player can pickup items
                                index = i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision){
                                entity.collisionIsOn= true;
                            }
                            if(player){ // only player can pickup items
                                index = i;
                            }
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
