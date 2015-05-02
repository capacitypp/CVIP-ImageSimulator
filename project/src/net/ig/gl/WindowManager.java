package net.ig.gl;

import net.ig.util.Bounds;

public class WindowManager {
	private static Bounds bounds;

	public static Bounds getBounds() {
		return bounds;
	}

	public static void setBounds(Bounds bounds) {
		WindowManager.bounds = bounds;
	}

}
