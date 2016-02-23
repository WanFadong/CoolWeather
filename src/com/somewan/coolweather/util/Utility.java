package com.somewan.coolweather.util;

import android.text.TextUtils;

import com.somewan.coolweather.db.CoolWeatherDB;
import com.somewan.coolweather.model.City;
import com.somewan.coolweather.model.County;
import com.somewan.coolweather.model.Province;

/**
 * 负责解析服务器发送过来的数据，并把解析出来的数据存储到数据库中。
 */
public class Utility {
	public synchronized static boolean handleProvinceResponse(CoolWeatherDB db,
			String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String s : allProvinces) {
					String[] array = s.split("|");
					Province p = new Province();
					p.setCode(array[0]);
					p.setName(array[1]);
					db.saveProvince(p);
				}
				return true;
			}
		}
		return false;

	}

	public static boolean handleCityResponse(CoolWeatherDB db, String response,
			int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCity = response.split(",");
			if (allCity != null && allCity.length > 0) {
				for (String s : allCity) {
					String[] array = s.split("|");
					City city = new City();
					city.setCode(array[0]);
					city.setName(array[1]);
					city.setProvinceId(provinceId);
					db.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}

	public static boolean handleCountyResponse(CoolWeatherDB db,
			String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounty = response.split(",");
			if (allCounty != null && allCounty.length > 0) {
				for (String s : allCounty) {
					String[] array = s.split("|");
					County county = new County();
					county.setCode(array[0]);
					county.setName(array[1]);
					county.setCityId(cityId);
					db.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
