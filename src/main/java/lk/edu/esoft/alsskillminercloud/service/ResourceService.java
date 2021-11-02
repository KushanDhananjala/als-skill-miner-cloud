package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.ResourceDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResourceService {

    String saveResourceFile(MultipartFile file, String teacher) throws Exception;

    boolean saveResource(ResourceDTO resourceDTO) throws Exception;

    ResourceDTO getResource(Long id) throws Exception;

    List<ResourceDTO> getResourcesByTeacher(Long teacherId) throws Exception;

    List<ResourceDTO> getAllResources() throws Exception;

    boolean updateResource(ResourceDTO resourceDTO) throws Exception;

    ResponseEntity<InputStreamResource> downloadResource(Long id) throws Exception;

}
