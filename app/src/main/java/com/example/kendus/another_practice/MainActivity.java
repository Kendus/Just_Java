package com.example.kendus.another_practice;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;



/**

 * This app displays an order form to order coffee.

 */

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    double cost = 2.75;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        playBackground();

    }





    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        CheckBox whippedCreamBox= (CheckBox) findViewById(R.id.whippedCreamBox);
        CheckBox chocoBox = (CheckBox) findViewById(R.id.chocoBox);
        boolean isWhippedCream = whippedCreamBox.isChecked();
        boolean isChocolate = chocoBox.isChecked();
        EditText nameBox = (EditText)findViewById(R.id.nameInput);
        String name = nameBox.getText().toString();
        double finalCost = cost;
        if (isWhippedCream){
            finalCost+=0.25;
        }
        if (isChocolate){
            finalCost+=0.50;
        }

        String nameMessage = "\n Name: " + name;
        String whippedMessage = "\n Whipped Cream: " + isWhippedCream;
        String chocolateMessage = "\n Chocolate: " + isChocolate;
        String quantityMessage = "\n Quantity: "+ quantity;
        String priceMessage = "\n Total: $" + (finalCost*quantity);
        String tyMessage = "\n Thank You!";

        String finalMessage = nameMessage+whippedMessage+chocolateMessage+quantityMessage+priceMessage+tyMessage;

        composeEmail("Reciept", finalMessage);


    }


    /**
     * This method displays the given quantity value on the screen.
     */


    public void playBackground(){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.saib_in_your_arms);
        mp.start();
    }

    private void display(int number) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity);

        quantityTextView.setText("" + number);

    }

    private void displaymessage(String message) {

        TextView quantityTextView = (TextView) findViewById(R.id.orderSummaryTextView);

        quantityTextView.setText(message);
    }





    public void increment(View view){
        quantity++;
        display(quantity);

    }

    public void decrement(View view){
        if(quantity>0)
            quantity--;
            display(quantity);


    }

    public void composeEmail(String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
