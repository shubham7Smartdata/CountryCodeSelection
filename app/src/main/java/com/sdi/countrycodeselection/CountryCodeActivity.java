package com.sdi.countrycodeselection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class CountryCodeActivity extends Activity {

	public static int w = 0, h = 0;
	private Phone_Adp adapter = new Phone_Adp();
	private GestureDetector mGestureDetector;
	private List<Object[]> alphabet;
	private HashMap<String, Integer> sections = new HashMap<String, Integer>();
	private int sideIndexHeight;
	private static float sideIndexX;
	private static float sideIndexY;
	private int indexListSize;
	public static HashMap<String, String> name_code;
	EditText search;
	TextView search_tv, phone_Header;
	TextView cross_search;
	int sear = 0;
	InputMethodManager imm;
	String CountryName[] = { "Abkhazia", "Afghanistan", "Albania", "Algeria",
			"American Samoa", "Andorra", "Angola", "Antigua and Barbuda",
			"Argentina", "Armenia", "Aruba", "Ascension", "Australia",
			"Australian External Territories", "Austria", "Azerbaijan",
			"Bahamas", "Bahrain", "Bangladesh", "Barbados", "Barbuda",
			"Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan",
			"Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil",
			"British Indian Ocean Territory", "British Virgin Islands",
			"Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia",
			"Cameroon", "Canada", "Cape Verde", "Cayman Islands",
			"Central African Republic", "Chad", "Chile", "China",
			"Christmas Island", "Colombia", "Comoros", "Congo",
			"Congo, Dem. Rep. of (Zaire)", "Cook Islands", "Costa Rica",
			"Ivory Coast", "Croatia", "Cuba", "Curacao", "Cyprus",
			"Czech Republic", "Denmark", "Diego Garcia", "Djibouti",
			"Dominica", "Dominican Republic", "Dominican Republic",
			"Dominican Republic", "East Timor", "Easter Island", "Ecuador",
			"Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
			"Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "France",
			"French Antilles", "French Guiana", "French Polynesia", "Gabon",
			"Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece",
			"Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala",
			"Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras",
			"Hong Kong SAR China", "Hungary", "Iceland", "India", "Indonesia",
			"Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan",
			"Jordan", "Kazakhstan", "Kenya", "Kiribati", "North Korea",
			"South Korea", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon",
			"Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania",
			"Luxembourg", "Macau SAR China", "Macedonia", "Madagascar",
			"Malawi", "Malaysia", "Maldives", "Mali", "Malta",
			"Marshall Islands", "Martinique", "Mauritania", "Mauritius",
			"Mayotte", "Mexico", "Micronesia", "Midway Island", "Micronesia",
			"Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat",
			"Morocco", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands",
			"Netherlands Antilles", "Nevis", "New Caledonia", "New Zealand",
			"Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island",
			"Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau",
			"Palestinian Territory", "Panama", "Papua New Guinea", "Paraguay",
			"Peru", "Philippines", "Poland", "Portugal", "Puerto Rico",
			"Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Samoa",
			"San Marino", "Saudi Arabia", "Senegal", "Serbia", "Seychelles",
			"Sierra Leone", "Singapore", "Slovakia", "Slovenia",
			"Solomon Islands", "South Africa", "Spain", "Sri Lanka", "Sudan",
			"Suriname", "Swaziland", "Sweden", "Switzerland", "Syria",
			"Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor Leste",
			"Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia",
			"Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu",
			"Uganda", "Ukraine", "United Arab Emirates", "United Kingdom",
			"United States", "Uruguay", "U.S. Virgin Islands", "Uzbekistan",
			"Vanuatu", "Venezuela", "Vietnam", "Wake Island",
			"Wallis and Futuna", "Yemen", "Zambia", "Zanzibar", "Zimbabwe" };
	ArrayList<String> temp_countries;
	String CountryCode[] = { "7840", "93", "355", "213", "1684", "376", "244",
			"1268", "54", "374", "297", "247", "61", "672", "43", "994",
			"1242", "973", "880", "1246", "1 268", "375", "32", "501", "229",
			"1 441", "975", "591", "387", "267", "55", "246", "1 284", "673",
			"359", "226", "257", "855", "237", "1", "238", "345", "236", "235",
			"56", "86", "61", "57", "269", "242", "243", "682", "506", "225",
			"385", "53", "599", "537", "420", "45", "246", "253", "1 767",
			"1 809", "1 829", "1 849", "670", "593", "20", "503", "240", "291",
			"372", "251", "500", "298", "679", "358", "33", "596", "594",
			"689", "241", "220", "995", "49", "233", "350", "30", "299",
			"1 473", "590", "1 671", "502", "224", "245", "595", "509", "504",
			"852", "36", "354", "91", "62", "98", "964", "353", "972", "39",
			"1 876", "81", "962", "7 7", "254", "686", "850", "82", "965",
			"996", "856", "371", "961", "266", "231", "218", "423", "370",
			"352", "853", "389", "261", "265", "60", "960", "223", "356",
			"692", "596", "222", "230", "262", "52", "691", "1 808", "691",
			"373", "377", "976", "382", "1664", "212", "95", "264", "674",
			"977", "31", "599", "1 869", "687", "64", "505", "227", "234",
			"683", "672", "1 670", "47", "968", "92", "680", "970", "507",
			"675", "595", "51", "63", "48", "351", "1 787", "974", "262", "40",
			"7", "250", "685", "378", "966", "221", "381", "248", "232", "65",
			"421", "386", "677", "27", "34", "94", "249", "597", "268", "46",
			"41", "963", "886", "992", "255", "66", "670", "228", "690", "676",
			"1 868", "216", "90", "993", "1 649", "688", "256", "380", "971",
			"44", "1", "598", "1 340", "998", "678", "58", "84", "1 808",
			"681", "967", "260", "255", "263" };
	List<String> countries;
	ListView lv;
	RelativeLayout search_view;
	LinearLayout phone_bac_layout;

	class SideIndexGestureListener extends
			GestureDetector.SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
			sideIndexX = sideIndexX - distanceX;
			sideIndexY = sideIndexY - distanceY;
			if (sideIndexX >= 0 && sideIndexY >= 0) {
				displayListItem();
			}
			return super.onScroll(e1, e2, distanceX, distanceY);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_country_codes);

		Display dis = getWindowManager().getDefaultDisplay();
		w = dis.getWidth();
		h = dis.getHeight();


		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		lv = (ListView) findViewById(R.id.listie);

		mGestureDetector = new GestureDetector(this,
				new SideIndexGestureListener());

		name_code = new HashMap<String, String>();
		for (int i = 0; i < CountryName.length; i++) {
			CountryCode[i] = "+" + CountryCode[i];
			name_code.put(CountryName[i], CountryCode[i]);
		}

		phone_bac_layout = (LinearLayout) findViewById(R.id.phone_bac_layout);
		phone_bac_layout.setPadding(w/32, w/32, w/32, w/32);
		phone_bac_layout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		phone_Header = (TextView) findViewById(R.id.phone_pro_header);
		phone_Header.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (w * 0.048));
		countries = populateCountries(CountryName);
		create_view(countries);

		search_view = (RelativeLayout) findViewById(R.id.search_view);
		LinearLayout.LayoutParams pam_ll = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, (int) (h * 0.08));
		search_view.setLayoutParams(pam_ll);

		search = (EditText) findViewById(R.id.searchView1);
		search.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (w * 0.04));
		search_tv = (TextView) findViewById(R.id.textView1);
		search_tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (w * 0.04));
		cross_search = (TextView) findViewById(R.id.imageView1);
		cross_search.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (w * 0.038));

		search_view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				search.setVisibility(View.VISIBLE);
				cross_search.setVisibility(View.VISIBLE);
				search.requestFocus();
				search.setText("");
				search_tv.setVisibility(View.INVISIBLE);
				imm.showSoftInput(search, 0);
			}
		});

		cross_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				search.setVisibility(View.INVISIBLE);
				cross_search.setVisibility(View.INVISIBLE);
				search_tv.setVisibility(View.VISIBLE);
				imm.hideSoftInputFromWindow(search.getApplicationWindowToken(),
						0);

				countries = populateCountries(CountryName);
				create_view(countries);
			}
		});

		search.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {

				// TODO Auto-generated method stub
				String searString = search.getText().toString().toLowerCase();
				if (searString.trim().length() == 0) {
					countries = populateCountries(CountryName);
					create_view(countries);
				} else {
					temp_countries = new ArrayList<String>();
					for (int i = 0; i < CountryName.length; i++) {
						if (CountryName[i].toLowerCase().contains(searString)) {
							temp_countries.add(CountryName[i]);
						}
					}
					String[] arr = new String[temp_countries.size()];
					for (int j = 0; j < temp_countries.size(); j++)
						arr[j] = temp_countries.get(j);

					countries = populateCountries(arr);
					create_view(countries);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
										  int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

		});

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
				// TODO Auto-generated method stub
				if (adapter.getItemViewType(position) == 0) {
					Log.e("clicked", "item");
					Phone_Adp.Item it = (Phone_Adp.Item) adapter
							.getItem(position);
					Intent i = new Intent();
					i.putExtra("Country_Name", it.text);
					i.putExtra("Country_code", name_code.get(it.text));
					setResult(RESULT_OK, i);
					finish();
				} else {
					Log.e("clicked", "section");
				}
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mGestureDetector.onTouchEvent(event)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	public void updateList() {
		LinearLayout sideIndex = (LinearLayout) findViewById(R.id.sideIndex);
		sideIndex.removeAllViews();
		indexListSize = alphabet.size();
		if (indexListSize < 1) {
			return;
		}

		int indexMaxSize = (int) Math.floor(sideIndex.getHeight() / 20);
		int tmpIndexListSize = indexListSize;
		while (tmpIndexListSize > indexMaxSize) {
			tmpIndexListSize = tmpIndexListSize / 2;
		}
		double delta;
		if (tmpIndexListSize > 0) {
			delta = indexListSize / tmpIndexListSize;
		} else {
			delta = 1;
		}

		TextView tmpTV;
		for (double i = 1; i <= indexListSize; i = i + delta) {
			Object[] tmpIndexItem = alphabet.get((int) i - 1);
			String tmpLetter = tmpIndexItem[0].toString();

			tmpTV = new TextView(this);
			tmpTV.setText(tmpLetter);
			tmpTV.setTextColor(Color.parseColor("#000000"));
			tmpTV.setText(tmpLetter);
			tmpTV.setGravity(Gravity.CENTER_VERTICAL);
			// tmpTV.setTextSize(15);
			tmpTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (w * 0.035));
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT, 1);
			tmpTV.setLayoutParams(params);

			sideIndex.addView(tmpTV);
		}

		sideIndexHeight = sideIndex.getHeight();

		sideIndex.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// now you know coordinates of touch
				sideIndexX = event.getX();
				sideIndexY = event.getY();

				// and can display a proper item it country list
				displayListItem();

				return false;
			}
		});
	}

	public void displayListItem() {
		LinearLayout sideIndex = (LinearLayout) findViewById(R.id.sideIndex);
		sideIndexHeight = sideIndex.getHeight();
		// compute number of pixels for every side index item
		double pixelPerIndexItem = (double) sideIndexHeight / indexListSize;

		// compute the item index for given event position belongs to
		int itemPosition = (int) (sideIndexY / pixelPerIndexItem);

		// get the item (we can do it since we know item index)
		if (itemPosition < alphabet.size()) {
			Object[] indexItem = alphabet.get(itemPosition);
			int subitemPosition = sections.get(indexItem[0]);

			// ListView lvSearch = (ListView) findViewById(android.R.id.list);
			lv.setSelection(subitemPosition);
		}
	}

	public void create_view(List<String> countries) {
		Log.e("countries", "are " + countries);
		Collections.sort(countries);
		alphabet = new ArrayList<Object[]>();
		lv.invalidate();
		List<Phone_Adp.Row> rows = new ArrayList<Phone_Adp.Row>();
		int start = 0;
		int end = 0;
		String previousLetter = null;
		Object[] tmpIndexItem = null;
		Pattern numberPattern = Pattern.compile("[0-9]");

		for (String country : countries) {
			String firstLetter = country.substring(0, 1);

			// Group numbers together in the scroller
			if (numberPattern.matcher(firstLetter).matches()) {
				firstLetter = "#";
			}

			// If we've changed to a new letter, add the previous letter to the
			// alphabet scroller
			if (previousLetter != null && !firstLetter.equals(previousLetter)) {
				end = rows.size() - 1;
				tmpIndexItem = new Object[3];
				tmpIndexItem[0] = previousLetter.toUpperCase(Locale.UK);
				tmpIndexItem[1] = start;
				tmpIndexItem[2] = end;
				alphabet.add(tmpIndexItem);

				start = end + 1;
			}

			// Check if we need to add a header row
			if (!firstLetter.equals(previousLetter)) {
				rows.add(new Phone_Adp.Section(firstLetter));
				sections.put(firstLetter, start);
			}

			// Add the country to the list
			rows.add(new Phone_Adp.Item(country));
			previousLetter = firstLetter;
		}

		if (previousLetter != null) {
			// Save the last letter
			tmpIndexItem = new Object[3];
			tmpIndexItem[0] = previousLetter.toUpperCase(Locale.UK);
			tmpIndexItem[1] = start;
			tmpIndexItem[2] = rows.size() - 1;
			alphabet.add(tmpIndexItem);
		}
		adapter = new Phone_Adp();
		adapter.setRows(rows);

		lv.setAdapter(adapter);

		updateList();
	}

	private List<String> populateCountries(String[] country_name) {
		List<String> countries = new ArrayList<String>();
		for (int i = 0; i < country_name.length; i++) {
			countries.add(country_name[i]);
		}
		return countries;
	}

}