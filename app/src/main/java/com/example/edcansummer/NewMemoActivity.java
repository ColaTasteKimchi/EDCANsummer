package com.example.edcansummer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.example.edcansummer.MemoModel;
import com.example.edcansummer.UserCache;
import com.example.edcansummer.databinding.ActivityNewMemoBinding;
import com.example.edcansummer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewMemoActivity extends AppCompatActivity {

    private ActivityNewMemoBinding binding;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_memo);
        binding.setMemo(getIntent().getStringExtra("memo_text"));

        binding.toolbarNewMemo.setNavigationOnClickListener(view -> finish());

        binding.btnNewMemoUpload.setOnClickListener(view -> {
            if (binding.getMemo().isEmpty()) {
                Toast.makeText(this, "빈칸을 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            MemoModel memoModel = new MemoModel();
            memoModel.setEmail(UserCache.getUser(this).getEmail());
            memoModel.setText(binding.getMemo());
            memoModel.setTime(getTime());
           if(getIntent().getBooleanExtra("is_edit", false)){

           }
           else{

           }
            firebaseFirestore
                    .collection("memo")
                    .document()
                    .set(memoModel)
                    .addOnSuccessListener(runnable -> {
                        Toast.makeText(this, "정상적으로 등록 되었습니다", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

    }

    private String getTime(){
        return new SimpleDateFormat("yyyy/MM/dd hh:mm aa", Locale.ENGLISH).format( new Date());
    }
}