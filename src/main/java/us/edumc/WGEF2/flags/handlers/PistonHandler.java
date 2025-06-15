package us.edumc.WGEF2.flags.handlers;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import us.edumc.WGEF2.WGEF2Plugin;
import us.edumc.WGEF2.flags.FlagManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

import java.util.List;

public class PistonHandler implements Listener {

    @EventHandler
    public void onPistonExtend(BlockPistonExtendEvent event) {
        Block pistonBlock = event.getBlock();
        ApplicableRegionSet pistonRegions = getRegionsForBlock(pistonBlock);

        if (pistonRegions.testState(null, FlagManager.BLOCK_PISTON_TRANSLOC)) {
            List<Block> blocks = event.getBlocks();
            BlockFace direction = event.getDirection();

            for (Block block : blocks) {
                Block targetBlock = block.getRelative(direction);
                ApplicableRegionSet targetRegions = getRegionsForBlock(targetBlock);

                if (!areRegionSetsEqual(pistonRegions, targetRegions)) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onPistonRetract(BlockPistonRetractEvent event) {
        if (!event.isSticky()) return;

        Block pistonBlock = event.getBlock();
        ApplicableRegionSet pistonRegions = getRegionsForBlock(pistonBlock);

        if (pistonRegions.testState(null, FlagManager.BLOCK_PISTON_TRANSLOC)) {
            List<Block> blocks = event.getBlocks();
            BlockFace direction = event.getDirection();

            for (Block block : blocks) {
                Block targetBlock = block.getRelative(direction);
                ApplicableRegionSet targetRegions = getRegionsForBlock(targetBlock);

                if (!areRegionSetsEqual(pistonRegions, targetRegions)) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    private ApplicableRegionSet getRegionsForBlock(Block block) {
        Location location = block.getLocation();
        RegionContainer container = WGEF2Plugin.getInstance().getRegionContainer();
        return container.createQuery().getApplicableRegions(location);
    }

    private boolean areRegionSetsEqual(ApplicableRegionSet set1, ApplicableRegionSet set2) {
        return set1.getRegions().equals(set2.getRegions());
    }
}