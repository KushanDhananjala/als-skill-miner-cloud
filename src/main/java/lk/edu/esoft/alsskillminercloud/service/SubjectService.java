package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {

    List<SubjectDTO> getSubjectsByStream(Long streamId) throws Exception;

}
