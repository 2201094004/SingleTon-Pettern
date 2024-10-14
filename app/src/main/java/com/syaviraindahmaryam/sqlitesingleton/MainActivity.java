package com.syaviraindahmaryam.sqlitesingleton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private ListView lvMahasiswa;
    private FloatingActionButton fabTambah;
    private List<ModelMahasiswa> mahasiswaList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private List<String> mahasiswaNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);
        lvMahasiswa = findViewById(R.id.lv_mahasiswa);
        fabTambah = findViewById(R.id.fab_tambah);

        loadMahasiswa();

        // Navigate to TambahActivity for adding new student
        fabTambah.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TambahActivity.class);
            startActivity(intent);
        });

        // Handle item clicks in ListView
        lvMahasiswa.setOnItemClickListener((parent, view, position, id) -> {
            ModelMahasiswa selectedMahasiswa = mahasiswaList.get(position);
            Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
            intent.putExtra("studentId", selectedMahasiswa.getId()); // Pass the ID to UpdateActivity
            startActivity(intent);
        });
    }

    private void loadMahasiswa() {
        mahasiswaList = db.getAllMahasiswa();
        mahasiswaNames.clear();
        for (ModelMahasiswa mahasiswa : mahasiswaList) {
            mahasiswaNames.add(mahasiswa.getName());
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mahasiswaNames);
        lvMahasiswa.setAdapter(arrayAdapter);
    }
}
