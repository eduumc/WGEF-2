package us.edumc.WGEF2.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import us.edumc.WGEF2.WGEF2Plugin;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§aWGEF2 v" + WGEF2Plugin.getInstance().getDescription().getVersion());
            sender.sendMessage("§e/wgef2 reload §7- Recargar configuración");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("wgef2.reload")) {
                sender.sendMessage("§cNo tienes permiso para este comando!");
                return true;
            }

            WGEF2Plugin.getInstance().reloadConfig();
            sender.sendMessage("§aConfiguración recargada correctamente!");
            return true;
        }

        return false;
    }
}