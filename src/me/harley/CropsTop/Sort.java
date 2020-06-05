package me.harley.CropsTop;

import java.util.Comparator;

class Sort implements Comparator<CropTopPlayer> {
	public int compare(CropTopPlayer a, CropTopPlayer b) {
		return a.getCactusCount() - b.getCactusCount();
	}
}
