package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.ResourceDTO;
import lk.edu.esoft.alsskillminercloud.entity.Resource;
import lk.edu.esoft.alsskillminercloud.entity.Teacher;
import lk.edu.esoft.alsskillminercloud.repository.ResourceRepository;
import lk.edu.esoft.alsskillminercloud.repository.TeacherRepository;
import lk.edu.esoft.alsskillminercloud.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ResourceServiceImpl implements ResourceService {

    private final String PDF = "/pdf/";
    private final ResourceRepository resourceRepository;
    private final TeacherRepository teacherRepository;
    @Value("${app.location.uploaded.resources}")
    private String LOCATION_UPLOADED_RESOURCES;

    @Override
    public String saveResourceFile(MultipartFile file, String teacher) throws Exception {
        try {
            String savePath = LOCATION_UPLOADED_RESOURCES + teacher + "\\" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Path path = Paths.get(savePath);
            Files.createDirectories(path);
            File f = new File(savePath + "\\" + file.getOriginalFilename());
            if (!f.exists()) {
                Files.copy(file.getInputStream(), path.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            }
            return savePath + "\\" + file.getOriginalFilename();
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveResource(ResourceDTO resourceDTO) throws Exception {
        Resource resource = copyPropertiesToResource(resourceDTO);
        resource.setLastUpdated(new Date());
        resourceRepository.save(resource);
        return true;
    }

    @Override
    public ResourceDTO getResource(Long id) throws Exception {
        Optional<Resource> optionalResource = resourceRepository.findById(id);
        if (!optionalResource.isPresent()) {
            throw new RuntimeException("Resource not found for id: " + id);
        }
        return copyPropertiesToResourceDTO(optionalResource.get());
    }

    @Override
    public List<ResourceDTO> getResourcesByTeacher(Long teacherId) throws Exception {

        if (teacherId == 0L) {
            return getAllResources();
        }

        return resourceRepository
                .findAllByTeacherAndExpireDateIsGreaterThanEqual(
                        teacherRepository.findById(teacherId).get(),
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .stream()
                .map(this::copyPropertiesToResourceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> getAllResources() throws Exception {
        return resourceRepository
                .findAllByExpireDateIsGreaterThanEqual(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .stream()
                .map(this::copyPropertiesToResourceDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateResource(ResourceDTO resourceDTO) throws Exception {
        Resource resource = copyPropertiesToResource(resourceDTO);
        resource.setLastUpdated(new Date());
        resourceRepository.save(resource);
        return true;
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadResource(Long id) throws Exception {
        try {
            Resource resource = resourceRepository.findById(id).get();

            File file = new File(resource.getResourceUrl().replace("/", "\\"));
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment;filename=" + file.getName())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())
                    .body(inputStreamResource);
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    private ResourceDTO copyPropertiesToResourceDTO(Resource resource) {

        ResourceDTO resourceDTO = new ResourceDTO();
        BeanUtils.copyProperties(resource, resourceDTO);
        resourceDTO.setTeacherId(resource.getTeacher().getId());

        return resourceDTO;

    }

    private Resource copyPropertiesToResource(ResourceDTO resourceDTO) {

        Resource resource = new Resource();
        BeanUtils.copyProperties(resourceDTO, resource);
        Teacher teacher = teacherRepository.findById(resourceDTO.getTeacherId()).get();
        resource.setTeacher(teacher);

        return resource;

    }
}
