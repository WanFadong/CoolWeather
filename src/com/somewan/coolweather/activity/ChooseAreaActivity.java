package com.somewan.coolweather.activity;

import java.util.ArrayList;
import java.util.List;

import com.somewan.coolweather.R;
import com.somewan.coolweather.db.CoolWeatherDB;
import com.somewan.coolweather.model.City;
import com.somewan.coolweather.model.County;
import com.somewan.coolweather.model.Province;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseAreaActivity extends Activity {
	public static final int LEVEL_PROVINCE = 0;
	public static final int LEVEL_CITY = 1;
	public static final int LEVEL_COUNTY = 2;

	private ProgressDialog progressDialog;
	private TextView titleTextView;
	private ListView placeListView;
	private ArrayAdapter<String> adapter;
	private List<String> placeList = new ArrayList<String>();
	private CoolWeatherDB db;

	private List<Province> provinceList;
	private List<City> cityList;
	private List<County> countyList;

	private Province selectedProvince;
	private City selectedCity;
	private int currentLevel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_area);
		titleTextView = (TextView) findViewById(R.id.title_text);
		placeListView = (ListView) findViewById(R.id.place_list);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, placeList);
		db = CoolWeatherDB.getInstance(this);
		placeListView.setAdapter(adapter);
		placeListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int index,
					long arg3) {
				if (currentLevel == LEVEL_PROVINCE) {
					selectedProvince = provinceList.get(index);
					queryCity();
				} else if (currentLevel == LEVEL_CITY) {
					selectedCity = cityList.get(index);
					queryCounty();
				}
			}
		});
		queryProvince();
	}

	private void queryCounty() {
		// TODO Auto-generated method stub

	}

	private void queryCity() {
		// TODO Auto-generated method stub

	}

	private void queryProvince(){
		provinceList=db.loadProvince();
		if(provinceList.size()>0){
			placeList.clear();
			for(Province province:provinceList){
				placeList.add(province.getName());
			}
			adapter.notifyDataSetChanged();
			placeListView.setSelection(0);
			titleTextView.setText("中国");
			currentLevel=LEVEL_PROVINCE;
		}else{
			queryFromSever(null,"province");
		}
	}

	private void queryFromSever(String code, String type) {
		// TODO Auto-generated method stub

	}
}
