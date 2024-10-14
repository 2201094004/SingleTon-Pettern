package com.syaviraindahmaryam.sqlitesingleton;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TambahActivity extends AppCompatActivity {

    private EditText etFullName, etStudentId;
    private Spinner spinnerProgramStudi;
    private Button btnSave;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        db = new DatabaseHandler(this);
        etFullName = findViewById(R.id.et_nama);
        etStudentId = findViewById(R.id.et_nim);
        spinnerProgramStudi = findViewById(R.id.spinner_program);
        btnSave = findViewById(R.id.btn_save);

        // Data untuk spinner
        String[] programStudiList = {"Informatika", "Sistem Informasi", "Teknik Komputer", "Manajemen", "Akuntansi"};

        // Mengisi spinner dengan data program studi
        ArrayAdapter<String> adapterProgramStudi = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, programStudiList);
        adapterProgramStudi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProgramStudi.setAdapter(adapterProgramStudi);

        btnSave.setOnClickListener(v -> {
            String name = etFullName.getText().toString().trim();
            String nim = etStudentId.getText().toString().trim();
            String programStudy = spinnerProgramStudi.getSelectedItem().toString(); // Ambil pilihan dari spinner

            if (!name.isEmpty() && !nim.isEmpty()) {
                ModelMahasiswa mahasiswa = new ModelMahasiswa();
                mahasiswa.setName(name);
                mahasiswa.setNim(nim);
                mahasiswa.setProgramStudi(programStudy);

                db.addMahasiswa(mahasiswa);
                Toast.makeText(TambahActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
                finish(); // Close activity and return to MainActivity
            } else {
                Toast.makeText(TambahActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
