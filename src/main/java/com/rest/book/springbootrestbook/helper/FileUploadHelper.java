package com.rest.book.springbootrestbook.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

    // for static file uploading
    // public final String UPLOAD_DIR =
    // "F:\\SpringBoot\\springbootrestbook\\src\\main\\resources\\static\\image";

    // dynamic file uploading easy way
    public final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();

    // created constructor to throw exception or to handle the exception for
    // classpathresource
    public FileUploadHelper() throws IOException {
    }

    public boolean uploadFile(MultipartFile file) {

        boolean flag = false;
        try {
            // InputStream inputStream = file.getInputStream();

            // byte data[] = new byte[inputStream.available()];
            // inputStream.read(data);

            // // write
            // FileOutputStream outputStream = new FileOutputStream(
            // UPLOAD_DIR + File.separator + file.getOriginalFilename());
            // outputStream.write(data);
            // outputStream.flush();
            // outputStream.close();
            // inputStream.close();

            // ..................OR.....................

            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
