package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.SubjectDTO;
import lk.edu.esoft.alsskillminercloud.entity.Subject;
import lk.edu.esoft.alsskillminercloud.repository.SubjectRepository;
import lk.edu.esoft.alsskillminercloud.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public List<SubjectDTO> getSubjectsByStream(Long streamId) throws Exception {
        return subjectRepository.findAllByStreamId(streamId).stream().map(this::copyPropertiesSubject).collect(Collectors.toList());
    }

    private SubjectDTO copyPropertiesSubject(Subject subject) {
        SubjectDTO subjectDTO = new SubjectDTO();
        BeanUtils.copyProperties(subject, subjectDTO);
        return subjectDTO;
    }
}
