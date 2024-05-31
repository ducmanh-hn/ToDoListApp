package com.todolist.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.todolist.Constant;
import com.todolist.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);


    @POST("/api/task")
    Call<Task> sendTasks(@Body Task task);
    @POST("/api/task/updateAll")
    Call<List<Task>> addAll(@Body List<Task> tasks);
    @GET("/api/task/{id}")
    Call<Task> getById(@Path("id") Integer id);
}
