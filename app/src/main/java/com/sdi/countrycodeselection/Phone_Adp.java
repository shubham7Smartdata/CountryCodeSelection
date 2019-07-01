package com.sdi.countrycodeselection;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Phone_Adp extends BaseAdapter {

	public static abstract class Row {
	}

	public static final class Section extends Row {
		public final String text;

		public Section(String text) {
			this.text = text;
		}
	}

	public static final class Item extends Row {
		public final String text;

		public Item(String text) {
			this.text = text;
		}
	}

	private List<Row> rows;

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	@Override
	public int getCount() {
		return rows.size();
	}

	@Override
	public Row getItem(int position) {
		return rows.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		if (getItem(position) instanceof Section) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (getItemViewType(position) == 0) { // Item
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) parent.getContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = (LinearLayout) inflater.inflate(R.layout.country_item,
						parent, false);
			}

			Item item = (Item) getItem(position);
			TextView textView = (TextView) view.findViewById(R.id.textView1);
			textView.setText(item.text);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					(int) (CountryCodeActivity.w * 0.05));
			textView.setTextColor(Color.GRAY);
			textView.setPadding(CountryCodeActivity.w / 32,
					CountryCodeActivity.w / 32, 0, CountryCodeActivity.w / 32);

			TextView textView2 = (TextView) view.findViewById(R.id.textView2);
			textView2.setText(CountryCodeActivity.name_code.get(item.text));
			textView2.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					(int) (CountryCodeActivity.w * 0.05));
			textView2.setTextColor(Color.GRAY);
			textView2.setPadding(0, CountryCodeActivity.w / 32,
					CountryCodeActivity.w / 32, CountryCodeActivity.w / 32);

		} else { // Section
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) parent.getContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = (LinearLayout) inflater.inflate(R.layout.country_section,
						parent, false);
			}

			Section section = (Section) getItem(position);
			TextView textView = (TextView) view.findViewById(R.id.textView1);
			textView.setText(section.text);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					(int) (CountryCodeActivity.w * 0.05));
			textView.setTextColor(Color.WHITE);
			textView.setPadding(CountryCodeActivity.w / 32, 0,
					CountryCodeActivity.w / 32, 0);
		}
		return view;
	}

}
