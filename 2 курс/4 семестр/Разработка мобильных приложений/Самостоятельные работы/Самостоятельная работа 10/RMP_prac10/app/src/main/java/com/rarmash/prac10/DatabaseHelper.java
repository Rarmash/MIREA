package com.rarmash.prac10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "games";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_YEARS = "year";
    public DatabaseHelper(Context context) {
        super(context, "games.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_YEARS + " TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, game.getTitle());
        cv.put(COLUMN_YEARS, game.getYearOfRelease());
        long result = db.insert(TABLE_NAME, null, cv);
        db.close();
        return result != -1;
    }
    public boolean deleteGame(String year) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_YEARS + " = ?",
                new String[]{year});
        db.close();
        return result > 0;
    }
    public Game findGame(String year) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new
                        String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_YEARS},
                COLUMN_YEARS + " = ?", new String[]{year}, null,
                null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Game game = new Game(cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2));
            cursor.close();
            db.close();
            return game;
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return null;
    }
    public List<Game> getAllGames() {
        List<Game> gameList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Game game = new Game(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2));
                gameList.add(game);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gameList;
    }
    public boolean updateGame(String oldYear, String newTitle, String newYear) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, newTitle);
        cv.put(COLUMN_YEARS, newYear);
        // Обновляем запись, где номер телефона равен oldYear
        int result = db.update(TABLE_NAME, cv, COLUMN_YEARS + " = ?", new String[]{oldYear});
        db.close();
        return result > 0;
    }
}