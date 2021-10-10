package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.SaveSubjectDTO;
import lk.edu.esoft.alsskillminercloud.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {

    boolean save(SaveSubjectDTO saveSubjectDTO) throws Exception;

    List<SubjectDTO> getSubjectsByStream(Long streamId) throws Exception;

    List<SubjectDTO> getSubjects() throws Exception;

    SubjectDTO getSubjectById(Long id) throws Exception;

    boolean deleteById(Long id) throws Exception;
}
