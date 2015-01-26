package com.strongjoshua.libgdx_utils.utils.game_utils.level_creation_2d;

import com.badlogic.gdx.math.MathUtils;
import com.strongjoshua.libgdx_utils.utils.game_utils.level_creation_2d.framework.Room;
import com.strongjoshua.libgdx_utils.utils.general.ArrayUtils;

public class SimpleMapGenerator {
	private int worldWidth, worldHeight;
	private int maxRooms, maxRoomWidth, maxRoomHeight, minRoomWidth, minRoomHeight;
	private int[][] tiles;
	private Room[] rooms;

	/**
	 * Constructs a new SimpleMapCreator object. setRoomParameters must be called before creating a map.
	 * 
	 * @see SimpleMapGenerator#setRoomParameters(int, int, int, int, int)
	 */
	public SimpleMapGenerator(int width, int height) {
		worldWidth = width;
		worldHeight = height;
		tiles = new int[width][height];
		ArrayUtils.setAll(tiles, 1);
	}

	/**
	 * Must be called before creating a room.
	 */
	public void setRoomParameters(int maxRooms, int maxRoomWidth, int maxRoomHeight, int minRoomWidth, int minRoomHeight) {
		this.maxRooms = maxRooms;
		this.maxRoomWidth = maxRoomWidth;
		this.maxRoomHeight = maxRoomHeight;
		this.minRoomWidth = minRoomWidth;
		this.minRoomHeight = minRoomHeight;
	}

	/**
	 * Assumes all rooms are squares.
	 * 
	 * @see SimpleMapGenerator#setRoomParameters(int, int, int, int, int)
	 */
	public void setRoomParameters(int maxRooms, int maxRoomSize, int minRoomSize) {
		setRoomParameters(maxRooms, maxRoomSize, maxRoomSize, minRoomSize, minRoomSize);
	}

	/**
	 * Returns the tiles of this map. Wall tiles are '1's and open tiles are '0's.
	 */
	public int[][] getTiles() {
		return tiles;
	}

	/**
	 * Creates the map. Call {@link SimpleMapGenerator#getTiles()} to get the result.
	 * 
	 * @see SimpleMapGenerator#getBiggestRoom()
	 */
	public void createMap() {
		int x, y, w, h;
		rooms = new Room[maxRooms];

		for(int i = 0; i < maxRooms; i++) {
			x = MathUtils.floor(MathUtils.random(worldWidth - maxRoomWidth));
			y = MathUtils.floor(MathUtils.random(worldHeight - maxRoomHeight));
			w = MathUtils.random(minRoomWidth, maxRoomWidth);
			h = MathUtils.random(minRoomHeight, maxRoomHeight);

			rooms[i] = new Room(x, y, w, h);
			if(i > 0) {
				if(rooms[i].intersects(rooms[i - 1])) {
					i--;
					continue;
				}
				else
					rooms[i].connectTo(rooms[i - 1], tiles);
			}
			rooms[i].place(tiles);
		}
	}

	/**
	 * <i>{@link SimpleMapGenerator#createMap()} must have been called.</i><br><br>
	 * Can be used for initial placement of the character.
	 * 
	 * @return The {@link Room} object of the biggest room in the map.
	 */
	public Room getBiggestRoom() {
		int b = 0;
		for(int i = 0; i < rooms.length; i++) {
			if(rooms[i].getArea() > rooms[b].getArea())
				b = i;
		}
		try {
			return rooms[b];
		}
		catch(ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException("You did not call createMap() or no rooms were created in said process.");
		}
	}
}
