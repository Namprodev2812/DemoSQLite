package com.asterisk.nam.DemoSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBManagerGiohang extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GioHangDB";
    private static final String TABLE_NAME = "giohang";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String INDEX = "index1";
    private static Integer VERSION = 1;
    private Context mContext;
    private String[] allColumns = {ID, NAME, PRICE, INDEX};

    public DBManagerGiohang(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " integer primary key, " +
                NAME + " TEXT, " +
                PRICE + " double, " +
                INDEX + " integer)";
        sqLiteDatabase.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addProduct(Giohang giohang) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        Values.put(NAME, giohang.getmName());
        Values.put(PRICE, giohang.getMprice());
        Values.put(INDEX, giohang.getmIndex());

        db.insert(TABLE_NAME, null, Values);
        db.close();
    }

    public List<Giohang> getAllProduct() {
        List<Giohang> mListGiohang = new ArrayList<>();

        String selectQuary = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase(); // lệnh để đọc dữ liệu quả SQLite

        // data trả về nằm trong CurSor
        // nó cung cấp các phương thức để lấy dữ liệu trả về
        //Cursor cursor = db.query(TABLE_NAME,allColumns,null,null,null,null,null);
        Cursor cursor = db.rawQuery(selectQuary, null);

        if (cursor.moveToFirst()) { // check xem du lieu trả về >=1
            do {
                Giohang giohang = new Giohang();
                giohang.setmId(cursor.getInt(0));
                giohang.setmName(cursor.getString(1));
                giohang.setMprice(cursor.getDouble(2));
                giohang.setmIndex(cursor.getInt(3));
                mListGiohang.add(giohang);  // add vao student
            }
            while (cursor.moveToNext()); //check xem du lieu tiếp theo có ko ?
        }
        db.close();
        return mListGiohang;
    }

    public int Update(Giohang giohang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, giohang.getmName());
        values.put(PRICE, giohang.getMprice());
        values.put(INDEX, giohang.getmIndex());

        return db.update(TABLE_NAME, values, ID + "=?", new String[]{String.valueOf(giohang.getmId())});
        //return db.update(TABLE_NAME,values,ID +"= 1",null);
    }

    public int delete(Giohang giohang) {
        SQLiteDatabase bd = this.getWritableDatabase();
        return bd.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(giohang.getmId())});
    }
}
