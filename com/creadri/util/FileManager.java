package com.creadri.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author creadri
 */
public class FileManager {

    private static final int BUFFERSIZE = 512;

    public static void copyDefaultRessources(File folderDestination, String ressourcePackageFullLocation, String... ressourceFile) throws IOException {
        
        if (!ressourcePackageFullLocation.endsWith("/")) {
            ressourcePackageFullLocation += "/";
        }
        
        int imax = ressourceFile.length;
        for (int i = 0; i < imax; i++) {
            String completeLocation = ressourcePackageFullLocation + ressourceFile[i];
            
            copyDefaultRessource(folderDestination, completeLocation, ressourceFile[i]);
        }
        
    }

    public static void copyDefaultRessource(File fileDestination, String ressourceFullLocation, String outputFileName) throws IOException {

        if (ressourceFullLocation.endsWith(".zip") || ressourceFullLocation.endsWith(".ZIP")) {
            InputStream is = FileManager.class.getResourceAsStream(ressourceFullLocation);
            extractZipFile(is, fileDestination);
            return;
        }
        
        File dest = new File(fileDestination, outputFileName);
        if (!dest.exists()) {
            dest.createNewFile();
        }

        InputStream is = FileManager.class.getResourceAsStream(ressourceFullLocation);
        FileOutputStream fos = new FileOutputStream(dest);

        byte[] buffer = new byte[BUFFERSIZE];

        int read;
        while ((read = is.read(buffer, 0, BUFFERSIZE)) > 0) {
            fos.write(buffer, 0, read);
        }
        
        is.close();
        fos.close();
    }

    public static void extractZipFile(InputStream input, File folderDestination) throws IOException {

        ZipInputStream zis = new ZipInputStream(input);

        ZipEntry ze;

        byte[] buffer = new byte[BUFFERSIZE];

        while ((ze = zis.getNextEntry()) != null) {

            File f = new File(folderDestination, ze.getName());

            if (ze.isDirectory()) {

                f.mkdirs();
            } else {

                if (!f.exists()) {
                    f.createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(f);

                int read;
                while ((read = zis.read(buffer, 0, BUFFERSIZE)) > 0) {
                    fos.write(buffer, 0, read);
                }

                fos.close();
            }

            zis.closeEntry();
        }

        zis.close();
    }
}
