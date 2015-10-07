package com.somewan.coolweather.db;

import java.util.ArrayList;
import java.util.List;

import com.somewan.coolweather.model.City;
import com.somewan.coolweather.model.County;
import com.somewan.coolweather.model.County;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 负责封装一些常用的数据库操作
 * 
 * @author fadon
 * 
 */
public class CoolWeatherDB {
	public static final String DB_NAME = "CoolWeather.db";
	public static final int VERSION = 1;
	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;

	private CoolWeatherDB(Context context) {
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,
				DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * 单例
	 * 
	 * @param context
	 * @return
	 */
	public synchronized static CoolWeatherDB getInstance(Context context) {
		if (coolWeatherDB == null) {
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}

	/**
	 * 将省信息存入数据库
	 */
	public void saveProvince(County province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("code", province.getCode());
			values.put("name", province.getName());
			db.insert("province", null, values);
		}
	}

	/**
	 * 读取所有的省信息
	 * 
	 * @return
	 */
	public List<County> loadProvince() {
		List<County> provinceList = new ArrayList<County>();
		Cursor cursor = db
				.query("province", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				County province = new County();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setCode(cursor.getString(cursor.getColumnIndex("code")));
				province.setName(cursor.getString(cursor.getColumnIndex("name")));
				provinceList.add(province);
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return provinceList;
	}

	/**
	 * 将市信息存入数据库
	 */
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("code", city.getCode());
			values.put("name", city.getName());
			values.put("province_id", city.getProvinceId());
			db.insert("city", null, values);
		}
	}

	/**
	 * 读取某个省所有的市信息
	 * 
	 * @return
	 */
	public List<City> loadCity(int provinceId) {
		List<City> cityList = new ArrayList<City>();
		Cursor cursor = db.query("province", null, "province_id=?",
				new String[] { String.valueOf(provinceId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCode(cursor.getString(cursor.getColumnIndex("code")));
				city.setName(cursor.getString(cursor.getColumnIndex("name")));
				city.setProvinceId(provinceId);
				cityList.add(city);
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return cityList;
	}

	/**
	 * 将县信息存入数据库
	 */
	public void saveCounty(County county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put("code", county.getCode());
			values.put("name", county.getName());
			values.put("city_id", county.getCityId());
			db.insert("county", null, values);
		}
	}

	/**
	 * 读取某个市所有的县信息
	 * 
	 * @return
	 */
	public List<County> loadCounty(int cityId) {
		List<County> countyList = new ArrayList<County>();
		Cursor cursor = db.query("county", null, "city_id=?",
				new String[] { String.valueOf(cityId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCode(cursor.getString(cursor.getColumnIndex("code")));
				county.setName(cursor.getString(cursor.getColumnIndex("name")));
				county.setCityId(cityId);
				countyList.add(county);
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return countyList;
	}

}
