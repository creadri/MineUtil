/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creadri.util;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.Configuration;

/**
 *
 * @author creadri
 */
public class Messages {
    Configuration configuration;
    JavaPlugin plugin;

    public Messages(JavaPlugin plugin, Configuration configuration) {
        this.configuration = configuration;
        this.plugin = plugin;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    
    public void sendPlayerMessage(Player player, String configNode, Object... values) {
        if (player == null) {
            return;
        }

        String msg = configuration.getString(configNode);

        if (msg == null || msg.isEmpty()) {
            msg = configNode + " is not set. Check configuration";
        }

        if (values != null) {

            for (int j = 0; j < values.length; j++) {
                String fieldName = "%" + j;

                msg = msg.replaceFirst(fieldName, values[j].toString());
            }
        }

        player.sendMessage(msg);
    }
    
    public void sendPlayerMessage(String player, String configNode, Object... values) {
        if (player == null) {
            return;
        }

        String msg = configuration.getString(configNode);

        if (msg == null || msg.isEmpty()) {
            msg = configNode + " is not set. Check configuration";
        }

        if (values != null) {

            for (int j = 0; j < values.length; j++) {
                String fieldName = "%" + j;

                msg = msg.replaceFirst(fieldName, values[j].toString());
            }
        }
        
        

        Player p = plugin.getServer().getPlayer(player);
        
        if (p != null) {
            p.sendMessage(msg);
        }
    }
}
