package com.svsos.core.utils.gis;

import java.util.List;

public class GISUtils {

	private static final double a = 6378137; // 长半轴
	// private static final double b = 6356752.3142; // 短半轴
	// private static final double f = 1 / 298.257223563; // 参考椭球体偏率
	private static final double e1 = 0.0066943799013; // 第一偏心率
	private static final double e2 = 0.00673949674227; // 第二偏心率
	private static final double PI = Math.PI;

	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 根据GPS经纬度转换对应的平面坐标
	 * 
	 * @param lng
	 * @param lat
	 * @return
	 */
	public static Point getPixelByGPS(double lng, double lat) {
		Point p = new Point(lng, lat);
		double L = lng * PI / 180;
		double B = lat * PI / 180;
		int n = (int) lng / 6 + 1;
		int L0 = n * 6 - 3;
		double t = Math.tan(B);
		double q = e2 * Math.pow(Math.cos(B), 2);
		double L00 = L0 * PI / 180;
		double m = Math.cos(B) * (L - L00);
		double m0 = a * (1 - e1);
		double m2 = 3.0 * e1 * m0 / 2.0;
		double m4 = 5.0 * e1 * m2 / 4.0;
		double m6 = 7.0 * e1 * m4 / 6.0;
		double m8 = 9.0 * e1 * m6 / 8.0;
		double a0 = m0 + m2 / 2.0 + 3.0 * m4 / 8.0 + 5.0 * m6 / 16.0 + 35.0 * m8 / 128;
		double a2 = m2 / 2.0 + m4 / 2.0 + 15.0 * m6 / 32.0 + 7.0 * m8 / 16.0;
		double a4 = m4 / 8.0 + 3.0 * m6 / 16.0 + 7.0 * m8 / 32.0;
		double a6 = m6 / 32.0 + m8 / 16.0;
		double a8 = m8 / 128.0;
		double A0 = a0;
		double B0 = a2;
		double C0 = a4;
		double D0 = a6;
		double E0 = a8;
		double S = (A0 * B - B0 * Math.sin(2.0 * B) / 2.0 + C0 * Math.sin(4.0 * B) / 4.0 - D0 * Math.sin(6.0 * B) / 6.0 + E0
				* Math.sin(8.0 * B) / 8.0); // 子午线弧长
		double N = a / Math.sqrt(1 - Math.pow(e1, 2) * Math.pow(Math.sin(B), 2));// 卯酉圈曲率半径
		double Y = N
				* ((1 + ((1 - t * t + q) / 6.0 + (5.0 - 18.0 * t * t + Math.pow(t, 4) + 14 * q - 58 * q * t * t) * m
						* m / 120.0)
						* m * m) * m);
		double coordX = S
				+ N
				* t
				* ((0.5 + ((5.0 - t * t + 9 * q + 4 * q * q) / 24 + (61.0 - 58.0 * t * t + Math.pow(t, 4)) * m * m
						/ 720.0)
						* m * m)
						* m * m);

		// 根据中国国情坐标纵轴西移500公里当作起始轴
		double coordY = 1000000 * n + 500000 + Y;
		p.setCoordX((double) Math.round(coordX * 1000) / 1000);
		p.setCoordY((double) Math.round(coordY * 1000) / 1000);
		return p;
	}

	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * 
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double distance(double lng1, double lat1, double lng2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * 
	 * 判断当前经纬度是否在多边形区域中
	 * 
	 * @param longitude
	 *            当前经度
	 * @param latitude
	 *            当前纬度
	 * @param polyRangPoint
	 *            多边形区域点集合
	 * @return
	 */
	public static boolean isInPoly(double longitude, double latitude, List<double[]> polyRangPoint) {
		int index = 0, count = 0, sum = 0;
		double dLon1 = 0, dLon2 = 0, dLat1 = 0, dLat2 = 0, dLon = 0;
		count = polyRangPoint.size();
		if (count < 3) {
			return false;
		}
		for (index = 0; index < count; index++) {
			if (index == count - 1) {
				dLon1 = polyRangPoint.get(index)[0];
				dLat1 = polyRangPoint.get(index)[1];
				dLon2 = polyRangPoint.get(0)[0];
				dLat1 = polyRangPoint.get(0)[1];
			}
			else {
				dLon1 = polyRangPoint.get(index)[0];
				dLat1 = polyRangPoint.get(index)[1];
				dLon2 = polyRangPoint.get(index + 1)[0];
				dLat1 = polyRangPoint.get(index + 1)[1];
			}

			if ((latitude >= dLat1 && latitude < dLat2) || (latitude >= dLat2 && latitude < dLat1)) {
				if (Math.abs(dLat1 - dLat2) > 0) {
					dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - latitude)) / (dLat1 - dLat2);
					if (dLon < longitude) {
						sum++;
					}
				}
			}
		}
		return sum % 2 != 0;
	}

	public static void main(String[] args) {
		// System.out.println("距离为:" + distance(120.574249, 31.316127,
		// 120.575504, 31.316279));
		System.out.println("距离为:" + distance(120.725764, 31.357212, 120.72573837182, 31.357218559547));
	}
}
