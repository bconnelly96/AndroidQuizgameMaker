package edu.temple.quizgame.game_ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.temple.quizgame.R;

public class QListAdapter extends BaseAdapter {

    Context context;
    String [] questionList;

    public QListAdapter(Context context, String [] questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @Override
    public int getCount() {
        return questionList.length;
    }

    @Override
    public Object getItem(int position) {
        return questionList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.adapter_layout, parent, false);
        TextView textView = convertView.findViewById(R.id.adapter_tv);
        textView.setText(questionList[position]);
        return convertView;
    }
}
