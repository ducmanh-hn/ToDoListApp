package com.todolist;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.todolist.API.APIService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    ListView taskLV;
    List<Task> tasks;
    TaskAdapter taskAdapter;
    ImageButton btnAdd;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("To Do List");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        match();

        btnAdd.setOnClickListener(v->{
            dialogAdd();
        });

    }

    private void match() {
        taskLV = this.findViewById(R.id.taskLV);
        btnAdd = this.findViewById(R.id.btnAdd);
        requestQueue = Volley.newRequestQueue(this);

//        tasks.add(new Task(1,"task 05", false));
//        tasks.add(new Task(1,"task 06", true));
    }
    public void getTasks(){
        tasks = new ArrayList<>();
        String url = Constant.URL_GET_TASKS;

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i=0; i< jsonArray.length(); i++){
                            try {
                                JSONObject object = jsonArray.getJSONObject(i);
                                int id = object.getInt("id");
                                String content = object.getString("content");
                                boolean status = object.getBoolean("status");
                                Task task = new Task(id, content, status);
                                tasks.add(task);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        taskAdapter = new TaskAdapter(MainActivity.this, R.layout.task_layout, tasks);
                        taskLV.setAdapter(taskAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                });
        requestQueue.add(arrayRequest);
    }
    public void dialogAdd(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_task_dialog);
        dialog.show();
        EditText taskContent = dialog.findViewById(R.id.txtContent);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        Button btnExit = dialog.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v->{
            dialog.dismiss();
        });
        btnAdd.setOnClickListener(v->{
            String newTaskContent = taskContent.getText().toString();
            if(newTaskContent.length() == 0){
                Toast.makeText(MainActivity.this, "Bạn chưa nhập nội dung công việc.", Toast.LENGTH_LONG).show();
            }else{
                Task newTask = new Task(newTaskContent, false);
                if(!tasks.contains(newTask)){
                    tasks.add(newTask);
                    taskAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(MainActivity.this, "Công việc này đã tồn tại.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void dialogEdit(String curentTaskContent){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.edit_task_dialog);
        dialog.show();
        EditText taskContent = dialog.findViewById(R.id.txtContent);
        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);
        Button btnRemove = dialog.findViewById(R.id.btnRemove);

        taskContent.setText(curentTaskContent);
        Task currentTask = new Task(curentTaskContent, false);

        btnRemove.setOnClickListener(v->{
            Dialog dialogConfirm = new Dialog(this);
            dialogConfirm.setContentView(R.layout.confirm_remove_dialog);
            dialogConfirm.show();
            Button btnConfirm = dialogConfirm.findViewById(R.id.btnConfirm);
            Button btnExit = dialogConfirm.findViewById(R.id.btnExit);
            btnExit.setOnClickListener(v2->{dialogConfirm.dismiss();});
            btnConfirm.setOnClickListener(v3->{
                tasks.remove(currentTask);
                Toast.makeText(this, "Đã xóa thành công", Toast.LENGTH_LONG).show();
                taskAdapter.notifyDataSetChanged();
                dialogConfirm.dismiss();
                dialog.dismiss();
            });
        });
        btnUpdate.setOnClickListener(v->{
            String newTaskContent = taskContent.getText().toString();
            Task taskUpdate = new Task(newTaskContent, false);
            if(tasks.contains(taskUpdate)){
                Toast.makeText(MainActivity.this, "Công việc này đã tồn tại.", Toast.LENGTH_LONG).show();
                return;
            }
            int index = tasks.indexOf(currentTask);
            tasks.get(index).setContent(newTaskContent);
            Toast.makeText(this, "Đã cập nhật thành công", Toast.LENGTH_LONG).show();
            taskAdapter.notifyDataSetChanged();
            dialog.dismiss();
        });
    }

    protected void updateTasks() {
        Log.d("qwe", "test: ");
        APIService.apiService.addAll(tasks).enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, retrofit2.Response<List<Task>> response) {
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable throwable) {

            }
        });
    }

    @Override
    protected void onStop() {
        updateTasks();
        super.onStop();
    }

    @Override
    protected void onStart() {
        getTasks();
        super.onStart();
    }



}