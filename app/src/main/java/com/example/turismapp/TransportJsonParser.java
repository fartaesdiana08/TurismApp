package com.example.turismapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TransportJsonParser {
    public static final String FIRMA = "firma";
    public static final String NUMARTELEFON="nrTelefon";
    public static final String NRTAXIURI ="nrTaxi";

    public static List<Transport> fromJson(String json) {
        try {
            JSONArray array = new JSONArray(json);
            return readTransporturi(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static List<Transport> readTransporturi(JSONArray array) throws JSONException {
        List<Transport> transporturi = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Transport transport = readTransporturi(array.getJSONObject(i));
            transporturi.add(transport);
        }
        return transporturi;
    }


    private static Transport readTransporturi(JSONObject object) throws JSONException{
        String firma=object.getString(FIRMA);
        String nrTelefon=object.getString(NUMARTELEFON);
        int nrTaxi=object.getInt(NRTAXIURI);
        return new Transport(firma,nrTelefon,nrTaxi);
    }
}
