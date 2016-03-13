package multitallented.redcastlemedia.bukkit.stronghold.effect;

/**
 * The effectManager are widely change.
 * The effects now direct instanced in uthe manger and NOT read from a 
 * jar-file.  
 * 
 * @author Windu
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import multitallented.redcastlemedia.bukkit.stronghold.Stronghold;

/**
 * 
 * @author Windu
 *
 */
public class EffectManager
{
	private final LinkedHashMap<String, Effect> effects;
	private final Stronghold plugin;

	public EffectManager(Stronghold plugin)
	{
		effects = new LinkedHashMap<String, Effect>();
		this.plugin = plugin;
		
		effects.put(EffectDenyMobSpawn.class.getName(), new EffectDenyMobSpawn(plugin));
		effects.put(EffectDrainPower.class.getName(), new EffectDrainPower(plugin));
		effects.put(EffectFood.class.getName(), new EffectFood(plugin));
		effects.put(EffectGainHealth.class.getName(), new EffectGainHealth(plugin));
		effects.put(EffectGainStamina.class.getName(), new EffectGainStamina(plugin));
		effects.put(EffectGate.class.getName(), new EffectGate(plugin));
		effects.put(EffectPeriodicUpkeep.class.getName(), new EffectPeriodicUpkeep(plugin));
		effects.put(EffectPowerCapacitor.class.getName(), new EffectPowerCapacitor(plugin));
		effects.put(EffectPowerGenerator.class.getName(), new EffectPowerGenerator(plugin));
		effects.put(EffectPowerShield.class.getName(), new EffectPowerShield(plugin));
		effects.put(EffectRepair.class.getName(), new EffectRepair(plugin));
		effects.put(EffectScheduledUpkeep.class.getName(), new EffectScheduledUpkeep(plugin));
		effects.put(EffectShootArrow.class.getName(), new EffectShootArrow(plugin));
		effects.put(EffectSiegeWeapon.class.getName(), new EffectSiegeWeapon(plugin));
//		effects.put(EffectWilderness.class.getName(), new EffectWilderness(plugin));
	}

	public Effect getEffect(String name)
	{
		if (name == null)
			return null;
		return effects.get(name);
	}

	public boolean hasEffect(String name)
	{
		return effects.containsKey(name);
	}


}
