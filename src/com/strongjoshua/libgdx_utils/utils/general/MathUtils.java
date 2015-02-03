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
}