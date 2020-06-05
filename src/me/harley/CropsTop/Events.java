package me.harley.CropsTop;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Events implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCropBreak(BlockBreakEvent e) {
		UUID uuid = e.getPlayer().getUniqueId();
		CropTopPlayer p = CropTopPlayer.getCropTopPlayer(uuid);
		Block block = e.getBlock();
		switch (block.getType()) {
		case CROPS:
			if (block.getData() == 7)
				p.setWheatCount(p.getWheatCount() + 1);
			break;
		case CACTUS:
			p.setCactusCount(p.getCactusCount() + getStack(block));
			break;
		case SUGAR_CANE_BLOCK:
			p.setSugarcaneCount(p.getSugarcaneCount() + getStack(block));
			break;
		case PUMPKIN:
			p.setPumpkinCount(p.getPumpkinCount() + 1);
			break;
		case MELON_BLOCK:
			p.setMelonCount(p.getMelonCount() + 1);
			break;
		case CARROT:
			if (block.getData() == 7)
				p.setCarrotCount(p.getCarrotCount() + 1);
			break;
		case POTATO:
			if (block.getData() == 7)
				p.setPotatoCount(p.getPotatoCount() + 1);
			break;
		default:
			break;
		}
	}

	public int getStack(Block b) {
		int amount = 1;
		Block aboveBlock = b.getRelative(BlockFace.UP);
		Block blockAboveThat = aboveBlock.getRelative(BlockFace.UP);
		if (b.getType() == Material.SUGAR_CANE_BLOCK || b.getType() == Material.CACTUS) {
			if (aboveBlock.getType() == b.getType())
				amount++;
			if (blockAboveThat.getType() == b.getType())
				amount++;
		}
		return amount;
	}
}
