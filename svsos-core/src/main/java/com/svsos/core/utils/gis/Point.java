package com.svsos.core.utils.gis;

/**
 * 坐标点对象
 * 
 * @author ranfi
 * 
 */
public class Point {

	private Double longtitude; // 经度
	private Double latitude; // 纬度
	private Double coordX; // 平面X坐标,对应经度
	private Double coordY; // 平面Y坐标,对应纬度

	public Point(Double longtitude, Double latitude) {
		this.longtitude = longtitude;
		this.latitude = latitude;
	}

	public Double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getCoordX() {
		return coordX;
	}

	public void setCoordX(Double coordX) {
		this.coordX = coordX;
	}

	public Double getCoordY() {
		return coordY;
	}

	public void setCoordY(Double coordY) {
		this.coordY = coordY;
	}

	@Override
	public String toString() {
		return this.getCoordX() + "," + this.getCoordY();
	}

}
