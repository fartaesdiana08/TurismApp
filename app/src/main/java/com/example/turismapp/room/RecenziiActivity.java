package com.example.turismapp.room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.turismapp.R;

public class RecenziiActivity extends AppCompatActivity {
    public static final String RECENZIE_KEY = "recenzieKey";
    private EditText etRecenzie;
    private EditText etNota;
    private Button btnAdaugare;

    private Intent intent;
    private Recenzie recenzie = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recenzii);
        initComponents();
        intent = getIntent();
        if (intent.hasExtra(RECENZIE_KEY)) {
            recenzie = (Recenzie) intent.getSerializableExtra(RECENZIE_KEY);
            buildViewsFromRecenzie(recenzie);
        }
    }

    private void buildViewsFromRecenzie(Recenzie recenzie) {
        if (recenzie == null) {
            return;
        }
        if(recenzie.getRecenzie()!=null) {
            etRecenzie.setText(recenzie.getRecenzie());
        }
        if (recenzie.getNota() != null) {
            etNota.setText(String.valueOf(recenzie.getNota()));
        }
    }

    private void initComponents() {
        etRecenzie = findViewById(R.id.etRecenzie);
        etNota = findViewById(R.id.etNota);
        btnAdaugare = findViewById(R.id.btnAdauga);
        btnAdaugare.setOnClickListener(saveRecenzieEventListener());
    }

    private View.OnClickListener saveRecenzieEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    createFromViews();
                    intent.putExtra(RECENZIE_KEY, recenzie);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private void createFromViews() {
        String feedback = etRecenzie.getText().toString();
        Double notap = Double.parseDouble(etNota.getText().toString());
        if (recenzie == null) {
            recenzie = new Recenzie(feedback,notap);
        } else {
            recenzie.setRecenzie(feedback);
            recenzie.setNota(notap);

        }
    }


    private boolean validate() {
        if (etNota.getText() == null || etNota.getText().toString().isEmpty()
                || Double.parseDouble(etNota.getText().toString()) < 0) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_nota_error_message,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }

}