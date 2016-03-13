/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multitallented.redcastlemedia.bukkit.stronghold.events;

import java.util.ArrayList;

import multitallented.redcastlemedia.bukkit.stronghold.region.Region;

import org.bukkit.Location;

/**
 *
 * @author Multitallented
 */
public interface HSEvent {
    public void setRegionsToCreate(ArrayList<Region> regions);
    public ArrayList<Region> getRegionsToCreate();
    public void setRegionsToDestroy(ArrayList<Location> regions);
    public ArrayList<Location> getRegionsToDestroy();
    public Location getLocation();
    
}
