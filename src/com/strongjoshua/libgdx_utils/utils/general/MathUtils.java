package com.strongjoshua.libgdx_utils.utils.general;

public class MathUtils {

	/**
	 * @param f
	 * @return 1 with the appropriate sign of f.
	 */
	public static float unitize(float f) {
		return Math.abs(f) / f;
	}

	/**
	 * @param i
	 * @return 1 with the appropriate sign of f.
	 */
	public static int unitize(int i) {
		return Math.abs(i) / i;
	}
	
	public static int ciel(float f) {
		return ((int) f) + 1;
	}
	
	public static int floor(float f) {
		boolean neg = f < 0;
		int r = (int) f;
		
		if(neg)
			r -= 1;
		
		return r;
	}
	
	public static int clamp(int i, int min, int max) {
		if(min >= max) throw new IllegalArgumentException("Min must be less than max. " + min + " >= " + max);
		
		if(i < min) return min;
		if(i > max) return max;
		
		return i;
	}
	
	public static float clamp(float f, float min, float max) {
		if(min >= max) throw new IllegalArgumentException("Min must be less than max. " + min + " >= " + max);
		
		if(f < min) return min;
		if(f > max) return max;
		
		return f;
	}
}