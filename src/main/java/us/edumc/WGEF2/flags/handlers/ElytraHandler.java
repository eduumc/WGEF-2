package us.edumc.WGEF2.flags.handlers;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import us.edumc.WGEF2.WGEF2Plugin;
import us.edumc.WGEF2.flags.FlagManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleGlideEvent;

public class ElytraHandler implements Listener {

    @EventHandler
    public void onGlide(PlayerToggleGlideEvent event) {
        if (!event.isGliding()) return;

        Player player = event.getPlayer();
        ApplicableRegionSet regions = WGEF2Plugin.getInstance().getWorldGuard()
                .getRegionContainer()
                .createQuery()
                .getApplicableRegions(player.getLocation());

        if (regions.testState(null, FlagManager.NO_ELYTRA)) {
            event.setCancelled(true);
            player.setGliding(false);
            player.sendMessage("¡El uso de elytra está prohibido en esta área!");
        }
    }
}