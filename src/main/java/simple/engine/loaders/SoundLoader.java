package simple.engine.loaders;

import simple.engine.util.ColorOut;
import simple.engine.util.Logger;

import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.Path;
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
        Logger.log(ColorOut.asString(String.format("Loaded %d sound(s) from files.", content.values().size()), ColorOut.GREEN), "resources");
    }

    @Override
    public Clip get(String name) {
        if (content.containsKey(name)) return content.get(name);
        throw new NullPointerException(name.concat(" isn't an audio clip"));
    }
}
