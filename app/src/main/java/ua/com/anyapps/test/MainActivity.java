package ua.com.anyapps.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.com.anyapps.test.Server.ServerConnect;
import ua.com.anyapps.test.Server.Users.Results;
import ua.com.anyapps.test.Server.Users.Users;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "debapp";
    UserListAdapter userListAdapter;
    ListView lvUserList;
    ProgressBar pbLoader;
    List<Results> results; // список пользователей
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvUserList = findViewById(R.id.lvUserList);
        pbLoader = findViewById(R.id.pbLoader);

        lvUserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.putExtra("user_photo", results.get(position).getPicture().getLarge());
                intent.putExtra("user_name", results.get(position).getName().getTitle() + " " + results.get(position).getName().getFirst() + " " + results.get(position).getName().getLast());
                intent.putExtra("user_gender", results.get(position).getGender());
                intent.putExtra("user_phone", results.get(position).getPhone());
                intent.putExtra("user_city", results.get(position).getLocation().getCity());
                intent.putExtra("user_country", results.get(position).getLocation().getCountry());
                intent.putExtra("user_postcode", results.get(position).getLocation().getPostcode());
                startActivity(intent);
            }
        });

        // если не поворот
        if(savedInstanceState == null || !savedInstanceState.containsKey("results")) {
            loadUserList();
        }else{
            showLoader();
            String json = savedInstanceState.getString("results");
            results = new ArrayList<>();
            Gson gson = new Gson();
            Type type = new TypeToken<List<Results>>(){}.getType();
            List<Results> userList = gson.fromJson(json, type);
            for (Results user : userList){
                results.add(user);
            }
            hideLoader();
            userListAdapter = new UserListAdapter(MainActivity.this, results);
            lvUserList.setAdapter(userListAdapter);
        }
    }

    private void showLoader(){
        lvUserList.setVisibility(View.GONE);
        pbLoader.setVisibility(View.VISIBLE);
    }
    private void hideLoader(){
        lvUserList.setVisibility(View.VISIBLE);
        pbLoader.setVisibility(View.GONE);
    }

    private void loadUserList(){
        showLoader();
        results = new ArrayList<>();
        ServerConnect.getInstance()
                .getJSONApi()
                .getUsers(getResources().getInteger(R.integer.max_users), getResources().getInteger(R.integer.sults))
                .enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        hideLoader();
                        if (response.isSuccessful()) {
                            if (Integer.parseInt(response.body().getInfo().getResults())>0) {
                                for(int i=0; i<Integer.parseInt(response.body().getInfo().getResults()); i++){
                                    results.add(response.body().getResults()[i]);
                                }
                                userListAdapter = new UserListAdapter(MainActivity.this, results);
                                lvUserList.setAdapter(userListAdapter);
                            }else{ // в ответе 0 пользователей
                                Toast.makeText(MainActivity.this, getResources().getStringArray(R.array.app_errors)[1], Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, getResources().getStringArray(R.array.app_errors)[0], Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        // при ошибке очищается предыдущий список
                        results.clear();
                        userListAdapter = new UserListAdapter(MainActivity.this, results);
                        lvUserList.setAdapter(userListAdapter);
                        hideLoader();
                        Toast.makeText(MainActivity.this, getResources().getStringArray(R.array.app_errors)[2], Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String json = new Gson().toJson(results);
        outState.putString("results", json);
    }
}