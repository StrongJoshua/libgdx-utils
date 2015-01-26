package com.strongjoshua.libgdx_utils.utils.game_utils.level_creation_2d.framework;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * For use in 2D level creation. Rooms are created based off of a 2D array where a room may span multiple array 'rows' and 'columns'.
 * 
 * @author StrongJoshua
 *
 */
public class Room {
	private Rectangle rect;

	/**
	 * Creates a room with given array coordinates, width, and height.
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public Room(int x, int y, int w, int h) {
		rect = new Rectangle(x, y, w, h);
	}

	public Rectangle getBounds() {
		return rect;
	}

	public Vector2 getCenter() {
		return rect.getCenter(new Vector2());
	}

	public boolean intersects(Room r) {
		return rect.overlaps(r.getBounds());
	}
	
	public int getArea() {
		return (int) (rect.width * rect.height);
	}

	/**
	 * Assumes the given array has walls set to '1'. Sets room tiles to '0'.
	 * @param tiles
	 */
	public void place(int[][] tiles) {
		for(int x = (int) rect.x; x < rect.x + rect.width; x++) {
			for(int y = (int) rect.y; y < rect.y + rect.height; y++) {
				tiles[x][y] = 0;
			}
		}
	}

	/**
	 * Connects this room to the given room, using straight corridors. Sets corridor tiles to '0'.
	 * @param r The room to connect to.
	 * @param tiles The map's tile array.
	 */
	public void connectTo(Room r, int[][] tiles) {
		Vector2 c1 = getCenter(), c2 = r.getCenter();

		// connect center x values
		int dif = MathUtils.floor(c2.x - c1.x);
		for(; Math.abs(dif) >= 0; dif = (Math.abs(dif) - 1) * dif / Math.abs(dif)) {
			tiles[MathUtils.floor(c1.x + dif)][MathUtils.floor(c1.y)] = 0;
			if(dif == 0) break;
		}
		// connect center y values
		dif = MathUtils.floor(c1.y - c2.y);
		for(; Math.abs(dif) >= 0; dif = (Math.abs(dif) - 1) * dif / Math.abs(dif)) {
			tiles[MathUtils.floor(c2.x)][MathUtils.floor(c2.y + dif)] = 0;
			if(dif == 0) break;
		}
	}
}