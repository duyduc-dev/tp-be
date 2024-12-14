package com.learn.techplatform.controllers;

import com.learn.techplatform.cloudinary.CloudinaryService;
import com.learn.techplatform.cloudinary.modal.CloudinaryUploadResponse;
import com.learn.techplatform.common.constants.ApiPath;
import com.learn.techplatform.common.enums.UploadFileType;
import com.learn.techplatform.common.restfullApi.RestAPIResponse;
import com.learn.techplatform.controllers.models.response.UploadFileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(ApiPath.UPLOAD_API)
public class UploadController extends AbstractBaseController {

    @Autowired
    CloudinaryService cloudinaryService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<RestAPIResponse<Object>> upload(
            @RequestPart("image") MultipartFile file,
            @RequestParam("type") UploadFileType uploadFileType
    ) {
        CloudinaryUploadResponse response = cloudinaryService.uploadImage(file, uploadFileType.name().toLowerCase());
        String imageUrl = response.getSecureUrl();

        return this.responseUtil.successResponse(UploadFileResponse.builder()
                .url(imageUrl)
                .publicId(response.getPublicId())
                .type(uploadFileType)
                .build());
    }
}
