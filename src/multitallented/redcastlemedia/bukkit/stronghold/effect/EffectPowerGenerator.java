package multitallented.redcastlemedia.bukkit.stronghold.effect;

import java.util.Date;
import java.util.HashMap;
import multitallented.redcastlemedia.bukkit.stronghold.Stronghold;
import multitallented.redcastlemedia.bukkit.stronghold.effect.Effect;
import multitallented.redcastlemedia.bukkit.stronghold.events.UpkeepEvent;
import multitallented.redcastlemedia.bukkit.stronghold.region.Region;
import multitallented.redcastlemedia.bukkit.stronghold.region.RegionType;
import multitallented.redcastlemedia.bukkit.stronghold.region.SuperRegion;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 *
 * @author Multitallented
 */
public class EffectPowerGenerator extends Effect {
    private HashMap<Location, Long> lastUpkeep = new HashMap<Location, Long>();
    
    public EffectPowerGenerator(Stronghold plugin) {
        super(plugin);
        registerEvent(new UpkeepListener(plugin, this));
    }
    
    @Override
    public void init(Stronghold plugin) {
        super.init(plugin);
    }
    
    public class UpkeepListener implements Listener {
        private final EffectPowerGenerator effect;
        private final Stronghold plugin;
        public UpkeepListener(Stronghold plugin, EffectPowerGenerator effect) {
            this.effect = effect;
            this.plugin = plugin;
        }
        
        
        @EventHandler
        public void onCustomEvent(UpkeepEvent event) {
            Location l = event.getLocation();
            Region r = getPlugin().getRegionManager().getRegion(event.getLocation());
            if (r == null) {
                return;
            }
            RegionType rt = getPlugin().getRegionManager().getRegionType(r.getType()); 
            
            //Check if the region has the shoot arrow effect and return arrow velocity
            long period = effect.regionHasEffect(rt.getEffects(), "powergenerator");
            if (period == 0) {
                return;
            }
            
            period *= 1000;            
            
            if (lastUpkeep.get(l) != null && period + lastUpkeep.get(l) > new Date().getTime()) {
                return;
            }
            
            //Check to see if the Stronghold has enough reagents
            if (!effect.hasReagents(l)) {
                return;
            }
            //Run upkeep but don't need to know if upkeep occured
            lastUpkeep.put(l, new Date().getTime());
            
            for (SuperRegion sr : plugin.getRegionManager().getContainingSuperRegions(l)) {
                if (sr.getPower() < plugin.getRegionManager().getSuperRegionType(sr.getType()).getMaxPower()) {
                    sr.setPower(sr.getPower() + 1);
                    effect.forceUpkeep(event);
                }
            }
        }
    }
    
}
