package lk.edu.esoft.alsskillminercloud.repository;

import lk.edu.esoft.alsskillminercloud.entity.Resource;
import lk.edu.esoft.alsskillminercloud.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findAllByExpireDateIsGreaterThanEqual(String expireDate);

    List<Resource> findAllByTeacherAndExpireDateIsGreaterThanEqual(Teacher teacher, String expireDate);

}
