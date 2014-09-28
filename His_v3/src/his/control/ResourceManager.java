
package his.control;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFont;

/**
 *
 * @author Patrick
 */
public class ResourceManager {
    
    private static Map<String, GlyphFont> gfs;
    
    public ResourceManager() {
        gfs = new HashMap<>();
    }
    
    public static void registerGlyphFont(String fontname, GlyphFont gf) {
        gfs.put(fontname, gf);
    }
    
    public static Glyph getGlyph(String fontname, char c) {
        return gfs.get(fontname).create(c);
    }
    
    public static Glyph getGlyph(String fontname, char c, Color color, double size) {
        return gfs.get(fontname).create(c).color(color).size(size);
    }
    
    public static Glyph getGlyph(String fontname, String name) {
        return gfs.get(fontname).create(name);
    }
    
    public static Glyph getGlyph(String fontname, String name, Color color, double size) {
        return gfs.get(fontname).create(name).color(color).size(size);
    }

}
