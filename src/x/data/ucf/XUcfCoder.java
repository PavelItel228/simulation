package x.data.ucf;

import entity.Plants.FruitPlant;
import entity.Plants.Plant;
import entity.humans.Human;
import entity.humans.Woman;
import entity.landscape.Landscape;

// Класс для дешифрации данных про обьекты
// Использует поля маски и сдвига для шифрации у кажного класса
public class XUcfCoder {
	
	public final long encodeLandscapeType(long uc, int u) {
		return encode(uc, u, Landscape.LANDSCAPE_TYPE_MASK, Landscape.LANDSCAPE_TYPE_SHIFT);
	}
	
	public final int decodeLandscapeType(long uc) {
		return decode(uc, Landscape.LANDSCAPE_TYPE_MASK, Landscape.LANDSCAPE_TYPE_SHIFT);
	}

	public final long encodeHumanType(long uc, int u) {
		return encode(uc, u, Human.HUMAN_TYPE_MASK, Human.HUMAN_TYPE_SHIFT);
	}
	
	public final int decodeHumanType(long uc) {
		return decode(uc, Human.HUMAN_TYPE_MASK, Human.HUMAN_TYPE_SHIFT);
	}
	
	public final long encodeHumanAge(long uc, int u) {
		return encode(uc, u, Human.HUMAN_AGE_MASK, Human.HUMAN_AGE_SHIFT);
	}
	
	public final int decodeHumanAge(long uc) {
		return decode(uc, Human.HUMAN_AGE_MASK, Human.HUMAN_AGE_SHIFT);
	}
	
	public final long encodeHumanEnergy(long uc, int u) {
		return encode(uc, u, Human.HUMAN_ENERGY_MASK, Human.HUMAN_ENERGY_SHIFT);
	}
	
	public final int decodeHumanEnergy(long uc) {
		return decode(uc, Human.HUMAN_ENERGY_MASK, Human.HUMAN_ENERGY_SHIFT);
	}

	public final long encodeHumanSatiety(long uc, int u) {
		return encode(uc, u, Human.HUMAN_SATIETY_MASK, Human.HUMAN_SATIETY_SHIFT);
	}
	
	public final int decodeHumanSatiety(long uc) {
		return decode(uc, Human.HUMAN_SATIETY_MASK, Human.HUMAN_SATIETY_SHIFT);
	}
	
	public final long encodeHumanPregnancy(long uc, int u) {
		return encode(uc, u, Woman.WOMAN_PREGNANCY_MASK, Woman.WOMAN_PREGNANCY_SHIFT);
	}
	
	public final int decodeHumanPregnancy(long uc) {
		return decode(uc, Woman.WOMAN_PREGNANCY_MASK, Woman.WOMAN_PREGNANCY_SHIFT);
	}
	
	public final long encodePlantType(long uc, int u) {
		return encode(uc, u, Plant.PLANT_TYPE_MASK, Plant.PLANT_TYPE_SHIFT);
	}
	
	public final int decodePlantType(long uc) {
		return decode(uc, Plant.PLANT_TYPE_MASK, Plant.PLANT_TYPE_SHIFT);
	}
	
	public final long encodePlantFruits(long uc, int u) {
		return encode(uc, u, FruitPlant.PLANT_FRUITS_MASK, FruitPlant.PLANT_FRUITS_SHIFT);
	}
	
	public final int decodePlantFruits(long uc) {
		return decode(uc, FruitPlant.PLANT_FRUITS_MASK, FruitPlant.PLANT_FRUITS_SHIFT);
	}

	public final  long encodeActiveFlagHuman(long uc, int u) {
		return encode(uc, u, Human.ACTIVE_FLAG_HUMAN_MASK, Human.ACTIVE_FLAG_HUMAN_SHIFT);
	}
	
	public final int decodeActiveFlagHuman(long uc) {
		return decode(uc, Human.ACTIVE_FLAG_HUMAN_MASK, Human.ACTIVE_FLAG_HUMAN_SHIFT);
	}

	public final long encodeActiveFlagPlant(long uc, int u) {
		return encode(uc, u, Plant.ACTIVE_FLAG_PLANT_MASK, Plant.ACTIVE_FLAG_PLANT_SHIFT);
	}
	
	public final int decodeActiveFlagPlant(long uc) {
		return decode(uc, Plant.ACTIVE_FLAG_PLANT_MASK, Plant.ACTIVE_FLAG_PLANT_SHIFT);
	}
	
	// Example:
	// ===================================================================================

	// 1. ~mask
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 000|1 1|000
	// ~
	// -----------------------------------------------------------------------------------
	// 1111 1111 1111 1111 1111 1111 1111 1111   1111 1111 1111 1111 1111 1111 111|0 0|111 

	// 2. (uc & ~mask)	
	// 0000 0000 0000 0000 0110 0000 0111 0001   1010 0000 0100 1110 0010 0000 000|1 0|000
	// &
	// 1111 1111 1111 1111 1111 1111 1111 1111   1111 1111 1111 1111 1111 1111 111|0 0|111
	// -----------------------------------------------------------------------------------
	// 0000 0000 0000 0000 0110 0000 0111 0001   1010 0000 0100 1110 0010 0000 000|0 0|000

	// 3. (long) u
	// 0000 0000 0000 0000 0000 0000 0000 00|01|
	// (long)
	// -----------------------------------------------------------------------------------
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 0000 00|01|
	
	// 4. (long) u << shift
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 0000 00|01|
	// << 3
	// -----------------------------------------------------------------------------------
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 000|0 1|000


	// 5. ((long) u << shift & mask) - additional protection
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 000|0 1|000
	// &
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 000|1 1|000
	// -----------------------------------------------------------------------------------
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 000|0 1|000
	
	// 6. (uc & ~mask) | ((long) u << shift & mask)
	// 0000 0000 0000 0000 0110 0000 0111 0001   1010 0000 0100 1110 0010 0000 000|0 0|000
	// |
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 000|0 1|000
	// -----------------------------------------------------------------------------------
	// 0000 0000 0000 0000 0110 0000 0111 0001   1010 0000 0100 1110 0010 0000 000|0 1|000

	private long encode(long uc, int u, long mask, int shift) {
		return (uc & ~mask) | ((long) u << shift & mask);
	}
	
	// Example:
	// ===================================================================================

	// 1. (uc & mask)	
	// 0000 0000 0000 0000 0110 0000 0111 0001   1010 0000 0100 1110 0010 0000 000|0 1|000
	// &
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 000|1 1|000
	// -----------------------------------------------------------------------------------
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 000|0 1|000

	// 2. ((uc & mask) >>> 3)
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 000|0 1|000
	// >>> 3
	// -----------------------------------------------------------------------------------
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 0000 00|01|

	// 3. (int) ((uc & mask) >>> shift)
	// 0000 0000 0000 0000 0000 0000 0000 0000   0000 0000 0000 0000 0000 0000 0000 00|01|
	// (int)
	// -----------------------------------------------------------------------------------
	// 0000 0000 0000 0000 0000 0000 0000 00|01|

	private int decode(long uc, long mask, int shift) {
		return (int) ((uc & mask) >>> shift);
	}
	
}
