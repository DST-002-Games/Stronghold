package multitallented.redcastlemedia.bukkit.stronghold.effect;

import multitallented.redcastlemedia.bukkit.stronghold.Stronghold;
import multitallented.redcastlemedia.bukkit.stronghold.effect.Effect;
import multitallented.redcastlemedia.bukkit.stronghold.region.Region;
import multitallented.redcastlemedia.bukkit.stronghold.region.RegionManager;
import multitallented.redcastlemedia.bukkit.stronghold.region.RegionType;
import org.bukkit.Location;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

/**
 * 
 * @author Sevaron
 */
public class EffectDenyMobSpawn extends Effect implements Listener
{
	 
	public final Stronghold aPlugin;

	public EffectDenyMobSpawn(Stronghold plugin)
	{
		super(plugin);
		this.aPlugin = plugin;
		this.registerEvent(this);
	}

	@Override
	public void init(Stronghold plugin)
	{
		super.init(plugin);
	}

	@EventHandler
	public void onCustomEvent(CreatureSpawnEvent event)
	{
		if (event.isCancelled() || event.getSpawnReason() == SpawnReason.CUSTOM
				|| !(event.getEntity() instanceof Monster))
		{
			return;
		}

		Location l = event.getLocation();
		RegionManager rm = this.getPlugin().getRegionManager();

		for (Region r : rm.getContainingRegions(l))
		{
			RegionType rt = rm.getRegionType(r.getType());
			if (regionHasEffect(rt.getEffects(), "denymobspawn") != 0
					&& hasReagents(r.getLocation()))
			{
				event.setCancelled(true);
				return;
			} else if (regionHasEffect(rt.getEffects(), "denymobspawnnoreagent") != 0)
			{
				event.setCancelled(true);
				return;
			}
		}
	}

}