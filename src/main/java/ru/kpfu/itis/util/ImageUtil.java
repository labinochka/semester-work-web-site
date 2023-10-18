package ru.kpfu.itis.util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class ImageUtil {

    public static File makeFile(Part part) {
        try {
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            InputStream content = part.getInputStream();
            File file = new File(fileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[content.available()];
            content.read(buffer);
            outputStream.write(buffer);
            outputStream.close();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
