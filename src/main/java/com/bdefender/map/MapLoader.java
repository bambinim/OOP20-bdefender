package com.bdefender.map;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public class MapLoader {

	private static MapLoader mapLoader = new MapLoader();
	
	private MapLoader() {}
	
	public static MapLoader getInstance() {
		return MapLoader.mapLoader;
	}
	
	public Map loadMap(int map) {
		Map res = new Map(this.loadMapImage(ClassLoader.getSystemResource(String.format("maps/%d/map.png", map))),
				this.loadPath(ClassLoader.getSystemResource(String.format("maps/%d/path.txt", map))));
		return res;
	}
	
	private Image loadMapImage(URL imageFile) {
		Image mapImage;
		try {
			mapImage = new Image(imageFile.openStream());
		} catch (Exception e) {
			mapImage = null;
		}
		return mapImage;
	}
	
	@SuppressWarnings("resource")
	private List<Coordinates> loadPath(URL coordsFile) {
		List<Coordinates> path = new ArrayList<Coordinates>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(coordsFile.openStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				path.add(this.parsePathLine(line));
			}
		} catch (Exception e) {
			path = List.of();
		}
		return path;
	}
	
	private Coordinates parsePathLine(String line) {
		String[] tmp = line.split("\\|");
		return new Coordinates(Double.valueOf(tmp[0]), Double.valueOf(tmp[1]));
	}
}
