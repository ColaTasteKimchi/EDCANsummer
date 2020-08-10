package com.example.edcansummer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.example.edcansummer.databinding.ActivityLoginBinding;
import com.example.edcansummer.databinding.ActivityRejisterBinding;

public class RejisterActivity extends AppCompatActivity {
    private ActivityRejisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rejister);
        binding.setName("");
        binding.setEmail("");
        binding.setPw("");
        binding.setPwcheck("");
        binding.btnRegiSignup.setOnClickListener(view -> {
            register(binding.getName(), binding.getEmail(), binding.getPw(), binding.getPwcheck())
        });
    }

    private void register(String name, String email, String pw, String pwcheck) {
        if (name.isEmpty() || email.isEmpty() || pw.isEmpty() || pwcheck.isEmpty()) {
            Toast.makeText(this, "빈칸을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pw.equals(pwcheck)){
            Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}