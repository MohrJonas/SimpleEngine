package simple.engine.engine.loaders;

import org.apache.commons.io.FilenameUtils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;

public class SoundLoader extends Loader<Clip> {

    private HashMap<String, Clip> clips = new HashMap<>();

    @Override
    public void preload(Stream<Path> path) {
        path.filter(p -> !FilenameUtils.getExtension(p.getFileName().toString()).isEmpty()).forEach(p -> {
            try {
                Clip clip;
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(String.valueOf(p)));
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                clips.put(p.toString(), clip);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        });
        System.out.println(clips);
    }

    @Override
    public Clip get(String name) {
        if (clips.containsKey(name)) return clips.get(name);
        throw new NullPointerException(name.concat(" isn't an audio clip"));
    }
}
