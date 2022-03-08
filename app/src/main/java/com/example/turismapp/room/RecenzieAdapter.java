package com.example.turismapp.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.turismapp.R;

import java.util.List;

public class RecenzieAdapter extends ArrayAdapter<Recenzie> {
    private Context context;
    private int resource;
    private List<Recenzie> recenzii;
    private LayoutInflater inflater;

    public RecenzieAdapter(@NonNull Context context, int resource, @NonNull List<Recenzie> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.recenzii = objects;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Recenzie recenzie = recenzii.get(position);
        if (recenzie != null) {
            addRecenzie(view, recenzie.getRecenzie());
            addNota(view, recenzie.getNota());
        }
        return view;
    }

    private void addRecenzie(View view, String category) {
        TextView textView = view.findViewById(R.id.tvRecenzie);
        addTextViewContent(textView, category);
    }

    private void addNota(View view, Double nota) {
        TextView textView = view.findViewById(R.id.tvNota);
        String value = null;
        if (nota != null) {
            value = context.getString(R.string.lv_nota, nota.toString());
        }
        addTextViewContent(textView, value);
    }

    private void addTextViewContent(TextView textView, String value) {
        if (value != null && !value.isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.lv_default_value);
        }
    }
}