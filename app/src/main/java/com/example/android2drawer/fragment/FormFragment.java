package com.example.android2drawer.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android2drawer.R;
import com.example.android2drawer.databinding.FragmentFormBinding;
import com.example.android2drawer.model.TaskModel;

import org.jetbrains.annotations.NotNull;


public class FormFragment extends Fragment {

    private EditText etTitle, etDescription;
    private Button btnSave;
    private FragmentFormBinding binding;
    private NavController navController;
    TaskModel taskModel;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FormFragment() {

    }

    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSave();
            }
        });
    }

    private void btnSave() {
        String title = binding.etTitle.getText().toString();
        String description = binding.etDescription.getText().toString();
        taskModel = new TaskModel(title,description);
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", taskModel);
        getParentFragmentManager().setFragmentResult("task", bundle);
        navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content_main);
        navController.navigateUp();
    }
}