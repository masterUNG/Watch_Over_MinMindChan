package mean.chan.mind.sendgps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class AddChildActivity extends AppCompatActivity {

    private ImageView pictureImageView;
    private EditText codeEditText, nameEditText;
    private RadioGroup radioGroup;
    private String codeString, nameString, genderString;
    private String[] loginStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        //Get Value From Intent
        getValueFromIntent();

        //Initial View
        initialView();

        //RedioGroup Controller
        radioGroupController();

        //Back Controller
        backController();

        //Camera Controller
        cameraController();

        //Save Controller
        saveController();


    }   // Main Method

    private void radioGroupController() {

    }

    private void getValueFromIntent() {
        loginStrings = getIntent().getStringArrayExtra("Login");
    }

    private void initialView() {
        pictureImageView = (ImageView) findViewById(R.id.imvPicture);
        codeEditText = (EditText) findViewById(R.id.edtCode);
        nameEditText = (EditText) findViewById(R.id.edtName);
        radioGroup = (RadioGroup) findViewById(R.id.ragGender);
    }

    private void saveController() {
        ImageView imageView = (ImageView) findViewById(R.id.imvSave);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //Get Value From Edit Text
                codeString = codeEditText.getText().toString().trim();
                nameString = nameEditText.getText().toString().trim();

                //Check Space
                if (codeString.equals("") || nameString.equals("")) {
                    //Have Space
                    MyAlert myAlert = new MyAlert(AddChildActivity.this);
                    myAlert.myDialog("Have Space", "Please Fill All Blank");
                } else {
                    //No Space
                    uploadValueToServer();

                }

            }
        });
    }

    private void uploadValueToServer() {

        

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Log.d("11JulyV1", "Result OK");

            Uri uri = data.getData();
            try {

                Bitmap bitmap = BitmapFactory
                        .decodeStream(getContentResolver().openInputStream(uri));
                pictureImageView.setImageBitmap(bitmap);

            } catch (Exception e) {
                Log.d("11JulyV1", "e ==> " + e.toString());
            }

        }   // if

    }   // onActivityResult

    private void cameraController() {

        ImageView cameraImageView = (ImageView) findViewById(R.id.imvCamera);

        cameraImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

            }
        });

    }

    private void backController() {
        ImageView imageView = (ImageView) findViewById(R.id.imvBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}   // Main Class
