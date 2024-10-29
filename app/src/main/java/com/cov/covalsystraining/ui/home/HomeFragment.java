package com.cov.covalsystraining.ui.home;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cov.covalsystraining.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences sp = getActivity().getSharedPreferences("mypref", Context.MODE_PRIVATE);

        String name= sp.getString("name","");
        String empid = sp.getString("empId","");
        String dept = sp.getString("department","");
        String manager = sp.getString("reportedBy","");
        String usertype = sp.getString("userType","");

        if(usertype.equals("A")){
            binding.empLayout.setVisibility(View.GONE);
            binding.adminLayout.setVisibility(View.VISIBLE);
        }else{
            binding.empLayout.setVisibility(View.VISIBLE);
            binding.adminLayout.setVisibility(View.GONE);
        }

        Log.e("TAG1", name);
        Log.e("TAG1", empid);
        Log.e("TAG1", dept);
        Log.e("TAG1", manager);
        Log.e("TAG1", usertype);

        binding.textname.setText(name);
        binding.textempid.setText(empid);
        binding.textgroup.setText(dept);
        binding.textmanager.setText(manager);
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}