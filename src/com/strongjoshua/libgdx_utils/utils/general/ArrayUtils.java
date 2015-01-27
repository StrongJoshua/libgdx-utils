/**
 * Copyright 2015 StrongJoshua (swampert_555@yahoo.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package com.strongjoshua.libgdx_utils.utils.general;

import com.badlogic.gdx.utils.Array;

public class ArrayUtils {
	
	/**
	 * Sets all of the integers in the array to the given value.
	 * @param a
	 * @param v
	 */
	public static void setAll(int[][] a, int v) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				a[i][j] = v;
			}
		}
	}
	
	/**
	 * Prints 0 for null and 1 for non-null for a 2D array. Assumes [x][y].
	 * @param o The array of objects.
	 */
	public static void printNonNull(Object[][] o) {
		for(int i = 0; i < o[0].length; i++) {
			for(int j = 0; j < o.length; j++) {
				System.out.print(o[j][i] == null ? 0 : 1);
				if(j < o.length - 1)
					System.out.print(", ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Converts an ArrayList into a type casted array (ArrayList.toArray() returns an un-castable Object array).
	 * @param toConvert
	 * @return The array of objects with the type of the given ArrayList.
	 */
	public static <T> T[] convertToArray(Array<T> toConvert) {
		int size = toConvert.size;
		@SuppressWarnings("unchecked")
		T[] converted = (T[]) java.lang.reflect.Array.newInstance(toConvert.get(0).getClass(), size);
		for(int i = 0; i < size; i++) {
			converted[i] = toConvert.get(i);
		}
		return converted;
	}
	
	/**
	 * Concatenates 2 arrays.
	 * @param a1 The first array.
	 * @param a2 The second array.
	 * @return Both arrays combined into one array.
	 */
	public static <T> T[] concatArrays(T[] a1, T[] a2)
	{
		@SuppressWarnings("unchecked")
		T[] both = (T[]) java.lang.reflect.Array.newInstance(a1.getClass().getComponentType(), a1.length + a2.length);

		for(int i = 0; i < a1.length; i++)
			both[i] = a1[i];

		for(int i = 0; i < a2.length; i++)
			both[i + a1.length] = a2[i];

		return both;
	}
	
	/**
	 * Prints an array with values separated by commas. Output does not wrap.
	 * @param o
	 */
	public static void printArray(Object[] o) {
		for(int i = 0; i < o.length; i++) {
			System.out.print(o[i]);
			if(i < o.length - 1) System.out.print(", ");
		}
		System.out.println();
	}
	
	/**
	 * Converts an object array of Floats to a primitive array.
	 * @param floats
	 * @return The primitive float[]
	 */
	public static float[] toPrimitive(Float[] floats) {
		float[] primitive = new float[floats.length];
		for(int i = 0; i < floats.length; i++) {
			primitive[i] = floats[i];
		}
		return primitive;
	}
}