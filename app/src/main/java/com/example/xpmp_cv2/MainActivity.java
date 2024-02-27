package com.example.xpmp_cv2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextProduct1Price;
    private EditText editTextProduct1Quantity;
    private EditText editTextProduct2Price;
    private EditText editTextProduct2Quantity;
    private Button buttonCompare;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextProduct1Price = findViewById(R.id.editTextProduct1Price);
        editTextProduct1Quantity = findViewById(R.id.editTextProduct1Quantity);
        editTextProduct2Price = findViewById(R.id.editTextProduct2Price);
        editTextProduct2Quantity = findViewById(R.id.editTextProduct2Quantity);
        buttonCompare = findViewById(R.id.buttonCompare);
        textViewResult = findViewById(R.id.textViewResult);

        // Set onClickListener for the compare button
        buttonCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comparePrices();
            }
        });
    }

    private void comparePrices() {
        float price1 = Float.parseFloat(editTextProduct1Price.getText().toString());
        float quantity1 = Float.parseFloat(editTextProduct1Quantity.getText().toString());
        float price2 = Float.parseFloat(editTextProduct2Price.getText().toString());
        float quantity2 = Float.parseFloat(editTextProduct2Quantity.getText().toString());

        float totalPrice1 = price1 / quantity1;
        float totalPrice2 = price2 / quantity2;

        String cheaperProduct;
        String message;

        if (totalPrice1 < totalPrice2) {
            cheaperProduct = "Zboží 1";
            message = "Zboží 1 je levnější.";
        } else if (totalPrice1 > totalPrice2) {
            cheaperProduct = "Zboží 2";
            message = "Zboží 2 je levnější.";
        } else {
            cheaperProduct = "";
            message = "Obě zboží mají stejnou cenu za jednotku.";
        }

        // Display result in AlertDialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Výsledek porovnání cen");
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("OK", null);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        // Highlight cheaper product
        switch (cheaperProduct) {
            case "Zboží 1":
                textViewResult.setText("Výsledek porovnání cen: Zboží 1 je levnější.");
                textViewResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                break;
            case "Zboží 2":
                textViewResult.setText("Výsledek porovnání cen: Zboží 2 je levnější.");
                textViewResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                break;
            default:
                textViewResult.setText("Obě zboží mají stejnou cenu za jednotku.");
                textViewResult.setTextColor(getResources().getColor(android.R.color.black));
                break;
        }
    }
}