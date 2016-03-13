package multitallented.redcastlemedia.bukkit.stronghold.listeners;

import com.herocraftonline.heroes.Heroes;
import java.util.logging.Logger;

import multitallented.redcastlemedia.bukkit.stronghold.Stronghold;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

/**
 * 
 * @author Multitallented
 */
public class PluginServerListener implements Listener
{
	private Stronghold plugin;
	private Heroes heroes;

	public PluginServerListener(Stronghold p)
	{
		this.plugin = p;
	}

	@EventHandler
	public void onPluginDisable(PluginDisableEvent event)
	{
		Plugin currentPlugin = event.getPlugin();
		String name = currentPlugin.getDescription().getName();

		// if (name.equals("Heroes")) {
		// Logger log = Logger.getLogger("Minecraft");
		// String message = "[Stronghold] " + name + " has been disabled!";
		// log.info(message);
		// Stronghold.heroes = null;
		// }
	}

	@EventHandler
	public void onPluginEnable(PluginEnableEvent event)
	{
		Plugin currentPlugin = event.getPlugin();
		String name = currentPlugin.getDescription().getName();

		// if (name.equals("Heroes")) {
		// Stronghold.heroes = (Heroes) currentPlugin;
		// Logger log = Logger.getLogger("Minecraft");
		// log.info("[Stronghold] Successfully hooked Heroes.");
		// }
	}
}
