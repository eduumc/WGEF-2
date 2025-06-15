package us.edumc.WGEF2.flags;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import us.edumc.WGEF2.flags.custom.*;

public class FlagManager {

    public static NoElytraFlag NO_ELYTRA;
    public static AntiEndermanGriefFlag ANTI_ENDERMAN_GRIEF;
    public static BlockPistonTranslocFlag BLOCK_PISTON_TRANSLOC;

    public void registerFlags() {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();

        // Registrar banderas
        registry.register(NO_ELYTRA = new NoElytraFlag("no-elytra"));
        registry.register(ANTI_ENDERMAN_GRIEF = new AntiEndermanGriefFlag("anti-enderman-grief"));
        registry.register(BLOCK_PISTON_TRANSLOC = new BlockPistonTranslocFlag("block-piston-transloc"));
    }
}