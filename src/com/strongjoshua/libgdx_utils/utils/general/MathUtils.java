package com.strongjoshua.libgdx_utils.utils.general;

/**
 * A collection of simple convenience methods that can be used in mathematical operations.
 * 
 * @author StrongJoshua
 */
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
		int i = (int) f;
		if(abs(i) / abs(f) == 1)
			return i;
		else
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
		if(min >= max)
			throw new IllegalArgumentException("Min must be less than max. " + min + " >= " + max);

		if(i < min)
			return min;
		if(i > max)
			return max;

		return i;
	}

	public static float clamp(float f, float min, float max) {
		if(min >= max)
			throw new IllegalArgumentException("Min must be less than max. " + min + " >= " + max);

		if(f < min)
			return min;
		if(f > max)
			return max;

		return f;
	}

	public static double clamp(double d, double min, double max) {
		if(min >= max)
			throw new IllegalArgumentException("Min must be less than max. " + min + " >= " + max);

		if(d < min)
			return min;
		if(d > max)
			return max;

		return d;
	}

	public static float abs(float f) {
		return f < 0 ? -f : f;
	}

	public static int abs(int i) {
		return i < 0 ? -i : i;
	}

	public static double abs(double d) {
		return d < 0 ? -d : d;
	}
}