package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
    private TextView Fullname;
    private TextView Email;
    private TextView Hpage;
    private TextView About;
    private ImageView avatarImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Fullname = findViewById(R.id.label_fullname);
        Email = findViewById(R.id.label_email);
        Hpage = findViewById(R.id.label_homepage);
        About = findViewById(R.id.label_about);
        avatarImage = findViewById((R.id.image_profile));


        Bundle extras = getIntent().getExtras();
        if (extras != null ){
            String fn = getIntent().getExtras().getString("fname");
            String em = getIntent().getExtras().getString("email");
            String hp = getIntent().getExtras().getString("hpage");
            String ab = getIntent().getExtras().getString("about");
            Bundle extra = getIntent().getExtras();
            Bitmap bmp = extra.getParcelable("image");

            Fullname.setText(fn);
            Email.setText(em);
            Hpage.setText(hp);
            About.setText(ab);
            avatarImage.setImageBitmap(bmp);
        }
    }

    public void handleHomePage(View view) {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String homepageText = bundle.getString("hpage");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + homepageText));
            startActivity(intent);
        }

    }
}
