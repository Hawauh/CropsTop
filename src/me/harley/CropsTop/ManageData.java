package me.harley.CropsTop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class ManageData implements Listener {

	CropsTop main = CropsTop.getPlugin(CropsTop.class);

	static FileConfiguration data;

	static File dfile;

	public void setup(Plugin plugin) {
		if (!plugin.getDataFolder().exists())
			plugin.getDataFolder().mkdir();
		dfile = new File(plugin.getDataFolder(), "data.yml");
		if (!dfile.exists())
			try {
				dfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create data.yml!");
			}
		data = (FileConfiguration) YamlConfiguration.loadConfiguration(dfile);

		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			public void run() {
				saveData();
			}
		}, 0L, 20 * 60 * (main.getConfig().getInt("update-time")));
	}

	public static FileConfiguration getData() {
		return data;
	}

	public void uploadPlayers() {
		for (CropTopPlayer players : CropTopPlayer.players) {
			getData().set("stats." + players.getUuid().toString() + ".crops.sugar_cane_block",
					players.getSugarcaneCount());
			getData().set("stats." + players.getUuid().toString() + ".crops.cactus", players.getCactusCount());
			getData().set("stats." + players.getUuid().toString() + ".crops.wheat", players.getWheatCount());
			getData().set("stats." + players.getUuid().toString() + ".crops.pumpkin", players.getPumpkinCount());
			getData().set("stats." + players.getUuid().toString() + ".crops.melon", players.getMelonCount());
			getData().set("stats." + players.getUuid().toString() + ".crops.carrot", players.getCarrotCount());
			getData().set("stats." + players.getUuid().toString() + ".crops.potato", players.getPotatoCount());
			saveData();
		}
	}

	// create a new object for each player upon onEnable
	public static void registerPlayers() {
		for (String players : getData().getConfigurationSection("stats").getKeys(false)) {
			new CropTopPlayer(UUID.fromString(players), getBroken(players, "sugar_cane_block"),
					getBroken(players, "cactus"), getBroken(players, "wheat"), getBroken(players, "pumpkin"),
					getBroken(players, "melon"), getBroken(players, "carrot"), getBroken(players, "potato"));

		}
	}

	public void saveData() {
		try {
			data.save(dfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save data.yml!");
		}
	}

	@EventHandler
	public void onSetupPlayer(PlayerJoinEvent e) {
		setupPlayer(e.getPlayer());
	}

	public void setupPlayer(Player p) {
		String uuid = p.getUniqueId().toString();
		if (!(getData().contains("stats." + uuid)))
			new CropTopPlayer(p.getUniqueId(), 0, 0, 0, 0, 0, 0, 0);
		getData().set("stats." + uuid + ".crops.sugar_cane_block", 0);
		getData().set("stats." + uuid + ".crops.cactus", 0);
		getData().set("stats." + uuid + ".crops.wheat", 0);
		getData().set("stats." + uuid + ".crops.pumpkin", 0);
		getData().set("stats." + uuid + ".crops.melon", 0);
		getData().set("stats." + uuid + ".crops.carrot", 0);
		getData().set("stats." + uuid + ".crops.potato", 0);
		saveData();
	}

	public static int getBroken(String offlinePlayer, String crop) {
		return getData().getInt("stats." + offlinePlayer + ".crops." + crop);
	}

	public List<CropTopPlayer> getTop(String crop) {
		List<CropTopPlayer> ar = new ArrayList<CropTopPlayer>(CropTopPlayer.players);

		Collections.sort(ar, new Sort());
		Collections.reverse(ar);
		
		return ar;
	}

}
