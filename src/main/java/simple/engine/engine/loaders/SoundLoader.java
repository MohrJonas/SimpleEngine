package simple.engine.engine.loaders;

import simple.engine.engine.util.ColorOut;

import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;

public class SoundLoader extends Loader<Clip> {

    @Override
    public void preload(Stream<Path> path) {
        path.forEach(p -> {
            try {
                Clip clip;
                AudioInputStream audioIn;
                audioIn = AudioSystem.getAudioInputStream(p.toFile());
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                content.put(p.toString(), clip);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });
        ColorOut.print(System.out, String.format("Loaded %d sounds from files.", content.values().size()), ColorOut.GREEN);
    }

    @Override
    public Clip get(String name) {
        if (content.containsKey(name)) return content.get(name);
        throw new NullPointerException(name.concat(" isn't an audio clip"));
    }
}
