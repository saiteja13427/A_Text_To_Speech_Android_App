package com.example.dell.text_to_speech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import static android.speech.tts.Voice.QUALITY_HIGH;
import static android.speech.tts.Voice.QUALITY_VERY_HIGH;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private EditText input;
    private Button speakButton;
    private TextToSpeech TTS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TTS =new TextToSpeech(this,this);
        input = findViewById(R.id.input);
        speakButton =  findViewById(R.id.speakButton);
        speakButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                speakIt();


            }
        });

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = TTS.setLanguage(Locale.getDefault());


            TTS.setPitch(1);
            TTS.setSpeechRate(1);


            if (result == TTS.LANG_MISSING_DATA || result == TTS.LANG_NOT_SUPPORTED) {

                Log.e("ERROR", "Initiallization Failed");

            } else {
                speakButton.setEnabled(true);
                speakIt();

            }
        }

    }
    private void speakIt(){
            String message = input.getText().toString();
            TTS.speak(message, TextToSpeech.QUEUE_FLUSH,null, null  );
            input.setText("");
        }

}
