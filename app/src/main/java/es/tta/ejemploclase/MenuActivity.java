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
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView tv1=(TextView)findViewById(R.id.menu_login);
        tv1.setText("TITULO1");

        TextView tv2=(TextView)findViewById(R.id.menu_login2);
        tv2.setText("TITULO2");
    }

    public void test(View view)
    {
        Intent intent = new Intent(this,TestActivity.class);
        startActivity(intent);
    }

    public void ejercicio(View view)
    {
        Intent intent = new Intent(this,EjercicioActivity.class);
        startActivity(intent);
    }

    public void seguimiento(View view)
    {
        Intent intent = new Intent(this,SeguimientoActivity.class);
        startActivity(intent);
    }
}