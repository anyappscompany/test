package ua.com.anyapps.test.Server;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ua.com.anyapps.test.Server.Users.Users;

public interface ServerApi {
    @GET("/api/")
    Call<Users> getUsers(@Query("results") int total, @Query("sults") int sults); // total - число возвращенных пользователей
}
