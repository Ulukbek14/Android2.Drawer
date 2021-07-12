package com.example.android2drawer.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.android2drawer.R;
import com.example.android2drawer.databinding.FragmentHomeBinding;
import com.example.android2drawer.model.TaskModel;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private HomeAdapter taskAdapter;
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskAdapter = new HomeAdapter();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setAdapter();
        setResultListener();
        return root;
    }
    public void setAdapter(){
        binding.rv.setAdapter(taskAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }

    private void setResultListener() {
        getParentFragmentManager().setFragmentResultListener("task",
                getViewLifecycleOwner(),
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        TaskModel taskModel = (TaskModel) result.getSerializable("model");
                        if (taskModel != null){
                            taskAdapter.addInfo(taskModel);
                            taskAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}