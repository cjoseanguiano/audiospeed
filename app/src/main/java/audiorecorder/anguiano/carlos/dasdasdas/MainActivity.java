package audiorecorder.anguiano.carlos.dasdasdas;

import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(this, R.raw.mpthreetest);

        setPlayPauseButton();
        setSpeedOptions();

    }

    // speed values displayed in the spinner
    private String[] getSpeedStrings() {
        return new String[]{"1.0", "1.2", "1.4", "1.6", "1.8", "2.0"};
    }

    private void setSpeedOptions() {
        final Spinner speedOptions = (Spinner) findViewById(R.id.speedOptions);
        String[] speeds = getSpeedStrings();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, speeds);
        speedOptions.setAdapter(arrayAdapter);

        // change player playback speed if a speed is selected
        speedOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (player != null) {
                    float selectedSpeed = Float.parseFloat(
                            speedOptions.getItemAtPosition(i).toString());

                    changeplayerSpeed(selectedSpeed);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void changeplayerSpeed(float speed) {
        // this checks on API 23 and up
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (player.isPlaying()) {
                player.setPlaybackParams(player.getPlaybackParams().setSpeed(speed));
            } else {
                player.setPlaybackParams(player.getPlaybackParams().setSpeed(speed));
                player.pause();
            }
        }
    }

    private void setPlayPauseButton() {
        final Button playPauseButton = (Button) findViewById(R.id.buttonPlayPause);
        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.isPlaying()) {
                    player.pause();
                } else {
                    player.start();
                }
            }
        });
    }
}
