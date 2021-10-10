package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {

    boolean save(TeacherDTO teacherDTO) throws Exception;

    List<TeacherDTO> getTeachersBySubject(Long subjectId) throws Exception;

    List<TeacherDTO> getTeachers() throws Exception;

    TeacherDTO getTeacherById(Long id) throws Exception;

    TeacherDTO getTeacherByUserName(String userName) throws Exception;

    boolean deleteById(Long id) throws Exception;

    boolean canAuthenticate(String username, String password) throws Exception;
}
