package multitallented.redcastlemedia.bukkit.stronghold.effect;

import java.util.ArrayList;
import multitallented.redcastlemedia.bukkit.stronghold.Stronghold;
import multitallented.redcastlemedia.bukkit.stronghold.effect.Effect;
import multitallented.redcastlemedia.bukkit.stronghold.events.UpkeepSuccessEvent;
import multitallented.redcastlemedia.bukkit.stronghold.region.Region;
import multitallented.redcastlemedia.bukkit.stronghold.region.RegionManager;
import multitallented.redcastlemedia.bukkit.stronghold.region.RegionType;
import multitallented.redcastlemedia.bukkit.stronghold.region.SuperRegion;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
/**
 *
 * @author Multitallented
 */
public class EffectDrainPower extends Effect {
    private final RegionManager rm;
    public EffectDrainPower(Stronghold plugin) {
        super(plugin);
        this.rm = plugin.getRegionManager();
        registerEvent(new IntruderListener(this));
    }
    
    @Override
    public void init(Stronghold plugin) {
        super.init(plugin);
    }
    
    public class IntruderListener implements Listener {
        private final EffectDrainPower effect;
        public IntruderListener(EffectDrainPower effect) {
            this.effect = effect;
        }
        
        @EventHandler
        public void onCustomEvent(UpkeepSuccessEvent event) {
            Region r = rm.getRegion(event.getRegionLocation());
            RegionType rt = rm.getRegionType(r.getType());
            if (effect.regionHasEffect(r, "drainpower") == 0) {
                return;
            }
            ArrayList<SuperRegion> srs = rm.getContainingSuperRegions(r.getLocation());
            if (srs.isEmpty()) {
                return;
            }
        }
    }
    
}
