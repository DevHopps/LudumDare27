package me.hopps.ld27.utils.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ColorButton {
    String text;
    Rectangle bounds;
    BitmapFont font;
    Color normal, highlight;
    public boolean hovered;

    public ColorButton(String text, int x, int y, BitmapFont font, Color normal, Color highlight) {
        this.text = text;
        this.font = font;
        this.normal = normal;
        this. highlight = highlight;
        bounds = new Rectangle(x,y, font.getBounds(text).width, font.getBounds(text).height);
        //System.out.println(bounds.width);
    }

    public void render(SpriteBatch batch) {
        if(bounds.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
            hovered = true;
        } else {
            hovered = false;
        }
        if(hovered) {
            font.setColor(highlight);
        } else {
            font.setColor(normal);
        }
        font.draw(batch, text, bounds.x, bounds.y + font.getBounds(text).height);

        batch.setColor(Color.WHITE);
    }
}
