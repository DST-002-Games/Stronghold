package multitallented.redcastlemedia.bukkit.stronghold.listeners;

import multitallented.redcastlemedia.bukkit.stronghold.Stronghold;
import multitallented.redcastlemedia.bukkit.stronghold.events.RegionDestroyedEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 *
 * @author Multitallented
 */
public class CustomListener implements Listener {
    private final Stronghold hs;
    public CustomListener(Stronghold hs) {
        this.hs = hs;
    }
    
    @EventHandler
    public void onCustomEvent(RegionDestroyedEvent event) {
        //Check if a super region needs to fall if a required region was destroyed
        hs.getRegionManager().checkIfDestroyedSuperRegion(event.getRegion().getLocation());
    }
}