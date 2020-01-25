package simple.engine.engine.util;

import com.madgag.gif.fmsware.GifDecoder;

import java.awt.image.BufferedImage;

public class Gif {

    private final BufferedImage[] frames;
    private final GifDecoder decoder;
    private int i = 0;
    private boolean repeating;

    public Gif(GifDecoder decoder) {
        frames = new BufferedImage[decoder.getFrameCount()];
        this.decoder = decoder;
        for (int i = 0; i < decoder.getFrameCount(); i++) {
            frames[i] = decoder.getFrame(i);
        }
    }

    public int getDelay(int i) {
        return decoder.getDelay(i);
    }

    public int getDelay() {
        return decoder.getDelay(0);
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    public BufferedImage next() {
        return getFrame(i++);
    }

    private BufferedImage getFrame(int i) {
        if (i < 0) return frames[0];
        if (!repeating) {
            if (i >= frames.length) return frames[frames.length - 1];
        } else {
            if (i >= frames.length) return frames[i - ((int) Math.floor((float) i / frames.length) * frames.length)];
        }
        return frames[i];
    }

    public BufferedImage get(int i) {
        return getFrame(i);
    }

}
