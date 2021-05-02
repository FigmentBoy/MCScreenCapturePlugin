package io.github.figmentboy.screencapture;

import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;

public class UpdateLoop extends BukkitRunnable {
    @Override
    public void run() {
        try {

            for (int i = 0; i < ScreenCapture.renderers.length; i++) {
                Image image = new Robot().createScreenCapture(new Rectangle(ScreenCapture.renderers[i].getOffsetX(), ScreenCapture.renderers[i].getOffsetY(), 2560/ScreenCapture.xmax, 2560/ScreenCapture.xmax)).getScaledInstance(128, 128, Image.SCALE_REPLICATE);

                ScreenCapture.renderers[i].setImage(image);
            }


        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
