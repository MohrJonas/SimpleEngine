package simple.engine.engine.util;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class GuiUtils {

    public static final Font FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    private static final AffineTransform AFFINE_TRANSFORM = new AffineTransform();
    private static final FontRenderContext RENDER_CONTEXT = new FontRenderContext(AFFINE_TRANSFORM, true, true);

    public static int getStringWidth(String s) {
        return (int) (FONT.getStringBounds(s, RENDER_CONTEXT).getWidth());
    }

    public static int getStringHeight(String s) {
        return (int) (FONT.getStringBounds(s, RENDER_CONTEXT).getHeight());
    }

}
