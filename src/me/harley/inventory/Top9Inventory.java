package me.harley.inventory;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.harley.CropsTop.CropTopPlayer;
import me.harley.CropsTop.CropsTop;
import me.harley.CropsTop.ManageData;
import me.harley.utils.SkullCreator;

public class Top9Inventory extends ManageData implements InventoryHolder, Listener {

	CropsTop cropstop = CropsTop.getPlugin(CropsTop.class);

	public void createInventory(Player p, String crop) {
		final Inventory inv;
		inv = Bukkit.createInventory(this, 9, cropstop.getConfig().getString("gui-name-" + crop).replace("&", "§"));

		if (getBroken(p.getUniqueId().toString(), crop) == 0) {
			p.sendMessage(cropstop.getConfig().getString("no-stats-exist").replace("&", "§").replace("{crop}", crop));
			return;
		}
		for (int i = 0; i < getTop(crop).size(); i++) {
			inv.setItem(i, getTopItem(crop, getTop(crop).get(i).getUuid().toString(),
					getAmountOfPlayer(crop, getTop(crop).get(i).getUuid()) + "", (i + 1)));
		}
		p.openInventory(inv);
	}

	@Override
	public Inventory getInventory() {
		return null;
	}

	@SuppressWarnings("deprecation")
	public ItemStack getTopItem(String crop, String uuid, String amount, int place) {
		ItemStack item;
		item = new SkullCreator().itemFromName(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(
				"§f§n" + Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName() + "§7§o (#" + place + ")");
		meta.setLore(Arrays.asList("§cAmount Broken: §f§n" + amount));
		meta.setOwner(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
		item.setItemMeta(meta);
		return item;
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if (e.getInventory().getSize() == 9) {
			if (e.getInventory().getItem(0).getType() == Material.SKULL_ITEM) {
				e.setCancelled(true);
			}
		}

	}

	public int getAmountOfPlayer(String crop, UUID uuid) {
		CropTopPlayer p = CropTopPlayer.getCropTopPlayer(uuid);
		int amount = 0;
		if (crop == "sugar_cane_block")
			amount = p.getSugarcaneCount();
		if (crop == "cactus")
			amount = p.getCactusCount();
		if (crop == "wheat")
			amount = p.getWheatCount();
		if (crop == "pumpkin")
			amount = p.getPumpkinCount();
		if (crop == "melon")
			amount = p.getMelonCount();
		if (crop == "carrot")
			amount = p.getCarrotCount();
		if (crop == "potato")
			amount = p.getPotatoCount();
		return amount;
	}

}
