package com.example.android.flavor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h1> Assignment3 </h1>
 * {@link OrderActivity} simply displays the user a list of items that can order specific products.
 * The user can order the selected items,filling all the information needed and sending the order details by email.
 * <p>
 * @author Adapteed from code written by Colette Kirwan. DCU Open Education
 * @author OrderActivity class updated by Abdelkrim Ouajjit .
 * @version 1.0 (current version number of program)
 * @version 2.0 (version updated 20/12/2018 )
 * @since 2018-12-10 (the version of the package this class
 * was first added to)
 *
 * </p>
 */

public class OrderActivity extends AppCompatActivity
{
    /**
     * Creating a variable based on Uri class named mPhotoURI.
     * Creating a variable based on Spinner class named mSpinner.
     * Creating a variable based on EditText class named mCustomerName.
     * Creating a variable based on EditText class named mCustomerName.
     */

    Uri mPhotoURI;
    Spinner mSpinner;
    EditText mCustomerName;
    EditText meditOptional;
    EditText mdeliveryAddress;
    boolean imageTaken= false;
    /**
     * Declaring a constant of the type integer named REQUEST_IMAGE_CAPTURE with the value 1.
     * Declaring a constant of the type integer named REQUEST_TAKE_PHOTO with the value 2.
     * Declaring a constant of the type string named TAG with value Assign3.
     */

    static final int REQUEST_TAKE_PHOTO = 2;
    private static final String TAG = "Assign3";

    /**
     * Creates the UI.
     * @see android.app.Activity#onCreate(android.os.Bundle)
     * @param savedInstanceState Bundle containing state information (key-value pairs).
     */
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        /*
         * Required call through to Activity.onCreate().
         * Restore any saved instance state.
         */
        super.onCreate(savedInstanceState);
        // Set up the application's user interface (Content view)
        setContentView(R.layout.activity_order);

        /**
         * Using findViewById()method To find EditText.
         * Get a reference to a EditText in the content view and store that value inside the container meditOptional.
         */

        meditOptional = (EditText) findViewById(R.id.editOptional);

        /**
         * Sets the IME options of the meditOptional with action Done on text field.
         * sets the input keyboard displayed to be only text.
         */

        meditOptional.setImeOptions(EditorInfo.IME_ACTION_DONE);
        meditOptional.setRawInputType(InputType.TYPE_CLASS_TEXT);

        /**
         * *******************
         * Creating a spinner.
         * *******************
         * There are two steps to create spinner :
         * 1- Create the data source.
         * 2- Define the appearance layout file through which the adapter will put data inside the spinner.
         * Get a reference to a Spinner in the content view and store that value inside the container mSpinner .
         * Get a reference to a EditText in the content view and store that value inside the container mCustomerName .
         */


        mSpinner = (Spinner) findViewById(R.id.spinner);
        mCustomerName = (EditText) findViewById(R.id.editCustomer);

        /**
         * Creating an adapter for the spinner.
         * Create an ArrayAdapter using the string array and a default spinner layout.
         * Attaching data adapter to spinner.
         * R.array.ui_time_entries (The string array was modified)
         */

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ui_time_entries, R.layout.spinner_days);
        mSpinner.setAdapter(adapter);

    }

    /**
     * *****************
     * Capturing images
     * *****************
     * Capturing images using MediaStore.ACTION_IMAGE_CAPTURE intent.
     * Ensure that there's a camera activity to handle the intent.
     * @param v it's view (When the user clicks on Text View, dispatchTakePictureIntent() method will be called).
     *
     */

    public void dispatchTakePictureIntent(View v)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        /**
         * Create an instance of SimpleDateFormat used for formatting.
         * The string timeStamp is representation of date (year/month/day_hour/minute/second).
         */


        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        // Creating unique string name of the picture with .jpg extension .
        String imageFileName = "my_tshirt_image_" + timeStamp + ".jpg";
        // On Logcat : To view that imageFileName string was created and the file to save the picture will be available.
        Log.i(TAG, "imagefile");
        // Create a file where the photo will be store.
        File file = new File(Environment.getExternalStorageDirectory(), imageFileName);
        /**
         * url  an absolute URL giving the base location of the image.
         * Uri provided by FileProvider can be used also providing Uri for sharing files with other apps too.
         */
        mPhotoURI = Uri.fromFile(file);
        // On Logcat : Display information when the uri was been converted to string.
        Log.i(TAG, mPhotoURI.toString());

        /**
         * Passing the photos as extras, using MediaStore.EXTRA_OUTPUT.
         * The image taken will be written to that path (mPhotoURI).
         */

        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI);
        /**
         * startActivityForResult() has two parameters:
         * The Intent (which can be used as well to pass data between activities).In this case we are calling the camera.
         * A "request code" integer that identifies your request(REQUEST_TAKE_PHOTO).
         */
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
        //incase of caching if it comes from the activity stack, just a precaution
        intent.removeExtra(MediaStore.EXTRA_OUTPUT);

    }

    /**
     ************************************
     * Getting a Result from an Activity
     * **********************************
     * @param requestCode The integer argument is a "request code" that identifies your request.
     * @param resultCode A result code specified by the second activity. This is either RESULT_OK
     * If the operation was successful or RESULT_CANCELED
     * If the user backed out or the operation failed for some reason.
     * @param data An Intent that carries the result data.
     *
     */

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        //also can give user a message that everything went ok
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK)
        {
            /**
             * Let user know that image saved
             * I have strings in strings.xml but have hardcoded here to copy/paste to students if needed
             */

            imageTaken=true;

            CharSequence text = "Image Taken successfully";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

            /**
             * Or perhaps do a dialog should only use one method i.e. toast or dialog, but have both code here for demo purposes
             * Also I have strings in strings.xml but have hardcoded here to copy/paste to students if needed
             */

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            /**
             * Hardcoded found in this line :
             * builder.setTitle("Notification!").setMessage("Image saved successfully.").setPositiveButton("OK", null).show();
             * The "Notification!" was replaced by R.string.notification_title.
             * The "Image saved successfully" was replaced by R.string.image_confirm.
             */
            builder.setTitle(R.string.notification_title).setMessage(R.string.image_confirm).setPositiveButton("OK", null).show();
        }


    }

    /**
     * Returns the Email Body Message.
     * Email body message is created used prescription related data inputted from user
     * @return Email Body Message
     */

    private String createOrderSummary()
    {
        /**
         * Hardcoded found on this code:
         * String orderMessage = "Name" + " " + mCustomerName.getText().toString();
         * orderMessage += "\n" + "\n" + "Please find my order attached";
         * String optionalInstructions = meditOptional.getText().toString();

         * orderMessage += "\n" + "I will collect it in" + ((CharSequence) mSpinner.getSelectedItem()).toString() + " days";
         * orderMessage += "\n" + "Regards" + "\n" + mCustomerName.getText().toString();
         * return orderMessage;
         * The code was replaced as :
         */

        String orderMessage = getString(R.string.customer_name) + " " + mCustomerName.getText().toString();
        orderMessage += "\n" + "\n" + getString(R.string.order_message_1);
        String optionalInstructions = meditOptional.getText().toString();

        orderMessage += "\n" + getString(R.string.order_message_collect) + ((CharSequence) mSpinner.getSelectedItem()).toString() + " days";
        orderMessage += "\n" + getString(R.string.order_message_end) + "\n" + mCustomerName.getText().toString();
        return orderMessage;

        //update Email screen
    }

    /**
     *
     * @param v Its view Button type
     * Validation the name of the user
     * Validation the delivery address of the user
     * Validation of the picture was taken.
     */

    public void sendEmail(View v)
    {

        /**
         * ************************************
         * Sending the order through the email.
         * ************************************
         * When the user clicks on send order sendEmail() method will be called.
         * The sendEmail() method has Two tasks :
         * 1- Validation of the all inputs :
         *       - Check that Name is not empty, and ask do they want to continue.
         *       - check that delivery address is not empty, and ask do they want to continue.
         *       - Check that picture was taken.
         * 2- Sending all the information of the order  to the email.
         */

        mdeliveryAddress = (EditText) findViewById(R.id.editOptional);
        String customerName = mCustomerName.getText().toString();
        String deliveryAddress = mdeliveryAddress.getText().toString();

        if (customerName.matches("")) {
            Toast.makeText(this, getString(R.string.customer_name_blank), Toast.LENGTH_SHORT).show();

        } else if  (deliveryAddress.matches(""))
        {
            Toast.makeText(this, "Please enter your delivery address", Toast.LENGTH_SHORT).show();
        }else if (!imageTaken)
            {
                Toast.makeText(this, "Please take a picture ", Toast.LENGTH_SHORT).show();
            }
         else
        {
            /**
             * intent.ACTION_SEND indicates that the intent is sending data from one activity to another, even across process boundaries.
             * Lets the user choose from several Activities.
             * Adding a Subject and Body.
             * Intent.EXTRA_STREAM to add attachment.
             *
             */

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.to_email)});
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
            intent.putExtra(Intent.EXTRA_STREAM, mPhotoURI);
            intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
            if (intent.resolveActivity(getPackageManager()) != null)
            {
                startActivity(intent);
            }
        }
    }


}
