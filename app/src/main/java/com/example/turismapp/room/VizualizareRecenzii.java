package com.example.turismapp.room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.turismapp.R;

import java.util.ArrayList;
import java.util.List;

public class VizualizareRecenzii extends AppCompatActivity {
    private static final int ADD_RECENZIE_REQUEST_CODE = 201;
    private static final int UPDATE_RECENZIE_REQUEST_CODE = 202;

    private ListView lvRecenzii;
    private Button btnAdaugare;

    private List<Recenzie> recenzii = new ArrayList<>();
    private RecenzieService recenzieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare_recenzii);

        initComponents();
        recenzieService = new RecenzieService(getApplicationContext());
        recenzieService.getAll(getAllFromDbCallback());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Recenzie recenzie = (Recenzie) data.getSerializableExtra(RecenziiActivity.RECENZIE_KEY);
            if (requestCode == ADD_RECENZIE_REQUEST_CODE) {
                recenzieService.insert(insertIntoDbCallback(), recenzie);
            } else if (requestCode == UPDATE_RECENZIE_REQUEST_CODE) {
                recenzieService.update(updateToDbCallback(), recenzie);
            }
        }
    }

    private Callback<List<Recenzie>> getAllFromDbCallback() {
        return new Callback<List<Recenzie>>() {
            @Override
            public void runResultOnUiThread(List<Recenzie> result) {
                if (result != null) {
                    recenzii.clear();
                    recenzii.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Recenzie> insertIntoDbCallback() {
        return new Callback<Recenzie>() {
            @Override
            public void runResultOnUiThread(Recenzie result) {
                if (result != null) {
                    recenzii.add(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Recenzie> updateToDbCallback() {
        return new Callback<Recenzie>() {
            @Override
            public void runResultOnUiThread(Recenzie result) {
                if (result != null) {
                    for (Recenzie recenzie : recenzii) {
                        if (recenzie.getId() == result.getId()) {
                            recenzie.setRecenzie(result.getRecenzie());
                            recenzie.setNota(result.getNota());
                            break;
                        }
                    }
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Integer> deleteToDbCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUiThread(Integer result) {
                if (result != -1) {
                    recenzii.remove(position);
                    notifyAdapter();
                }
            }
        };
    }

    private void initComponents() {
        lvRecenzii = findViewById(R.id.lvRecenzii);
        btnAdaugare = findViewById(R.id.btnRecenzii);

        addAdapter();
        btnAdaugare.setOnClickListener(addRecenzieEventListener());
        lvRecenzii.setOnItemClickListener(updateEventListener());
        lvRecenzii.setOnItemLongClickListener(deleteEventListener());
    }

    private AdapterView.OnItemClickListener updateEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), RecenziiActivity.class);
                intent.putExtra(RecenziiActivity.RECENZIE_KEY, recenzii.get(position));
                startActivityForResult(intent, UPDATE_RECENZIE_REQUEST_CODE);
            }
        };
    }

    private AdapterView.OnItemLongClickListener deleteEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                recenzieService.delete(deleteToDbCallback(position), recenzii.get(position));
                return true;
            }
        };
    }

    private View.OnClickListener addRecenzieEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecenziiActivity.class);
                startActivityForResult(intent, ADD_RECENZIE_REQUEST_CODE);
            }
        };
    }

    private void addAdapter() {
        RecenzieAdapter adapter = new RecenzieAdapter(getApplicationContext(), R.layout.custom_adapter_recenzii,
                recenzii, getLayoutInflater());
        lvRecenzii.setAdapter(adapter);
    }

    private void notifyAdapter() {
        RecenzieAdapter adapter = (RecenzieAdapter) lvRecenzii.getAdapter();
        adapter.notifyDataSetChanged();
    }
}