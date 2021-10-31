package lk.edu.esoft.alsskillminercloud.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileImageService {

    void storeUserImage(MultipartFile file, String userName) throws Exception;

    void storeAdminImage(MultipartFile file, String userName) throws Exception;

    void storeTeacherImage(MultipartFile file, String userName) throws Exception;

    void storeQuestionImages(MultipartFile file, long id) throws Exception;

    void storeAnswerImages(MultipartFile file, long id) throws Exception;

    ResponseEntity<InputStreamResource> download(String path) throws Exception;

}
