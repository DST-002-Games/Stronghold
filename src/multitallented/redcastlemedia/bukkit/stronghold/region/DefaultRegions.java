package multitallented.redcastlemedia.bukkit.stronghold.region;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import multitallented.redcastlemedia.bukkit.stronghold.Stronghold;

import org.bukkit.block.Biome;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * 
 * @author Multitallented
 */
public class DefaultRegions
{
	public static void createDefaultRegionFiles(Stronghold plugin)
	{
		File folder = new File(plugin.getDataFolder(), "RegionConfig");
		try
		{
			folder.mkdirs();
			File shackFile = new File(folder, "CABIN.yml");
			shackFile.createNewFile();
			FileConfiguration config = new YamlConfiguration();
			config.load(shackFile);
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.add("denyblockbuildnoreagent.1");
			tempList.add("denyexplosion.1");
			tempList.add("denyblockbreak.1");
			tempList.add("denyplayerinteract.1");
			tempList.add("scheduledupkeep.7200");
			tempList.add("denybucketusenoreagent.1");
			tempList.add("denyfirenoreagent.1");
			tempList.add("denydamage.1");
			config.set("effects", tempList);
			tempList = new ArrayList<String>();
			config.set("radius", 4);
			tempList.add("WOODEN_DOOR.1");
			tempList.add("LOG.5");
			tempList.add("COBBLESTONE.10");
			config.set("requirements", new ArrayList<String>(tempList));
			tempList = new ArrayList<String>();
			tempList.add("GOLD_NUGGET.1");
			config.set("reagents", new ArrayList<String>(tempList));
			tempList = new ArrayList<String>();
			config.set("upkeep", new ArrayList<String>(tempList));
			tempList = new ArrayList<String>();
			tempList.add("WHEAT.1");
			config.set("output", new ArrayList<String>(tempList));
			config.set("upkeep-chance", 0.001);
			tempList = new ArrayList<String>();
			config.set("super-regions", new ArrayList<String>(tempList));
			config.set("money-requirement", 100.0);
			config.set("upkeep-money-output", 0.0);
			config.set("housing", 2);
			config.set("description", "Hold a settler. A basic villager building. This villager building can used in realms.");
			config.set("power-drain", 0.0);
			config.save(shackFile);
		} catch (Exception e)
		{

		}
	}

	public static void createDefaultSuperRegionFiles(Stronghold plugin)
	{
		File folder = new File(plugin.getDataFolder(), "SuperRegionConfig");
		try
		{
			folder.mkdirs();
			File shackFile = new File(folder, "CLAIM.yml");
			shackFile.createNewFile();
			FileConfiguration config = new YamlConfiguration();
			config.load(shackFile);
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.add("denyblockbuild");
			tempList.add("denyexplosion");
			tempList.add("denyblockbreak");
			tempList.add("denybucketuse");
			tempList.add("denyfirenoreagent");
			tempList.add("denyfriendlyfire");
			tempList.add("denyblockbuildnoreagent");
			tempList.add("denyexplosionnoreagent");
			tempList.add("denyblockbreaknoreagent");
			tempList.add("denybucketusenoreagent");
			tempList.add("denyfirenoreagent");
			tempList.add("denyfriendlyfire");
			tempList.add("denyfire");
			tempList.add("powershield");
			config.set("effects", tempList);
			config.set("radius", 40);
			config.set("requirements", new ArrayList<String>(tempList));
			config.set("money-requirement", 500.0);
			config.set("money-output-daily", -1.0);
			config.set("max-power", 1000);
			config.set("daily-power-increase", 10);
			config.set("exp", 10);
			tempList = new ArrayList<String>();
			tempList.add("BERTH");
			config.set("children",tempList);
			config.set("description", "Smallest settlement for player No buildings needed.");
			config.save(shackFile);

			shackFile = new File(folder, "BERTH.yml");
			shackFile.createNewFile();
			config = new YamlConfiguration();
			config.load(shackFile);
			tempList = new ArrayList<String>();
			tempList.add("denyblockbuild");
			tempList.add("denyexplosion");
			tempList.add("denyblockbreak");
			tempList.add("denybucketuse");
			tempList.add("denyfirenoreagent");
			tempList.add("denyfriendlyfire");
			tempList.add("denyblockbuildnoreagent");
			tempList.add("denyexplosionnoreagent");
			tempList.add("denyblockbreaknoreagent");
			tempList.add("denybucketusenoreagent");
			tempList.add("denyfirenoreagent");
			tempList.add("denyfriendlyfire");
			tempList.add("denyfire");
			tempList.add("powershield");
			config.set("effects", tempList);
			config.set("radius", 15);
			config.set("requirements", new ArrayList<String>(tempList));
			config.set("money-requirement", 50.0);
			config.set("money-output-daily", -1.0);
			config.set("max-power", 100);
			config.set("daily-power-increase", 10);
			config.set("exp", 10);
			tempList = new ArrayList<String>();
			tempList.add("VILLAGE");
			tempList.add("HAMLET");
			tempList.add("TOWN");
			tempList.add("CITY");
			tempList.add("METROPOLIS");
			tempList.add("LEHEN_1");
			tempList.add("LEHEN_2");
			tempList.add("LEHEN_3");
			tempList.add("LEHEN_4");
			config.set("children",tempList);
			tempList = new ArrayList<String>();
			tempList.add(Biome.BEACHES.name());
			tempList.add(Biome.COLD_BEACH.name());
			tempList.add(Biome.COLD_BEACH.name());
			tempList.add(Biome.STONE_BEACH.name());
			tempList.add(Biome.SWAMPLAND.name());
			tempList.add(Biome.RIVER.name());
			tempList.add(Biome.FROZEN_RIVER.name());
			tempList.add(Biome.MUSHROOM_ISLAND_SHORE.name());
			tempList.add(Biome.HELL.name());
			tempList.add(Biome.OCEAN.name());
			tempList.add(Biome.DEEP_OCEAN.name());
			tempList.add(Biome.FROZEN_OCEAN.name());
			config.set("biome",tempList);
			config.set("description", " Can used for boats and ships like in Movecraft, to drop down the anchor and protect the vessel.");
			config.save(shackFile);
		} catch (Exception e)
		{

		}
	}

	public static void migrateRegions(File regionFile, Stronghold plugin)
	{
		File folder = new File(plugin.getDataFolder(), "RegionConfig");
		try
		{
			FileConfiguration config = new YamlConfiguration();
			config.load(regionFile);
			Set<String> keys = config.getKeys(false);
			folder.mkdirs();
			for (String s : keys)
			{
				try
				{
					FileConfiguration currentConfig = new YamlConfiguration();
					ConfigurationSection cs = config.getConfigurationSection(s);
					File file = new File(folder, s + ".yml");
					file.createNewFile();
					currentConfig.load(file);
					for (String s0 : cs.getKeys(false))
					{
						currentConfig.set(s0, cs.get(s0));
					}
					currentConfig.save(file);
				} catch (Exception e)
				{
					System.out.println("[Stronghold] failed to migrate " + s
							+ ".yml");
				}
			}
		} catch (Exception e)
		{
			System.out.println("[Stronghold] failed to migrate regions.yml");
		}
	}

	public static void migrateSuperRegions(File sRegionFile, Stronghold plugin)
	{
		File folder = new File(plugin.getDataFolder(), "SuperRegionConfig");
		try
		{
			FileConfiguration config = new YamlConfiguration();
			config.load(sRegionFile);
			Set<String> keys = config.getKeys(false);
			folder.mkdirs();
			for (String s : keys)
			{
				FileConfiguration currentConfig = new YamlConfiguration();
				ConfigurationSection cs = config.getConfigurationSection(s);
				File file = new File(folder, s + ".yml");
				file.createNewFile();
				currentConfig.load(file);
				for (String s0 : cs.getKeys(false))
				{
					currentConfig.set(s0, cs.get(s0));
				}
				currentConfig.save(file);
			}
		} catch (Exception e)
		{
			System.out
					.println("[Stronghold] failed to migrate super-regions.yml");
		}
	}
}
