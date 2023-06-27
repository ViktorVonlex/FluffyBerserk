package en.fluffyBerserk.game.logic.objects;

import javafx.scene.image.Image;

public interface Object {

    void setX(float x);

    void setY(float y);

    float getX();

    float getY();

    int getHeight();

    int getWidth();

    int getHitBoxHeight();

    int getHitBoxWidth();

    float getHitBoxX();

    float getHitBoxY();

    void setHitBoxX(float x);

    void setHitBoxY(float y);

    Image getImage();
}
