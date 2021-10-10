package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.TeacherDTO;
import lk.edu.esoft.alsskillminercloud.entity.Subject;
import lk.edu.esoft.alsskillminercloud.entity.Teacher;
import lk.edu.esoft.alsskillminercloud.repository.SubjectRepository;
import lk.edu.esoft.alsskillminercloud.repository.TeacherRepository;
import lk.edu.esoft.alsskillminercloud.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean save(TeacherDTO teacherDTO) throws Exception {
        teacherRepository.save(copyPropertiesTeacher(teacherDTO));
        return true;
    }

    @Override
    public List<TeacherDTO> getTeachersBySubject(Long subjectId) throws Exception {
        Subject subject = subjectRepository.findById(subjectId).get();
        return teacherRepository.findAllBySubject(subject).stream().map(this::copyPropertiesTeacherDto).collect(Collectors.toList());
    }

    @Override
    public List<TeacherDTO> getTeachers() throws Exception {
        return teacherRepository.findAll().stream().map(this::copyPropertiesTeacherDto).collect(Collectors.toList());
    }

    @Override
    public TeacherDTO getTeacherById(Long id) throws Exception {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (!optionalTeacher.isPresent()) {
            throw new RuntimeException("Teacher not found for id: " + id);
        }
        return copyPropertiesTeacherDto(optionalTeacher.get());
    }

    @Override
    public TeacherDTO getTeacherByUserName(String userName) throws Exception {
        Optional<Teacher> optionalTeacher = teacherRepository.findByUserName(userName);
        if (!optionalTeacher.isPresent()) {
            throw new RuntimeException("Teacher not found for user name: " + userName);
        }
        return copyPropertiesTeacherDto(optionalTeacher.get());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteById(Long id) throws Exception {
        subjectRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean canAuthenticate(String username, String password) throws Exception {
        Optional<Teacher> byUserName = teacherRepository.findByUserName(username);

        if (!byUserName.isPresent()) {
            return false;
        }

        Teacher teacher = teacherRepository.findByUserName(username).get();

        return passwordEncoder.matches(password, teacher.getPassword());
    }

    private TeacherDTO copyPropertiesTeacherDto(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(teacher, teacherDTO);
        teacherDTO.setSubjectId(teacher.getSubject().getId());

        return teacherDTO;
    }

    private Teacher copyPropertiesTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDTO, teacher);
        Subject subject = subjectRepository.findById(teacherDTO.getSubjectId()).get();
        teacher.setSubject(subject);
        teacher.setPassword(passwordEncoder.encode(teacherDTO.getPassword()));

        return teacher;
    }
}
