package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.SaveSubjectDTO;
import lk.edu.esoft.alsskillminercloud.dto.StreamDTO;
import lk.edu.esoft.alsskillminercloud.dto.SubjectDTO;
import lk.edu.esoft.alsskillminercloud.entity.Stream;
import lk.edu.esoft.alsskillminercloud.entity.StreamSubjectDetail;
import lk.edu.esoft.alsskillminercloud.entity.Subject;
import lk.edu.esoft.alsskillminercloud.repository.StreamRepository;
import lk.edu.esoft.alsskillminercloud.repository.StreamSubjectDetailRepository;
import lk.edu.esoft.alsskillminercloud.repository.SubjectRepository;
import lk.edu.esoft.alsskillminercloud.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final StreamRepository streamRepository;
    private final StreamSubjectDetailRepository streamSubjectDetailRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean save(SaveSubjectDTO saveSubjectDTO) throws Exception {
        Subject savedSubject = subjectRepository.save(copyPropertiesSubject(saveSubjectDTO.getSubjectDTO()));

        if (saveSubjectDTO.getStreamDTOS() != null || !saveSubjectDTO.getStreamDTOS().isEmpty()) {

            for (StreamDTO streamDTO : saveSubjectDTO.getStreamDTOS()) {
                Stream savedStream = streamRepository.findById(streamDTO.getId()).get();
                streamSubjectDetailRepository.save(new StreamSubjectDetail(0L, savedStream, savedSubject));
            }
        }

        return true;
    }

    @Override
    public List<SubjectDTO> getSubjectsByStream(Long streamId) throws Exception {
        ArrayList<Object[]> objectArrayList = subjectRepository.getAllByStreamId(streamId);
        ArrayList<SubjectDTO> subjectsByStreamId = new ArrayList<>();

        for (Object[] o : objectArrayList) {
            SubjectDTO subjectDTO = new SubjectDTO();
            subjectDTO.setId(Long.parseLong(o[0].toString()));
            subjectDTO.setSubject(o[1].toString());

            subjectsByStreamId.add(subjectDTO);
        }
        return subjectsByStreamId;
    }

    @Override
    public List<SubjectDTO> getSubjects() throws Exception {
        return subjectRepository.findAll().stream().map(this::copyPropertiesSubjectDto).collect(Collectors.toList());
    }

    @Override
    public SubjectDTO getSubjectById(Long id) throws Exception {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (!optionalSubject.isPresent()) {
            throw new RuntimeException("Subject not found for id: " + id);
        }
        return copyPropertiesSubjectDto(optionalSubject.get());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteById(Long id) throws Exception {
        subjectRepository.deleteById(id);
        return true;
    }

    private SubjectDTO copyPropertiesSubjectDto(Subject subject) {
        SubjectDTO subjectDTO = new SubjectDTO();
        BeanUtils.copyProperties(subject, subjectDTO);
        return subjectDTO;
    }

    private Subject copyPropertiesSubject(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectDTO, subject);
        return subject;
    }
}
