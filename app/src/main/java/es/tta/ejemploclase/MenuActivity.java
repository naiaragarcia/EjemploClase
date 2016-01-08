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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MenuActivity extends AppCompatActivity
{
    public final static String EXTRA_TEST = "es.tta.ejemplo1.test";
    public final static String EXTRA_AUTH = "es.tta.ejemplo1.auth";

    private RestClient restC;
    private String dni, passwd;
    private TextView userView, lessonView;
    private JSONObject status;
    private String name, lessonNumber, lessonTitle;
    private int nextTest, nextExercise;
    private String auth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //CONEXION A SERVIDOR
        Intent intent = getIntent();
        dni = intent.getStringExtra(MainActivity.EXTRA_LOGIN);
        passwd = intent.getStringExtra(MainActivity.EXTRA_PASSWD);

        userView = (TextView)findViewById(R.id.menu_login);
        lessonView = (TextView)findViewById(R.id.menu_login2);

        restC = new RestClient("http://u017633.ehu.eus:18080/AlumnoTta/rest/tta");
        restC.setHttpBasicAuth(dni, passwd);
        auth = restC.getAuthorization();

        new Thread(new Runnable() {
            public void run()
            {
                try
                {
                    status = restC.getJson("getStatus?dni=" + dni);
                    name = status.getString("user");
                    lessonNumber = status.getString("lessonNumber");
                    lessonTitle = status.getString("lessonTitle");
                    nextTest = status.getInt("nextTest");
                    nextExercise = status.getInt("nextExercise");

                    userView.post(new Runnable()
                    {
                        public void run()
                        {
                            userView.setText("Bienvenido " + name);
                        }
                    });

                    lessonView.post(new Runnable()
                    {
                        public void run()
                        {
                            lessonView.setText("Leccion " + lessonNumber + ": " + lessonTitle);
                        }
                    });
                }
                catch (IOException e)
                {
                }
                catch (JSONException e)
                {
                }
            }
        }).start();
    }

    public void test(View view)
    {
        Intent intent = new Intent(this,TestActivity.class);
        intent.putExtra(EXTRA_AUTH, auth);
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