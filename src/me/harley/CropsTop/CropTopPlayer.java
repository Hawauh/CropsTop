package me.harley.CropsTop;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CropTopPlayer {

	static List<CropTopPlayer> players = new ArrayList<CropTopPlayer>();

	private UUID uuid;

	private int sugarcaneCount;
	private int cactusCount;
	private int wheatCount;
	private int pumpkinCount;
	private int melonCount;
	private int carrotCount;
	private int potatoCount;

	public CropTopPlayer(UUID uuid, int sugarcaneCount, int cactusCount, int wheatCount, int pumpkinCount,
			int melonCount, int carrotCount, int potatoCount) {
		this.setUuid(uuid);
		this.setSugarcaneCount(sugarcaneCount);
		this.setCactusCount(cactusCount);
		this.setWheatCount(wheatCount);
		this.setPumpkinCount(pumpkinCount);
		this.setMelonCount(melonCount);
		this.setCarrotCount(carrotCount);
		this.setPotatoCount(potatoCount);
		players.add(this);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public int getSugarcaneCount() {
		return sugarcaneCount;
	}

	public void setSugarcaneCount(int sugarcaneCount) {
		this.sugarcaneCount = sugarcaneCount;
	}

	public int getCactusCount() {
		return cactusCount;
	}

	public void setCactusCount(int cactusCount) {
		this.cactusCount = cactusCount;
	}

	public int getPumpkinCount() {
		return pumpkinCount;
	}

	public void setPumpkinCount(int pumpkinCount) {
		this.pumpkinCount = pumpkinCount;
	}

	public int getWheatCount() {
		return wheatCount;
	}

	public void setWheatCount(int wheatCount) {
		this.wheatCount = wheatCount;
	}

	public int getMelonCount() {
		return melonCount;
	}

	public void setMelonCount(int melonCount) {
		this.melonCount = melonCount;
	}

	public int getCarrotCount() {
		return carrotCount;
	}

	public void setCarrotCount(int carrotCount) {
		this.carrotCount = carrotCount;
	}

	public int getPotatoCount() {
		return potatoCount;
	}

	public void setPotatoCount(int potatoCount) {
		this.potatoCount = potatoCount;
	}

	public static CropTopPlayer getCropTopPlayer(UUID uuid) {
		for (CropTopPlayer players : players) {
			if (players.getUuid().equals(uuid)) {
				return players;
			}
		}
		return null;
	}

	public static Player getPlayer(CropTopPlayer cropTopPlayer) {
		return Bukkit.getPlayer(cropTopPlayer.getUuid());

	}

}
