package es.tta.ejemploclase;

import android.content.Context;
import android.provider.Settings;
import android.content.Intent;
import android.graphics.Color;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestActivity extends AppCompatActivity
{
    //private String advise = "<h1>My First Heading</h1> <p>My first paragraph.</p>";
    private String advise = "http://www.google.com";
    private RestClient restC;
    private String pregunta;
    private JSONArray respuestas;
    private int respuesta_correcta;
    private TextView preguntaView;
    private RadioGroup opcionesView;
    private Context contexto = this;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //CONEXION A SERVIDOR
        Intent intent = getIntent();
        String auth = intent.getStringExtra(MenuActivity.EXTRA_AUTH);

        restC = new RestClient("http://u017633.ehu.eus:18080/AlumnoTta/rest/tta");
        restC.setAuthorization(auth);

        preguntaView = (TextView)findViewById(R.id.test_pregunta);
        opcionesView = (RadioGroup)findViewById(R.id.test_choices);

        new Thread(new Runnable() {
            public void run()
            {
                try
                {
                    JSONObject test = restC.getJson("getTest?id=1");
                    pregunta = test.getString("wording");
                    respuestas = test.getJSONArray("choices");


                    preguntaView.post(new Runnable() {
                        public void run() {
                            preguntaView.setText(pregunta);
                        }
                    });
                    opcionesView.post(new Runnable() {
                        public void run() {
                            for(int i=0; i<respuestas.length();i++)
                            {
                                try
                                {
                                    JSONObject respuesta = respuestas.getJSONObject(i);
                                    String respuesta_texto = respuesta.getString("answer");
                                    Boolean correcto = respuesta.getBoolean("correct");
                                    if(correcto == true)
                                    {
                                        respuesta_correcta = i + 1;
                                    }
                                    RadioButton radio = new RadioButton(contexto);
                                    radio.setText(respuesta_texto);
                                    opcionesView.addView(radio);
                                }
                                catch(Exception e)
                                {
                                }
                            }
                        }
                    });
                } catch (Exception e)
                {
                }
            }
        }).start();
    }


    public void comprobarRespuesta(View view)
    {
        int respuestaId = opcionesView.getCheckedRadioButtonId();
        RadioButton r = (RadioButton)findViewById(respuestaId);
        if(respuestaId == respuesta_correcta)
        {
            //Toast.makeText(this, "ID ES " + respuestaId + " Y CORRECTO ES " + respuesta_correcta, Toast.LENGTH_SHORT).show();
            r.setBackgroundColor(Color.GREEN);
        }
        else
        {
            //Toast.makeText(this, "ID ES " + respuestaId + " Y CORRECTO ES " + respuesta_correcta, Toast.LENGTH_SHORT).show();
            r.setBackgroundColor(Color.RED);
        }

    }


    //private void showHtml(String advise)
    public void showHtml(View view)//SUSTITUIR POR LA DE ARRIBA ¡OJO! Tiene que ser public
    {
        if(advise.substring(0,10).contains("://"))
        {
            //Toast.makeText(this, "ENTRO EN PRIMER CASO", Toast.LENGTH_SHORT).show();
            Uri uri =Uri.parse(advise);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        else
        {
            //Toast.makeText(this, "ENTRO EN SEGUNDO CASO", Toast.LENGTH_SHORT).show();
            WebView web = new WebView(this);
            web.loadData(advise, "text/html", null);
            web.setBackgroundColor(Color.TRANSPARENT);
            web.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            LinearLayout layout = (LinearLayout)findViewById(R.id.audio_layout);
            layout.addView(web);
        }
    }

    //private void showVideo(String advise)
    public void showVideo(View view)
    {
        VideoView video = new VideoView(this);
        //video.setVideoURI(Uri.parse(advise));
        //video.setVideoURI(Uri.parse("http://www.semanticdevlab.com/abc.mp4"));
        video.setVideoURI(Uri.parse("http://u017633.ehu.eus:18080/static/AndroidManifest.mp4"));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        video.setLayoutParams(params);

        MediaController controller = new MediaController(this)
        {
            @Override
            public void hide()
            {}

            @Override
            public boolean dispatchKeyEvent(KeyEvent event)
            {
                if(event.getKeyCode()==KeyEvent.KEYCODE_BACK)
                {
                    finish();
                }
                return super.dispatchKeyEvent(event);
            }
        };
        controller.setAnchorView(video);
        video.setMediaController(controller);

        LinearLayout layout = (LinearLayout)findViewById(R.id.audio_layout);
        layout.addView(video);
        video.start();
    }

    public void playAudio(View view)
    {
        Runnable run = new Runnable() {
            @Override
            public void run() {

            }
        };
        LinearLayout layout_audio = (LinearLayout)findViewById(R.id.audio_layout);
        AudioPlayer ap = new AudioPlayer(layout_audio, run);
        try
        {
            ap.setAudioUri(Settings.System.DEFAULT_RINGTONE_URI);
        }
        catch(Exception e)
        {

        }
    }

}
