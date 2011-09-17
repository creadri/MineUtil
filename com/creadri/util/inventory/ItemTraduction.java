package com.creadri.util.inventory;

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
        
        Properties defaults = new Properties();
        Material materials[] = Material.values();
        for (int i = 0; i < materials.length; i++) {
            int id = materials[i].getId();
            String name = materials[i].name().replaceAll("_", " ");
            String capitalLetter = name.substring(0, 1).toUpperCase();
            String rest = name.substring(1).toLowerCase();
            
            defaults.put(id + ".0", capitalLetter + rest);
        }
        
        properties = new Properties(defaults);
  
        properties.load(new FileInputStream(file));
    }
    
    public static String getTraduction(int ressourceId, short damageId) {
        return properties.getProperty(ressourceId + "." + damageId, Material.getMaterial(ressourceId).name());
    }
}
