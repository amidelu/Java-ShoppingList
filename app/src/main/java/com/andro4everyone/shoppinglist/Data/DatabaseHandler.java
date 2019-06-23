package com.andro4everyone.shoppinglist.Data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.andro4everyone.shoppinglist.Model.Shopping;
import com.andro4everyone.shoppinglist.Utils.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    Context ctx;

    public DatabaseHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creating Shopping table and execute query
        String CREATE_SHOPPING_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                + Constants.KEY_SHOPPING_ITEM + " TEXT,"
                + Constants.KEY_QTY_NUMBER + " TEXT,"
                + Constants.KEY_DATE_NAME + " LONG);";

        sqLiteDatabase.execSQL(CREATE_SHOPPING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /*
    CRUD OPERATIONS: Create, Read, Update, Delete Methods
     */
    //Add Shopping
    public void addShopping(Shopping shopping) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_SHOPPING_ITEM, shopping.getName());
        values.put(Constants.KEY_QTY_NUMBER, shopping.getQuantity());
        values.put(Constants.KEY_DATE_NAME, java.lang.System.currentTimeMillis());//get system time

        //Insert the rows
        db.insert(Constants.TABLE_NAME, null, values);

        Log.d("Saved!!", "Saved to DB");
    }

    //Get a Shopping
    public Shopping getShopping(int id) {
            SQLiteDatabase db = this.getWritableDatabase();

            @SuppressLint("Recycle") Cursor cursor = db.query(Constants.TABLE_NAME, new String[] {Constants.KEY_ID,
                            Constants.KEY_SHOPPING_ITEM, Constants.KEY_QTY_NUMBER, Constants.KEY_DATE_NAME},
                    Constants.KEY_ID + "=?",
                    new String[] {String.valueOf(id)}, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

            Shopping shopping = new Shopping();
            shopping.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            shopping.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_SHOPPING_ITEM)));
            shopping.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));

            //convert timestamp to something readable
            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
            String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME)))
                    .getTime());

            shopping.setDateItemAdded(formatedDate);

        return shopping;
    }

    //Get all Shopping
    public List <Shopping> getAllShopping() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Shopping> shoppingList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{
                Constants.KEY_ID, Constants.KEY_SHOPPING_ITEM, Constants.KEY_QTY_NUMBER,
                Constants.KEY_DATE_NAME}, null, null, null, null, Constants.KEY_DATE_NAME + " DESC");

        if (cursor.moveToFirst()) {
            do {
                Shopping shopping = new Shopping();
                shopping.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                shopping.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_SHOPPING_ITEM)));
                shopping.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));

                //convert timestamp to something readable
                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME)))
                        .getTime());

                shopping.setDateItemAdded(formatedDate);

                //Add to ShoppingList
                shoppingList.add(shopping);

            }while (cursor.moveToNext());
        }

        return shoppingList;
    }

    //Update Shopping
    public int updateShopping(Shopping shopping) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_SHOPPING_ITEM, shopping.getName());
        values.put(Constants.KEY_QTY_NUMBER, shopping.getQuantity());
        values.put(Constants.KEY_DATE_NAME, java.lang.System.currentTimeMillis()); //get system time

        //update row
        return db.update(Constants.TABLE_NAME, values, Constants.KEY_ID + "=?", new String[] {String.valueOf(shopping.getId())});
    }

    //Delete Shopping
    public void deleteShopping(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID + " = ?",
                new String[] {String.valueOf(id)});

        db.close();
    }

    //Get Count
    public int getShoppingCount() {
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
}
