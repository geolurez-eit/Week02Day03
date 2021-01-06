package com.terremoto.week02day03.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.terremoto.week02day03.model.data.Pizza;

public class PizzaDatabaseHelper extends SQLiteOpenHelper {
    
    public static final String DATABASE_NAME = "pizza_orders.db";
    public static final String PIZZA_TABLE_NAME = "PIZZA_ORDERS";
    public static final String COLUMN_ORDER_ID = "order_id";
    public static final String COLUMN_PIZZA_FLAVOR = "pizza_flavor";
    public static final String COLUMN_PIZZA_PRICE = "pizza_price";
    public static final String COLUMN_PIZZA_CALORIES = "pizza_calories";
    public static final String COLUMN_PIZZA_INGREDIENTS = "pizza_ingredients";
    public static final String COLUMN_IMAGE_URL = "pizza_image";
    public static int DATABASE_VERSION = 1;
    
    public PizzaDatabaseHelper(
            @Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public void insertPizzaOrder(Pizza pizzaOrdered) {
        ContentValues pizzaContent = new ContentValues();
        pizzaContent.put(COLUMN_PIZZA_FLAVOR, pizzaOrdered.getPizzaFlavor());
        pizzaContent.put(COLUMN_PIZZA_PRICE, pizzaOrdered.getPizzaPrice());
        pizzaContent.put(COLUMN_PIZZA_CALORIES, pizzaOrdered.getCalories());
        pizzaContent.put(COLUMN_PIZZA_INGREDIENTS, pizzaOrdered.getIngredients());
        pizzaContent.put(COLUMN_IMAGE_URL, pizzaOrdered.getImageUrl());
        
        SQLiteDatabase database = getWritableDatabase();
        database.insert(PIZZA_TABLE_NAME, null, pizzaContent);
        
    }    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "
                + PIZZA_TABLE_NAME + " ( "
                + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PIZZA_FLAVOR + " TEXT, "
                + COLUMN_PIZZA_PRICE + " TEXT, "
                + COLUMN_PIZZA_CALORIES + " TEXT, "
                + COLUMN_PIZZA_INGREDIENTS + " TEXT, "
                + COLUMN_IMAGE_URL + " TEXT)";
        
        db.execSQL(CREATE_TABLE);
    }
    
    public Cursor getAllPizzaOrders() {
        //TODO: return an array list instead of cursor
        return getReadableDatabase().rawQuery(
                "SELECT * FROM "
                        + PIZZA_TABLE_NAME,
                null,
                null);
    }    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String update = "DROP TABLE IF EXISTS " + PIZZA_TABLE_NAME;
        db.execSQL(update);
        DATABASE_VERSION = newVersion;
        onCreate(db);
    }
    

    

}
