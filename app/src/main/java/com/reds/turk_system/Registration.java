package com.reds.turk_system;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registration extends AppCompatActivity {
    private EditText un,em,ps,ps2;
    private Button reg;
    public Registration(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_registration);
        un = (EditText) findViewById(R.id.user_in);
        em = (EditText) findViewById(R.id.email_in);
        ps = (EditText) findViewById(R.id.pass_in);
        ps2 = (EditText) findViewById(R.id.pass2_in);

        reg = (Button) findViewById(R.id.Register_Acc);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RegistrationCheck(reg.getContext()).execute(em.getText().toString(),ps.getText().toString());
            }
        });



    }

}
