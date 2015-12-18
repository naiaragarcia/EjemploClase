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

public class MainActivity extends AppCompatActivity
{
    public final static String EXTRA_LOGIN = "es.tta.ejemplo1.login";
    public final static String EXTRA_PASSWD = "es.tta.ejemplo1.passwd";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void login(View view)
    {
        Intent intent = new Intent(this,MenuActivity.class);
        EditText editLogin = (EditText)findViewById(R.id.login);
        EditText editPasswd = (EditText)findViewById(R.id.passwd);
        intent.putExtra(EXTRA_LOGIN, editLogin.getText().toString());
        intent.putExtra(EXTRA_PASSWD, editPasswd.getText().toString());
        startActivity(intent);
    }
}
