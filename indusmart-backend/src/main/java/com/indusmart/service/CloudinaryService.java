package com.indusmart.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    /**
     * Upload Image to Cloudinary
     */
    public String uploadImage(MultipartFile file) {

        try {

            Map<?, ?> uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "indusmart/products"
                    )
            );

            return uploadResult.get("secure_url").toString();

        } catch (IOException e) {

            throw new RuntimeException("Image Upload Failed", e);

        }
    }

    /**
     * Delete Image From Cloudinary
     */
    public String deleteImage(String publicId) {

        try {

            Map<?, ?> result = cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.emptyMap()
            );

            return result.get("result").toString();

        } catch (IOException e) {

            throw new RuntimeException("Image Delete Failed", e);

        }
    }
}