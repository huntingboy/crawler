package com.nomad.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileStoreUtil {

    public static void fileStore(String fileName, String content) {
        try {
            String path = FileStoreUtil.class.getClassLoader().getResource("").getFile();
            File file = new File(path, fileName);
//            File file = new File("/tmp/response.html");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
