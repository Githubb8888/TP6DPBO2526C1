import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    private Clip clip;

    public Sound(String path) {
        try {
            URL url = getClass().getResource(path);
            AudioInputStream in = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(in);
        } catch (Exception e) {
            System.out.println("Не удалось загрузить звук: " + path);
        }
    }

    public void play() {
        if (clip == null) return;
        clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        if (clip == null) return;
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) clip.stop();
    }
}