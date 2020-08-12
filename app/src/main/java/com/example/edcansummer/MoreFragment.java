package com.example.edcansummer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.edcansummer.databinding.FragmentMemoBinding;
import com.example.edcansummer.databinding.FragmentMoreBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MoreFragment extends Fragment {
    public static MoreFragment newInstance() {
        return new MoreFragment();
    }

    private Context mContext;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
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
        //로그아웃
        binding.btnMoreLogout.setOnClickListener(view -> logout());

        //비밀 번호 재설정-.
        binding.btnMorePw.setOnClickListener(view -> {
            firebaseAuth.sendPasswordResetEmail(UserCache.getUser(mContext).getEmail());
            Toast.makeText(mContext, "비밀번호 재설정 메일이 도착되었습니다", Toast.LENGTH_SHORT).show();
            logout();
        });

        //탈퇴
        binding.btnMoreDelete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("회원 탈퇴");
            builder.setMessage("정말로 탈퇴 하시겠습니까?");
            builder.setPositiveButton("탈퇴하기", (dialogInterface, i) -> {
                firebaseAuth.getCurrentUser().delete().addOnSuccessListener(runnable -> {
                        firebaseFirestore
                                .collection("users")
                                .document(UserCache.getUser(mContext).getEmail())
                                .delete()
                                .addOnSuccessListener(runnable1 -> {
                                    Toast.makeText(mContext, "정상적으로 탈퇴 되었습니다", Toast.LENGTH_SHORT).show();
                                    UserCache.clear(mContext);
                                    startActivity(new Intent(mContext, LoginActivity.class));
                                    getActivity().finish();
                                });
                });
            });
            builder.setNegativeButton("최소", (dialogInterface, i) -> {
            });
            builder.show();
        });
        return binding.getRoot();
    }

    private void logout() {
        UserCache.clear(mContext);
        firebaseAuth.signOut();
        startActivity(new Intent(mContext, LoginActivity.class));
        getActivity().finish();
    }
}
