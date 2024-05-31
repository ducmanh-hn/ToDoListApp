package com.todolist;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.List;

public class TaskAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;

    private List<Task> tasks;

    public TaskAdapter(MainActivity context, int layout, List<Task> tasks) {
        this.context = context;
        this.layout = layout;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        CheckBox checkBox;
        TextView txtContent;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder = new ViewHolder();
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.todoListCheckbox);
            viewHolder.txtContent = (TextView) convertView.findViewById(R.id.txtContent);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Task task = tasks.get(position);

        viewHolder.checkBox.setChecked(task.isCheck());
        viewHolder.txtContent.setText(task.getContent());

        viewHolder.txtContent.setOnClickListener(v->{
            context.dialogEdit(task.getContent());
        });
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setCheck(isChecked);
                viewHolder.checkBox.setChecked(isChecked);
                Log.d("mmmmm", String.valueOf(task.isCheck()) + "  "+ String.valueOf(tasks.get(position).isCheck()));
            }
        });
        if(viewHolder.checkBox.isChecked())
        {
            viewHolder.checkBox.setChecked(true);
        }
        else {
            viewHolder.checkBox.setChecked(false);
        }
        return convertView;
    }
}
