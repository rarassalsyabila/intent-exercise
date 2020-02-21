package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    public static final String FULLNAME_KEY = "fname";
    public static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY = "pass";
    public static final String CPASSWORD_KEY = "cpass";
    public static final String HOMEPAGE_KEY = "hpage";
    public static final String ABOUT_KEY = "about";
    public static final String AVATAR_KEY = "image";

    private TextInputEditText FullnameInput;
    private TextInputEditText EmailInput;
    private TextInputEditText PasswordInput;
    private TextInputEditText CPasswordInput;
    private TextInputEditText HomepageInput;
    private TextInputEditText AboutInput;
    private ImageView avatarImage;

    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FullnameInput = findViewById(R.id.text_fullname);
        EmailInput = findViewById(R.id.text_email);
        PasswordInput = findViewById(R.id.text_password);
        CPasswordInput = findViewById(R.id.text_confirm_password);
        HomepageInput = findViewById(R.id.text_homepage);
        AboutInput = findViewById(R.id.text_about);
        avatarImage = findViewById(R.id.image_profile);


    }


    public void handleProfil(View view) {
        String fullname = FullnameInput.getText().toString();
        String email = EmailInput.getText().toString();
        String pass = PasswordInput.getText().toString();
        String cpass = CPasswordInput.getText().toString();
        String Hpage = HomepageInput.getText().toString();
        String About = AboutInput.getText().toString();



        if (fullname.length() == 0) {
            FullnameInput.setError("Username is needed!");
        } else if (email.length() == 0) {
            EmailInput.setError("Email is needed!");
        } else if (pass.length() == 0) {
            PasswordInput.setError("Password is needed!");
        } else if (cpass.length() == 0) {
            CPasswordInput.setError("Confirm Password is needed!");

        } else if (Hpage.length() == 0) {
            HomepageInput.setError("HomePage is needed!");
        } else if (About.length() == 0) {
            AboutInput.setError("About is needed!");
        } else{
            if(isValidEmail(email)){
                if(isValidUrl(Hpage)){
                    if (pass.equals(cpass)) {
                        Toast.makeText(getApplicationContext(), "Registrasion success!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, ProfileActivity.class);
                        avatarImage.buildDrawingCache();
                        Bitmap image = avatarImage.getDrawingCache();
                        Bundle extras = new Bundle();
                        extras.putParcelable(AVATAR_KEY, image);
                        intent.putExtras(extras);
                        intent.putExtra(FULLNAME_KEY, fullname);
                        intent.putExtra(EMAIL_KEY, email);
                        intent.putExtra(HOMEPAGE_KEY, Hpage);
                        intent.putExtra(ABOUT_KEY, About);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "Not valid URL on your homepage, xxx.xxx", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Please input your email, xx@xx.xx", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void handlePhoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatarImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public static boolean isValidEmail(String email) {
        boolean validate;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern)) {
            validate = true;
        } else if (email.matches(emailPattern2)) {
            validate = true;
        } else {
            validate = false;
        }

        return validate;
    }

    public static boolean isValidUrl(String Hpage) {
        boolean validate;
        String emailPattern = "[a-zA-Z0-9._-]+.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+\\.+[a-z]+\\.+[a-z]+";

        if (Hpage.matches(emailPattern)) {
            validate = true;
        } else if (Hpage.matches(emailPattern2)) {
            validate = true;
        } else {
            validate = false;
        }

        return validate;
    }



}


