package ua.com.anyapps.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class UserActivity extends AppCompatActivity {
    private static final String TAG = "debapp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name) + ": " + intent.getStringExtra("user_name"));

        ImageView ivUserPhotoLarge = findViewById(R.id.ivUserPhotoLarge);
        Picasso.get().load(intent.getStringExtra("user_photo")).placeholder( R.drawable.photo_load_progress).into(ivUserPhotoLarge);

        TextView tvGender = findViewById(R.id.tvGender);
        tvGender.setText(getResources().getString(R.string.user_info_gender) + " " +  intent.getStringExtra("user_gender"));

        TextView tvPhone = findViewById(R.id.tvPhone);
        tvPhone.setText(getResources().getString(R.string.user_info_phone) + " " +  intent.getStringExtra("user_phone"));

        TextView tvCountry = findViewById(R.id.tvCountry);
        tvCountry.setText(getResources().getString(R.string.user_info_country) + " " +  intent.getStringExtra("user_country"));

        TextView tvCity = findViewById(R.id.tvCity);
        tvCity.setText(getResources().getString(R.string.user_info_city) + " " +  intent.getStringExtra("user_city"));

        TextView tvPostCode = findViewById(R.id.tvPostCode);
        tvPostCode.setText(getResources().getString(R.string.user_info_post_code) + " " +  intent.getStringExtra("user_postcode"));

    }
}