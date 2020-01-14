package simple.engine.engine.loaders;

import org.apache.commons.io.FilenameUtils;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.HashMap;

public class SoundLoader extends Loader<Clip> {

    private HashMap<String, Clip> clips = new HashMap<>();

    @Override
    public void preload(String folder) {
        getResourceFiles(folder).stream().filter(s -> !FilenameUtils.getExtension(s).isEmpty()).forEach(s -> {
            try {
                Clip clip;
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(SoundLoader.class.getResource(folder.concat(s)));
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                clips.put(s, clip);
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
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
