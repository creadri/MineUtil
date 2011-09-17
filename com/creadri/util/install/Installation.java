package com.creadri.util.install;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.bukkit.plugin.java.JavaPlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author creadri
 */
public class Installation {

    static public boolean checkInstallation(JavaPlugin plugin, Properties config, Logger log) {
        boolean ret = true;

        String pluginName = plugin.getDescription().getName();

        byte buffer[] = new byte[512];

        Iterator<Entry<Object, Object>> it = config.entrySet().iterator();

        while (it.hasNext()) {
            Entry<Object, Object> entry = it.next();

            if (!(entry.getKey() instanceof String) || !(entry.getValue() instanceof String)) {
                continue;
            }

            String path = (String) entry.getKey();
            String url = (String) entry.getValue();

            File file = new File(path);

            if (!file.exists()) {
                // make folders
                file.getParentFile().mkdirs();
                DataInputStream dis = null;
                DataOutputStream dos = null;
                try {
                    // get the url
                    URL fileToGet = new URL(url);

                    //net input
                    dis = new DataInputStream(fileToGet.openStream());

                    //file output
                    dos = new DataOutputStream(new FileOutputStream(file));
                    int size;

                    while ((size = dis.read(buffer)) > 0) {
                        dos.write(buffer, 0, size);
                    }

                    log.info(pluginName + " : Downloaded default file " + path);

                } catch (IOException ex) {
                    log.warning(pluginName + " : could not download " + path);
                    ret = false;
                } finally {
                    try {
                        if (dis != null) {
                            dis.close();
                        }
                        if (dos != null) {
                            dos.close();
                        }
                    } catch (IOException ex) {
                        log.warning(pluginName + " : could not close streams for " + path);
                    }
                }
            }
        }

        return ret;
    }

    static public void checkUpdate(JavaPlugin plugin, URL xmlPluginInfo, Logger log) {

        String pluginName = plugin.getDescription().getName();
        String pluginVersion = plugin.getDescription().getVersion();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream is = xmlPluginInfo.openStream();
            Document doc = db.parse(is);
            is.close();

            NodeList pluginList = doc.getElementsByTagName("plugin");

            int imax = pluginList.getLength();
            for (int i = 0; i < imax; i++) {
                Node node = pluginList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = getElementChildValue("name", element);
                    String version = getElementChildValue("version", element);
                    String link = getElementChildValue("link", element);

                    if (name.equalsIgnoreCase(pluginName)) {
                        if (compareVersion(pluginVersion, version) > 0) {
                            log.info("[" + pluginName + "] NEW VERSION IS AVAILABLE AT " + link);
                        }
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.warning("[" + pluginName + "] could not check update");
        }
    }

    static private String getElementChildValue(String tagName, Element element) {
        NodeList childs = element.getElementsByTagName(tagName);

        if (childs.getLength() == 0) {
            return null;
        }

        return childs.item(0).getNodeValue();
    }
    
    static private int compareVersion(String currentVersion, String newVersion) {

        String cvSplit[] = currentVersion.split("\\D+");
        String nvSplit[] = newVersion.split("\\D+");

        int imax = Math.min(cvSplit.length, nvSplit.length);

        for (int i = 0; i < imax; i++) {

            int cvNumber = 0;
            int nvNumber = 0;

            if (i < cvSplit.length) {
                cvNumber = Integer.parseInt(cvSplit[i]);
            }

            if (i < nvSplit.length) {
                nvNumber = Integer.parseInt(nvSplit[i]);
            }

            if (nvNumber > cvNumber) {
                return 1;
            } else if (nvNumber < cvNumber) {
                return -1;
            }
        }

        return 0;
    }
}
