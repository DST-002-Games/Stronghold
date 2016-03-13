package multitallented.redcastlemedia.bukkit.stronghold.checkregiontask;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import multitallented.redcastlemedia.bukkit.stronghold.Stronghold;
import multitallented.redcastlemedia.bukkit.stronghold.events.TwoSecondEvent;
import multitallented.redcastlemedia.bukkit.stronghold.region.Region;
//import net.minecraft.server.v1_8_R2.PlayerSelector;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

/**
 *
 * @author Multitallented
 */
public class CheckRegionTask implements Runnable {
    private final transient Server server;
    private final Stronghold hs;
    private final Set<Location> regionsToDestroy = new HashSet<Location>();
    private final HashSet<Region> regionsToCreate = new HashSet<Region>();
    private int i= 0;
    
    public CheckRegionTask(Server server, Stronghold hs) {
        this.server = server;
        this.hs = hs;
    }
    
    public synchronized void addOrDestroyRegionToDestroy(Location l) {
        if (!regionsToDestroy.remove(l)) {
            regionsToDestroy.add(l);
        }
    }
    
    public synchronized void addRegionToCreate(Region r) {
        regionsToCreate.add(r);
    }
    
    public boolean containsRegionToDestory(Location l) {
        return regionsToDestroy.contains(l);
    }
    
    public HashSet<Region> getRegiosToCreate(Region r) {
        return regionsToCreate;
    }

    @Override
    public void run() {
        Collection<? extends Player> players = server.getOnlinePlayers();
        Player[] playersA =  new Player[players.size()];
        int index = 0;
        for (Player p : players)
        {
        	playersA[index] = p;
        	index++;
        }
        int chunk = players.size()/4; // length / 4;
        PluginManager pm = server.getPluginManager();
        
        for (int j=chunk * i; j<(i==3 ? playersA.length: chunk * (i+1)); j++) 
        {
            try {
                CheckPlayerInRegionThread thread = new CheckPlayerInRegionThread(this, pm, hs.getRegionManager(), playersA[j]);
                thread.run();
            } catch (Exception e) {
                
            }
        }
        if (i == 3) {
            i=-1;
            
            
            for (Location l : hs.getRegionManager().getRegionLocations()) {
                CheckUpkeepThread thread = new CheckUpkeepThread(this, pm, hs.getRegionManager(), l);
                try {
                    thread.run();
                } catch (Exception e) {
                }
            }
            pm.callEvent(new TwoSecondEvent());
        } else {
            i++;
        }
        
        for (Location l : regionsToDestroy) {
            hs.getRegionManager().destroyRegion(l);
            hs.getRegionManager().removeRegion(l);
        }
        regionsToDestroy.clear();
        for (Region r : regionsToCreate) {
            hs.getRegionManager().addRegion(r.getLocation(), r.getType(), r.getOwners(), r.getMembers());
        }
        regionsToCreate.clear();
    }
}
