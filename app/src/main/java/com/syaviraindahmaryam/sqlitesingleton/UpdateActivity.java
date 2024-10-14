package com.syaviraindahmaryam.sqlitesingleton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    private EditText etFullName, etStudentId, etProgramStudy;
    private Button btnUpdate, btnDelete;
    private DatabaseHandler db;
    private int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        db = new DatabaseHandler(this);
        etFullName = findViewById(R.id.et_nama);
        etStudentId = findViewById(R.id.et_nim);
        etProgramStudy = findViewById(R.id.et_program_study);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        // Retrieve student ID passed from MainActivity
        studentId = getIntent().getIntExtra("studentId", -1);
        loadStudentData(studentId);

        btnUpdate.setOnClickListener(v -> {
            String name = etFullName.getText().toString().trim();
            String nim = etStudentId.getText().toString().trim();
            String programStudy = etProgramStudy.getText().toString().trim();

            if (!name.isEmpty() && !nim.isEmpty() && !programStudy.isEmpty()) {
                ModelMahasiswa mahasiswa = new ModelMahasiswa();
                mahasiswa.setId(studentId);
                mahasiswa.setName(name);
                mahasiswa.setNim(nim);
                mahasiswa.setProgramStudi(programStudy);

                db.updateMahasiswa(mahasiswa);
                Toast.makeText(UpdateActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                finish(); // Close activity and return to MainActivity
            } else {
                Toast.makeText(UpdateActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(v -> {
            db.deleteMahasiswa(studentId);
            Toast.makeText(UpdateActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
            finish(); // Close activity and return to MainActivity
        });
    }

    private void loadStudentData(int studentId) {
        ModelMahasiswa student = db.getStudentById(studentId);
        if (student != null) {
            etFullName.setText(student.getName());
            etStudentId.setText(student.getNim());
            etProgramStudy.setText(student.getProgramStudi());
        }
    }
}
