package io.github.figmentboy.screencapture;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;


public final class ScreenCapture extends JavaPlugin {
    public static int xmax = 3;
    public static int ymax = 2;
    public static ScreenRenderer[] renderers = new ScreenRenderer[xmax*ymax];

    UpdateLoop loop = new UpdateLoop();
    @Override
    public void onEnable() {
        this.getCommand("map").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        for (int x = 0; x < xmax; x++) {
            for (int y = 0; y < ymax; y++) {
                ItemStack i = new ItemStack(Material.FILLED_MAP, 1);
                MapMeta meta = (MapMeta) i.getItemMeta();

                MapView view = Bukkit.getMap(x*ymax + y /* :D */);
                for (MapRenderer mr : view.getRenderers()) {
                    view.removeRenderer(mr);
                }

                renderers[x*ymax+y] = new ScreenRenderer(x*2560/xmax, y*2560/xmax, view);
                view.addRenderer(renderers[x*ymax+y]);

                meta.setMapView(view);
                i.setItemMeta(meta);

                p.getInventory().addItem(i);
            }
        }
        loop.runTaskTimer(this, 0, 1);
        return true;
    }



}
