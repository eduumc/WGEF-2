package us.edumc.WGEF2.listeners;

import us.edumc.WGEF2.WGEF2Plugin;
import us.edumc.WGEF2.flags.handlers.ElytraHandler;
import us.edumc.WGEF2.flags.handlers.EndermanHandler;
import us.edumc.WGEF2.flags.handlers.PistonHandler;
import org.bukkit.plugin.PluginManager;

public class WorldGuardListener {

    public WorldGuardListener(PluginManager pm) {
        pm.registerEvents(new ElytraHandler(), WGEF2Plugin.getInstance());
        pm.registerEvents(new EndermanHandler(), WGEF2Plugin.getInstance());
        pm.registerEvents(new PistonHandler(), WGEF2Plugin.getInstance());
    }
}