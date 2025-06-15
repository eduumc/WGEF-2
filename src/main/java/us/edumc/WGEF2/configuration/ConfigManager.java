package us.edumc.WGEF2.configuration;

import us.edumc.WGEF2.WGEF2Plugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigManager {

    private final FileConfiguration config;

    public ConfigManager() {
        this.config = WGEF2Plugin.getInstance().getConfig();
    }

    public List<String> getEnabledFlags() {
        return config.getStringList("flags.enabled");
    }

    public boolean isFlagEnabled(String flagName) {
        return getEnabledFlags().contains(flagName);
    }
}