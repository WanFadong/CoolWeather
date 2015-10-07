package com.somewan.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	public static final String CREATE_PROVINCE_TABLE = "create table province("
			+ "id integer primary key autoincrement," + "code text,"
			+ "name text)";
	public static final String CREATE_CITY_TABLE = "create table city("
			+ "id integer primary key autoincrement," + "code text,"
			+ "name text," + "province_id integer)";
	public static final String CREATE_COUNTY_TABLE = "create table county("
			+ "id integer primary key autoincrement," + "code text,"
			+ "name text," + "city_id integer)";

	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE_TABLE);
		db.execSQL(CREATE_CITY_TABLE);
		db.execSQL(CREATE_COUNTY_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
