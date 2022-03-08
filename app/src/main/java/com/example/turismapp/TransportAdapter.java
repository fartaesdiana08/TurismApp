package com.example.turismapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TransportAdapter extends ArrayAdapter<Transport> {
    private Context ctx;
    private List<Transport> transport;
    private LayoutInflater inflater;
    private int resource;

    public TransportAdapter(@NonNull Context context, int resource,
                            @NonNull List<Transport> transport, LayoutInflater inflater) {
        super(context, resource, transport);
        this.ctx = context;
        this.transport = transport;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Transport t = transport.get(position);
        if (t != null) {
            addFirma(view, t.getFirma());
            addNrTelefon(view, t.getNrTelefon());
            addNrTaxi(view, t.getNrTaxi());
        }
        return view;
    }
    private void addFirma(View view, String firma) {
        TextView textView = view.findViewById(R.id.firmaTransport);
        populateTextViewContent(textView,  firma  );
    }

    private void addNrTelefon(View view, String nrTelefon) {
        TextView textView = view.findViewById(R.id.nrTelefon);
        populateTextViewContent(textView,  nrTelefon  );
    }

    private void addNrTaxi(View view, int nrTaxi) {
        TextView textView = view.findViewById(R.id.tvNrTaxi);
        populateTextViewContent(textView, String.valueOf(nrTaxi));
    }


    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText("");
        }
    }

}
