

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

package com.example.android.justjava;

import android.content.Intent;
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

    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox= (CheckBox) findViewById(R.id.Whipped_cream_checkbox);
        boolean hasWhippedCream= whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox= (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate= chocolateCheckBox.isChecked();

        EditText name= (EditText) findViewById(R.id.name_input);
        String user_name= name.getText().toString();

        int price = calculatePrice(hasChocolate, hasWhippedCream);
        String priceMessage= createOrderSummary(price, hasWhippedCream, hasChocolate,user_name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));;
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject)+ " " + user_name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);}

        }



    public void increment(View view) {

        if (quantity<100)
            displayQuantity(++quantity);
    }


    public void decrement(View view) {

        if (quantity > 0)
         displayQuantity(--quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }





    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(boolean hasChocolate, boolean hasWhippedCream) {
        int price = 5;
        if (hasChocolate)
            price= price + 2;
        if (hasWhippedCream)
            price++;
        return price * quantity;
    }

    private String createOrderSummary(int price, boolean cream, boolean chocolate, String name)
    {
        String orderSummary= getString(R.string.order_summary_name)+ " " + name +
                "\n" + getString(R.string.quantity)+ " " + quantity
                + "\n" + getString(R.string.addWhippedCream) + " " +cream
                + "\n"+ getString(R.string.addChocolate)+ " " + chocolate
                 + "\n"+ getString(R.string.order_summary_price) + price + "\n" + getString(R.string.thank_you);
        return orderSummary;
    }
}