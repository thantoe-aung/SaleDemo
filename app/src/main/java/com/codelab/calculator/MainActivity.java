package com.codelab.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalculate=findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText mass=findViewById(R.id.edtMass);
                TextInputEditText acc=findViewById(R.id.edtAcc);
                TextInputEditText force=findViewById(R.id.edtForce);
                Double result=Double.parseDouble(mass.getText().toString()) * Double.parseDouble(acc.getText().toString());
                force.setText(result.toString());
            }
        });

    }
}