package io.github.figmentboy.screencapture;

import org.bukkit.entity.Player;
import org.bukkit.map.*;

import java.awt.*;

public class ScreenRenderer extends MapRenderer {
    private Image image;
    private MapView view;

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    private int offsetX, offsetY = 0;

    public ScreenRenderer(int x, int y, MapView view) {
        this.offsetX = x;
        this.offsetY = y;
        this.view = view;
    }

    public void setImage(Image image) {
        this.image = image;
    }


    public void render(MapView map, MapCanvas canvas, Player player) {
        if (this.image != null) {
            canvas.drawImage(0, 0, this.image);
        }

    }

    public MapView getView() {
        return view;
    }
}