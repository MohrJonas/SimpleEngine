package simple.engine.engine.loaders;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

public class SoundLoader extends Loader<Clip> {

    private HashMap<String, Clip> clips = new HashMap<>();

    public Clip get(File file) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return clip;
    }

    @Override
    public void preload(String folder) {
        try {
            Files.walk(Paths.get(Objects.requireNonNull(this.getClass().getClassLoader().getResource(folder)).toURI())).filter(Files::isRegularFile).forEach(path -> {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream stream = AudioSystem.getAudioInputStream(path.toFile());
                    clip.open(stream);
                    clips.put(path.toFile().getName(), clip);
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(clips);
    }

    @Override
    public Clip get(String name) {
        return clips.get(name);
    }
}
