
package com.example.garapon4jsample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import me.gizio.garapon4j.json.Program;

import java.util.List;

final class ProgramArrayAdapter extends ArrayAdapter<Program> {

    ProgramArrayAdapter(Context context, List<Program> programList) {
        super(context, android.R.layout.simple_list_item_1, programList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView text1 = (TextView) view.findViewById(android.R.id.text1);

        text1.setText(getItem(position).getTitle());
        return view;
    }
}
