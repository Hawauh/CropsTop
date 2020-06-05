package me.harley.CropsTop;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.harley.inventory.CropCatergoryInventory;
import me.harley.inventory.Top9Inventory;

public class CropsTop extends JavaPlugin implements Listener {

	public void onEnable() {
		saveDefaultConfig();
		new ManageData().setup(this);
		getCommand("cropstop").setExecutor(new CropsTopCmd());
		getServer().getPluginManager().registerEvents(new CropCatergoryInventory(), this);
		getServer().getPluginManager().registerEvents(new Top9Inventory(), this);
		getServer().getPluginManager().registerEvents(new Events(), this);
		getServer().getPluginManager().registerEvents(new ManageData(), this);
		ManageData.registerPlayers();


	}

	@Override
	public void onDisable() {
	}

}
