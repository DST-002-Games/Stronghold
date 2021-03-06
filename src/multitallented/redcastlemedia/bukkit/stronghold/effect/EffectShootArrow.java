package multitallented.redcastlemedia.bukkit.stronghold.effect;

import multitallented.redcastlemedia.bukkit.stronghold.Stronghold;
import multitallented.redcastlemedia.bukkit.stronghold.effect.Effect;
import multitallented.redcastlemedia.bukkit.stronghold.events.PlayerInRegionEvent;
import multitallented.redcastlemedia.bukkit.stronghold.region.RegionManager;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

/**
 *
 * @author Multitallented
 */
public class EffectShootArrow extends Effect {
    private final RegionManager rm;
    public EffectShootArrow(Stronghold plugin) {
        super(plugin);
        this.rm = plugin.getRegionManager();
        registerEvent(new IntruderListener(this));
    }
    
    @Override
    public void init(Stronghold plugin) {
        super.init(plugin);
    }
    
    public class IntruderListener implements Listener {
        private final EffectShootArrow effect;
        public IntruderListener(EffectShootArrow effect) {
            this.effect = effect;
        }
        
        @EventHandler
        public void onCustomEvent(PlayerInRegionEvent event) {
            
            Location l = event.getLocation();
            //Check if the region has the shoot arrow effect and return arrow velocity
            double speed = effect.regionHasEffect(effect.rm.getRegionType(effect.rm.getRegion(l).getType()).getEffects(), "shootarrow");
            if (speed == 0)
                return;
            
            Player player = event.getPlayer();
            
            //Check if the player owns or is a member of the region
            if (effect.isOwnerOfRegion(player, l) || effect.isMemberOfRegion(player, l)) {
                return;
            }
            
            //Check to see if the Stronghold has enough reagents
            if (!effect.hasReagents(l)) {
                return;
            }
            
            //Run upkeep but don't need to know if upkeep occured
            effect.forceUpkeep(event);
            
            //Calculate trajectory of the arrow
            Location loc = l.getBlock().getRelative(BlockFace.UP, 2).getLocation();
            Location playerLoc = player.getLocation();
            Vector vel = new Vector(playerLoc.getX() - loc.getX(), playerLoc.getY() - loc.getY(), playerLoc.getZ() - loc.getZ());
            
            //Spawn and set velocity of the arrow
            Arrow arrow = l.getWorld().spawnArrow(loc, vel, (float) (0.2 * speed), 12);
            arrow.setVelocity(vel.multiply(speed));
        }
    }
    
}
