package com.example.kirill.neutrinotestapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kirill.neutrinotestapp.objects.CreateResponseObject;

import java.io.File;
import java.io.IOException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

import static android.view.View.GONE;


public class CreateAdvertisementActivity extends Activity {

    private String selectedImagePath = "";
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_FILE = 2;
    private View.OnClickListener getCameraListener;
    private Uri mImageCaptureUri;
    EditText etName, etDescription;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advertisement);
        etName = (EditText) findViewById(R.id.etAdvertisementName);
        etDescription = (EditText) findViewById(R.id.etAdvertisementDescription);
        ivImage = (ImageView) findViewById(R.id.ivAdvertisementImage);
        Button btnPost = (Button) findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ivImage.getDrawable()!=getResources().getDrawable(android.R.drawable.ic_menu_report_image)){
                    File file = new File(selectedImagePath);
                    TypedFile image = new TypedFile("image/jpg", file);
                    String name = String.valueOf(etName.getText());
                    String description = String.valueOf(etDescription.getText());
                    RestClient.getAdvertisementService(CreateAdvertisementActivity.this).createAdvertisement(name, description, image, new Callback<CreateResponseObject>() {
                        @Override
                        public void success(CreateResponseObject createResponseObject, Response response) {
                            if(createResponseObject.getSuccess()){
                                SupportMethods.showDialog(CreateAdvertisementActivity.this, getString(R.string.str_ok));
                            }else {
                                SupportMethods.showDialog(CreateAdvertisementActivity.this, createResponseObject.getMessage());
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                }
            }
        });
        captureImageInitialization();
    }

    private void captureImageInitialization() {
        final String[] items = new String[]{"Take from camera",
                "Select from gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Image");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) { // pick from
                // camera
                if (item == 0) {
                   Intent intent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Utils.setImageUri());
                    startActivityForResult(intent, PICK_FROM_CAMERA);
                } else { // pick from file
                    Intent intent;
                    if (Build.VERSION.SDK_INT < 19) {
                        intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,
                                "Complete action using"), PICK_FROM_FILE);
                    } else {
                        intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                                mImageCaptureUri);
                        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
                    }
                }
            }
        });
        final AlertDialog dialog = builder.create();
        getCameraListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        };
        ivImage.setOnClickListener(getCameraListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode){
            case PICK_FROM_CAMERA:
                selectedImagePath = Utils.getImagePath();
                ivImage.setImageBitmap(Utils.decodeFile(selectedImagePath));
                break;
            case PICK_FROM_FILE:
                mImageCaptureUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageCaptureUri);
                    ivImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

}
