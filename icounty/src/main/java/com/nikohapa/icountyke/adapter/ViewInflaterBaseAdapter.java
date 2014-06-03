package com.nikohapa.icountyke.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by james on 3/06/14.
 */
public abstract class ViewInflaterBaseAdapter<T> extends BaseAdapter {

    private ArrayList<T> arrayList;
    private ViewInflater inflater;

    public ViewInflaterBaseAdapter(ViewInflater inflater){
        this.inflater = inflater;
        arrayList = new ArrayList<T>();
    }

    public ViewInflaterBaseAdapter(ViewInflater inflater, ArrayList<T> data){
        this.inflater = inflater;
        arrayList = data;
    }

    public void setInflater(ViewInflater inflater){
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public T getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return inflater != null ? inflater.inflate(this, position, convertView, parent) : null;
    }

    public interface ViewInflater<T>{
        View inflate (ViewInflaterBaseAdapter<T> adapter, int pos, View ConvertView, ViewGroup parent);
    }
}

