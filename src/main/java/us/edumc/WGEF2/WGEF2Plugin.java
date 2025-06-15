package us.edumc.WGEF2;

import com.sk89q.worldguard.WorldGuard;
import us.edumc.WGEF2.commands.MainCommand;
import us.edumc.WGEF2.configuration.ConfigManager;
import us.edumc.WGEF2.flags.FlagManager;
import us.edumc.WGEF2.listeners.WorldGuardListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class WGEF2Plugin extends JavaPlugin {

    private static WGEF2Plugin instance;
    private FlagManager flagManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        // Verificar que WorldGuard está cargado
        if (Bukkit.getPluginManager().getPlugin("WorldGuard") == null) {
            getLogger().severe("WorldGuard no encontrado! Desactivando plugin...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        configManager = new ConfigManager();
        flagManager = new FlagManager();
        flagManager.registerFlags();

        // Registrar listeners
        new WorldGuardListener(Bukkit.getPluginManager());

        getCommand("wgef2").setExecutor(new MainCommand());

        getLogger().info("WGEF2 activado! Versión: " + getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        getLogger().info("WGEF2 desactivado!");
    }

    public static WGEF2Plugin getInstance() {
        return instance;
    }

    public FlagManager getFlagManager() {
        return flagManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    // Método corregido para obtener RegionContainer
    public com.sk89q.worldguard.protection.regions.RegionContainer getRegionContainer() {
        return WorldGuard.getInstance().getPlatform().getRegionContainer();
    }
}
