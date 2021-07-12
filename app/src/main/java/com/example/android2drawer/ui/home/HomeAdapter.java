package com.example.android2drawer.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android2drawer.R;
import com.example.android2drawer.model.TaskModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private List<TaskModel>list = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public HomeAdapter.HomeHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeAdapter.HomeHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addInfo(TaskModel taskModel){
        list.add(taskModel);
        notifyDataSetChanged();
    }


    public class HomeHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription;
        public HomeHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }

        public void bind(TaskModel taskModel) {
            tvTitle.setText(taskModel.getTitle());
            tvDescription.setText(taskModel.getDescription());
        }
    }
}
