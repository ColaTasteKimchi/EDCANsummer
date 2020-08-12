package com.example.edcansummer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.edcansummer.databinding.FragmentMemoBinding;
import com.example.edcansummer.databinding.FragmentMoreBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MoreFragment extends Fragment {
    public static MoreFragment newInstance(){
        return new MoreFragment();
    }
    private Context mContext;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FragmentMoreBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_more, container, false);
        binding.setUser(UserCache.getUser(mContext));
        binding.btnMoreLogout.setOnClickListener(view -> {
            UserCache.clear(mContext);
            firebaseAuth.signOut();
            startActivity(new Intent(mContext, LoginActivity.class));

        });
        return binding.getRoot();
    }
}
