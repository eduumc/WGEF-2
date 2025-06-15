package us.edumc.WGEF2.flags.handlers;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import us.edumc.WGEF2.WGEF2Plugin;
import us.edumc.WGEF2.flags.FlagManager;
import org.bukkit.entity.Enderman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EndermanHandler implements Listener {

    @EventHandler
    public void onEndermanGrief(EntityChangeBlockEvent event) {
        if (!(event.getEntity() instanceof Enderman)) return;

        ApplicableRegionSet regions = WGEF2Plugin.getInstance().getWorldGuard()
                .getRegionContainer()
                .createQuery()
                .getApplicableRegions(event.getBlock().getLocation());

        if (regions.testState(null, FlagManager.ANTI_ENDERMAN_GRIEF)) {
            event.setCancelled(true);
        }
    }
}