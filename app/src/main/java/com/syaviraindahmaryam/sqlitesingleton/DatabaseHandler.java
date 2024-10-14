package com.syaviraindahmaryam.sqlitesingleton;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mahasiswa.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MAHASISWA = "mahasiswa";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NIM = "nim";
    private static final String KEY_PROGRAM_STUDI = "program_studi";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MAHASISWA_TABLE = "CREATE TABLE " + TABLE_MAHASISWA + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_NIM + " TEXT,"
                + KEY_PROGRAM_STUDI + " TEXT" + ")";
        db.execSQL(CREATE_MAHASISWA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);
        onCreate(db);
    }

    public void addMahasiswa(ModelMahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, mahasiswa.getName());
        values.put(KEY_NIM, mahasiswa.getNim());
        values.put(KEY_PROGRAM_STUDI, mahasiswa.getProgramStudi());

        db.insert(TABLE_MAHASISWA, null, values);
        db.close();
    }

    public ModelMahasiswa getStudentById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MAHASISWA, new String[]{KEY_ID, KEY_NAME, KEY_NIM, KEY_PROGRAM_STUDI}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            ModelMahasiswa mahasiswa = new ModelMahasiswa();
            mahasiswa.setId(cursor.getInt(0));
            mahasiswa.setName(cursor.getString(1));
            mahasiswa.setNim(cursor.getString(2));
            mahasiswa.setProgramStudi(cursor.getString(3));
            cursor.close();
            return mahasiswa;
        }
        return null;
    }

    public void updateMahasiswa(ModelMahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, mahasiswa.getName());
        values.put(KEY_NIM, mahasiswa.getNim());
        values.put(KEY_PROGRAM_STUDI, mahasiswa.getProgramStudi());

        db.update(TABLE_MAHASISWA, values, KEY_ID + "=?", new String[]{String.valueOf(mahasiswa.getId())});
        db.close();
    }

    public void deleteMahasiswa(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MAHASISWA, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<ModelMahasiswa> getAllMahasiswa() {
        List<ModelMahasiswa> mahasiswaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MAHASISWA, null);
        if (cursor.moveToFirst()) {
            do {
                ModelMahasiswa mahasiswa = new ModelMahasiswa();
                mahasiswa.setId(cursor.getInt(0));
                mahasiswa.setName(cursor.getString(1));
                mahasiswa.setNim(cursor.getString(2));
                mahasiswa.setProgramStudi(cursor.getString(3));
                mahasiswaList.add(mahasiswa);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return mahasiswaList;
    }
}
