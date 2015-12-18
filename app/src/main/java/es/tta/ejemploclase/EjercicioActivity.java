package es.tta.ejemploclase;

        import android.content.Intent;
        import android.content.pm.PackageManager;
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
        import android.widget.Toast;

public class EjercicioActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio);
    }

    public void subirFichero(View view)
    {

    }

    public void sacarFoto(View view)
    {

    }

    public void grabarAudio(View view)
    {

    }

    public void grabarVideo(View view)
    {

    }

    public void sendPicture(View view)
    {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            Toast.makeText(this,R.string.no_camera, Toast)
        }
    }
}