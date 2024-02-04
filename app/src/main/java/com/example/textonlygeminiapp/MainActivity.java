package com.example.textonlygeminiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextInputEditText queryEditText = findViewById(R.id.queryEditText);
        Button sendQueryButton = findViewById(R.id.sendPromptButton);
        TextView responseTextView = findViewById(R.id.modelResponseTextView);
        ProgressBar progressBar = findViewById(R.id.sendPromptProgressBar);

        sendQueryButton.setOnClickListener(v -> {
            GeminiPro model = new GeminiPro();

            String query = queryEditText.getText().toString();
            progressBar.setVisibility(View.VISIBLE);

            responseTextView.setText("");
            queryEditText.setText("");

            model.getResponse(query, new ResponseCallback() {
                @Override
                public void onResponse(String response) {
                    responseTextView.setText(response);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Throwable throwable) {
                    Toast.makeText(MainActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        });
    }
}