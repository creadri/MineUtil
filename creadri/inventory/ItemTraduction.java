package creadri.inventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.bukkit.Material;

/**
 *
 * @author creadri
 */
public class ItemTraduction {
    private static Properties properties;

    public static void loadFile(File file) throws FileNotFoundException, IOException {
        if (properties == null) {
            properties = new Properties();
        } else {
            properties.clear();
        }
        
        properties.load(new FileInputStream(file));
    }
    
    public static String getTraduction(int ressourceId, short damageId) {
        return properties.getProperty(ressourceId + "." + damageId, Material.getMaterial(ressourceId).name());
    }
}
