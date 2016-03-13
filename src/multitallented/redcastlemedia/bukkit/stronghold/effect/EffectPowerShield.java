package multitallented.redcastlemedia.bukkit.stronghold.effect;

import multitallented.redcastlemedia.bukkit.stronghold.Stronghold;
import multitallented.redcastlemedia.bukkit.stronghold.effect.Effect;
import multitallented.redcastlemedia.bukkit.stronghold.region.RegionCondition;
import multitallented.redcastlemedia.bukkit.stronghold.region.RegionManager;
import multitallented.redcastlemedia.bukkit.stronghold.region.SuperRegion;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

/**
 *
 * @author Multitallented
 */
public class EffectPowerShield extends Effect {
    public EffectPowerShield(Stronghold plugin) {
        super(plugin);
        registerEvent(new EffectPowerShield.UpkeepListener(this));
    }
    
    @Override
    public void init(Stronghold plugin) {
        super.init(plugin);
        
    }
    
    public class UpkeepListener implements Listener {
        private final EffectPowerShield effect;
        private RegionManager rm;
        public UpkeepListener(EffectPowerShield effect) {
            this.effect = effect;
            rm = effect.getPlugin().getRegionManager();
        }
        
        @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
        public void onEntityExplode(EntityExplodeEvent event) {
            Entity e = event.getEntity();
//            if (!e.getClass().equals(TNTPrimed.class)) {
//                System.out.println("No TNT !");
//                return;
//            }
            if (rm.shouldTakeAction(event.getLocation(), null, new RegionCondition("powershield", true, 0))) {
                boolean powerReduced = false;
                for (SuperRegion sr : rm.getContainingSuperRegions(event.getLocation())) {
//                    if (sr.getPower() > 0 && rm.getRegionType(sr.getType()).getEffects().contains("powershield")) {
                      if (sr.getPower() > 0 ) {
                        powerReduced = true;
                        rm.reduceRegion(sr);
                        System.out.println("Shield active!");
                        event.setCancelled(true);
                    }
                }
                if (!powerReduced) {
                    event.setCancelled(true);
                }
            }
        }
    }
}