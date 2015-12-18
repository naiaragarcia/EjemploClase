package es.tta.ejemploclase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        RadioGroup group = (RadioGroup)findViewById(R.id.test_choices);
        for(int i=0;i<5;i++)
        {
            RadioButton radio = new RadioButton(this);
            radio.setText("TEXTO"+i);
            group.addView(radio);
        }
    }

    public void enviar(View view)
    {

    }

}
