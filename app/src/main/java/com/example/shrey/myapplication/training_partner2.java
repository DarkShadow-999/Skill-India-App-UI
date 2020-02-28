package com.example.shrey.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import pub.devrel.easypermissions.EasyPermissions;

public class training_partner2 extends AppCompatActivity {

    private ImageView imageview;
    private Toolbar toolbar;
    private EditText inputTCName, inputDOR, inputAddress, inputPhone, inputEmail, inputState, inputSector, inputCity;
    private TextInputLayout inputLayoutTCName, inputLayoutDOR, inputLayoutAddress, inputLayoutPhone, inputLayoutEmail, inputLayoutState, inputLayoutSector, inputLayoutCity;
    private Button btnSignUp, Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_training_partner2);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputLayoutTCName = (TextInputLayout) findViewById(R.id.input_layout_tcname);
        inputLayoutDOR = (TextInputLayout) findViewById(R.id.input_layout_dor);
        inputLayoutAddress = (TextInputLayout) findViewById(R.id.input_layout_address);
        inputLayoutPhone = (TextInputLayout) findViewById(R.id.input_layout_phone);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutState = (TextInputLayout) findViewById(R.id.input_layout_state);
        inputLayoutSector = (TextInputLayout) findViewById(R.id.input_layout_sector);
        inputLayoutCity = (TextInputLayout) findViewById(R.id.input_layout_city);

        inputTCName = (EditText) findViewById(R.id.input_tcname);
        inputDOR = (EditText) findViewById(R.id.input_dor);
        inputAddress = (EditText) findViewById(R.id.input_address);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputState = (EditText) findViewById(R.id.input_state);
        inputSector = (EditText) findViewById(R.id.input_sector);
        inputCity = (EditText) findViewById(R.id.input_city);

        btnSignUp = (Button) findViewById(R.id.btn_signup);
        Button = (Button) findViewById(R.id.button19);


        inputTCName.addTextChangedListener(new MyTextWatcher(inputTCName));
        inputDOR.addTextChangedListener(new MyTextWatcher(inputDOR));
        inputAddress.addTextChangedListener(new MyTextWatcher(inputAddress));
        inputPhone.addTextChangedListener(new MyTextWatcher(inputPhone));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputState.addTextChangedListener(new MyTextWatcher(inputState));
        inputSector.addTextChangedListener(new MyTextWatcher(inputSector));
        inputCity.addTextChangedListener(new MyTextWatcher(inputCity));
        imageview = (ImageView) findViewById(R.id.circleView);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    // function to choose profile image
    private void selectImage() {

// to create dialog box to choose the method to select profile image

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(training_partner2.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    EnableRuntimePermission();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {

                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
//            to take photo from camera
            if (requestCode == 1) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageview.setImageBitmap(bitmap);

            }

//          to choose photo from gallery
            else if (requestCode == 2) {


//          real time permission required for api 23+ to read and write external storage
                String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

                if (EasyPermissions.hasPermissions(this, galleryPermissions)) {
                    Uri selectedImage = data.getData();
                    String[] filePath = {MediaStore.Images.Media.DATA};
                    Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                    c.moveToFirst();

                    int columnIndex = c.getColumnIndex(filePath[0]);
                    String picturePath = c.getString(columnIndex);
                    c.close();

                    Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                    imageview.setImageBitmap(thumbnail);
                } else {
                    EasyPermissions.requestPermissions(this, "Access for storage",
                            101, galleryPermissions);
                }


            }
        }
    }


//    function to check textfields of form
    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateTCName()) {
            return;
        }

        if (!validateDOR()) {
            return;
        }

        if (!validateAddress()) {
            return;
        }
        if (!validatePhone()){
            return;
        }
        if (!validateEmail()){
            return;
        }
        if (!validateState()){
            return;
        }
        if (!validateSector()){
            return;
        }
        if (!validateCity()){
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateTCName() {
        if (inputTCName.getText().toString().trim().isEmpty()) {
            inputLayoutTCName.setError(getString(R.string.err_msg_name));
            requestFocus(inputTCName);
            return false;
        } else {
            inputLayoutTCName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateDOR() {
        if (inputDOR.getText().toString().trim().isEmpty()) {
            inputLayoutDOR.setError(getString(R.string.err_msg_date));
            requestFocus(inputDOR);
            return false;
        } else {
            inputLayoutDOR.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateAddress() {
        if (inputAddress.getText().toString().trim().isEmpty()) {
            inputLayoutAddress.setError(getString(R.string.err_msg_address));
            requestFocus(inputAddress);
            return false;
        } else {
            inputLayoutAddress.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validatePhone(){
        if (inputPhone.getText().toString().trim().isEmpty()){
            inputLayoutPhone.setError(getString(R.string.err_msg_phone));
            requestFocus(inputPhone);
            return false;
        }   else{
            inputLayoutAddress.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateState(){
        if (inputState.getText().toString().trim().isEmpty()){
            inputLayoutState.setError(getString(R.string.err_msg_state));
            requestFocus(inputState);
            return false;
        }   else{
            inputLayoutState.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateSector(){
        if (inputSector.getText().toString().trim().isEmpty()){
            inputLayoutSector.setError(getString(R.string.err_msg_sector));
            requestFocus(inputSector);
            return false;
        }   else{
            inputLayoutSector.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateCity(){
        if (inputCity.getText().toString().trim().isEmpty()){
            inputLayoutCity.setError(getString(R.string.err_msg_city));
            requestFocus(inputCity);
            return false;
        }   else{
            inputLayoutCity.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_tcname:
                    validateTCName();
                    break;
                case R.id.input_dor:
                    validateDOR();
                    break;
                case R.id.input_address:
                    validateAddress();
                    break;
                case R.id.input_phone:
                    validatePhone();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_state:
                    validateState();
                    break;
                case R.id.input_sector:
                    validateSector();
                    break;
                case R.id.input_city:
                    validateCity();
                    break;
            }
        }
    }
    //    real time permission for camera
    public void EnableRuntimePermission(){
        final int RequestPermissionCode  = 1 ;

        if (ActivityCompat.shouldShowRequestPermissionRationale(training_partner2.this,
                Manifest.permission.CAMERA))
        {

            Toast.makeText(training_partner2.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(training_partner2.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }
    // real time permission for camera
    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        final int RequestPermissionCode  = 1 ;
        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(training_partner2.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(training_partner2.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
}

