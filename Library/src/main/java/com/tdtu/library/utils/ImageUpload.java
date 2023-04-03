package com.tdtu.library.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUpload {
    private final String UPLOAD_FOLDER;

    public ImageUpload() {
        String userDirectory = System.getProperty("user.dir");
        UPLOAD_FOLDER = userDirectory + "\\Admin\\src\\main\\resources\\static\\img\\products";
    }

    public boolean uploadImage(MultipartFile productImage) {
        boolean isUploaded = false;
        try {
            Files.copy(productImage.getInputStream(),
                    Paths.get(UPLOAD_FOLDER + File.separator, productImage.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            isUploaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUploaded;
    }

    public boolean isExistedImage(MultipartFile productImage) {
        boolean isExisted = false;
        try {
            File file = new File(UPLOAD_FOLDER + "\\" + productImage.getOriginalFilename());
            isExisted = file.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExisted;
    }
}
