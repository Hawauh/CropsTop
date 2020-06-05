package me.harley.CropsTop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.harley.inventory.CropCatergoryInventory;

public class CropsTopCmd implements CommandExecutor {

	CropsTop cropstop = CropsTop.getPlugin(CropsTop.class);

	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("cropstop")) {
			if (args.length == 0)
				new CropCatergoryInventory().createInventory(p);
			else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("update")) {
					if (p.hasPermission("cropstop.update")) {
						new ManageData().uploadPlayers();
					}
					p.sendMessage(cropstop.getConfig().getString("updated").replace("&", "§"));
				} else
					p.sendMessage(cropstop.getConfig().getString("no-perms").replace("&", "§"));
			}
		}
		return true;
	}

}
