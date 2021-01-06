package com.terremoto.week02day03.view;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.terremoto.week02day03.R;
import com.terremoto.week02day03.model.data.Pizza;
import com.terremoto.week02day03.model.db.PizzaDatabaseHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editText_pizza_flavor)
    public EditText pizzaFlavorEditText;
    
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editText_pizza_calories)
    public EditText pizzaCaloriesEditText;
    
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editText_pizza_price)
    public EditText pizzaPriceEditText;
    
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editText_pizza_url)
    public EditText pizzaUrlEditText;
    
    private PizzaDatabaseHelper databaseHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ButterKnife.bind(this);
        databaseHelper = new PizzaDatabaseHelper(this);
        readOrders();
    }
    
    private void readOrders() {
        Cursor orders = databaseHelper.getAllPizzaOrders();
        orders.moveToFirst();
        
        while (orders.moveToNext()) {
            String pizzaName = orders.getString(orders.getColumnIndex(PizzaDatabaseHelper.COLUMN_PIZZA_FLAVOR));
            int pizzaCalories = Integer.parseInt(orders.getString(orders.getColumnIndex(PizzaDatabaseHelper.COLUMN_PIZZA_CALORIES)));
            String pizzaPrice = orders.getString(orders.getColumnIndex(PizzaDatabaseHelper.COLUMN_PIZZA_PRICE));
            String pizzaUrl = orders.getString(orders.getColumnIndex(PizzaDatabaseHelper.COLUMN_IMAGE_URL));
    
            Log.d("TAG_X", pizzaName + ", " + pizzaCalories + ", $" + pizzaPrice + " "+ pizzaUrl);
        }
    }
    
    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.button_make_order)
    public void onMakeOrder(View view) {
        if (checkInput()) {
            String flavor = pizzaFlavorEditText.getText().toString().trim();
            double price = Double.parseDouble(pizzaPriceEditText.getText().toString().trim());
            int calories = Integer.parseInt(pizzaCaloriesEditText.getText().toString().trim());
            String url = pizzaUrlEditText.getText().toString().trim();
            
            Pizza pizza = new Pizza(flavor, price, calories, "", url);
            databaseHelper.insertPizzaOrder(pizza);
            
            Toast.makeText(this, "Pizza Inserted", Toast.LENGTH_SHORT).show();
            clearFields();
            readOrders();
        }
    }
    
    private boolean checkInput() {
        if (pizzaFlavorEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Pizza Flavor can not be empty.", Toast.LENGTH_SHORT)
                    .show();
        } else if (pizzaPriceEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Pizza Price can not be empty.", Toast.LENGTH_SHORT)
                    .show();
        } else if (pizzaCaloriesEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Pizza Calories can not be empty.", Toast.LENGTH_SHORT)
                    .show();
        } else if (pizzaUrlEditText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Pizza URL can not be empty.", Toast.LENGTH_SHORT)
                    .show();
        } else
            return true;
        return false;
    }
    
    private void clearFields() {
        pizzaFlavorEditText.setText("");
        pizzaCaloriesEditText.setText("");
        pizzaPriceEditText.setText("");
        pizzaUrlEditText.setText("");
    }
}