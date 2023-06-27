package en.fluffyBerserk.gui.graphics.animations;

import en.fluffyBerserk.Constants;
import en.fluffyBerserk.gui.graphics.sprites.SpriteImage;
import en.fluffyBerserk.gui.utils.LocateImage;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class AnimationsFactory {
    public static ArrayList<Image> portal, bullets, bulletsEnemy;

    /**
     * Creates animations for portal
     */
    public static ArrayList<Image> portalAnimations() {
        portal = new ArrayList<>(4);

        portal.add(0, new Image("objects/portal/pt1.png", Constants.TILE_SIZE,Constants.TILE_SIZE,false, false));
        portal.add(1, new Image("objects/portal/pt2.png", Constants.TILE_SIZE,Constants.TILE_SIZE,false, false));
        portal.add(2, new Image("objects/portal/pt3.png", Constants.TILE_SIZE,Constants.TILE_SIZE,false, false));
        portal.add(3, new Image("objects/portal/pt4.png", Constants.TILE_SIZE,Constants.TILE_SIZE,false, false));

        return portal;
    }

    public static ArrayList<Image> bulletAnimations() {
        LocateImage sprite = new LocateImage("bullets/flufball_basic_sprite_8.png");
        bullets = new ArrayList<>(8);

        bullets.add(0, new SpriteImage(sprite, 0,0, Constants.TILE_SIZE, Constants.TILE_SIZE).getFrame());
        bullets.add(1, new SpriteImage(sprite, Constants.TILE_SIZE,0, Constants.TILE_SIZE, Constants.TILE_SIZE).getFrame());
        bullets.add(2, new SpriteImage(sprite, Constants.TILE_SIZE * 2, 0, Constants.TILE_SIZE, Constants.TILE_SIZE).getFrame());
        bullets.add(3, new SpriteImage(sprite, Constants.TILE_SIZE * 3,0, Constants.TILE_SIZE, Constants.TILE_SIZE).getFrame());
        bullets.add(4, new SpriteImage(sprite, Constants.TILE_SIZE * 4,0, Constants.TILE_SIZE, Constants.TILE_SIZE).getFrame());
        bullets.add(5, new SpriteImage(sprite, Constants.TILE_SIZE * 5,0, Constants.TILE_SIZE, Constants.TILE_SIZE).getFrame());
        bullets.add(6, new SpriteImage(sprite, Constants.TILE_SIZE * 6,0, Constants.TILE_SIZE, Constants.TILE_SIZE).getFrame());
        bullets.add(7, new SpriteImage(sprite, Constants.TILE_SIZE * 7,0, Constants.TILE_SIZE, Constants.TILE_SIZE).getFrame());

        return bullets;
    }

    public static ArrayList<Image> bulletAnimationsEnemy() {
        LocateImage sprite = new LocateImage("bullets/pantsu_basic_sprite.png");
        bulletsEnemy = new ArrayList<>(4);
        int pantsuTileSize = Constants.TILE_SIZE / 4 * 3;

        bulletsEnemy.add(0, new SpriteImage(sprite, 0,0, pantsuTileSize, pantsuTileSize).getFrame());
        bulletsEnemy.add(1, new SpriteImage(sprite, pantsuTileSize,0, pantsuTileSize, pantsuTileSize).getFrame());
        bulletsEnemy.add(2, new SpriteImage(sprite, 0, pantsuTileSize, pantsuTileSize, pantsuTileSize).getFrame());
        bulletsEnemy.add(3, new SpriteImage(sprite, pantsuTileSize, pantsuTileSize, pantsuTileSize, pantsuTileSize).getFrame());

        return bulletsEnemy;
    }
}
