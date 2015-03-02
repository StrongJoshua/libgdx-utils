package com.strongjoshua.libgdx_utils.geometry;

import com.badlogic.gdx.math.Vector2;

public class Line {
	public Vector2 start, stop;
	
	public Line(float x1, float y1, float x2, float y2) {
		start = new Vector2(x1, y1);
		stop = new Vector2(x2, y2);
	}
}
