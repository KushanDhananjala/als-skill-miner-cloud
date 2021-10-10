package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.AdminDTO;
import lk.edu.esoft.alsskillminercloud.entity.Admin;
import lk.edu.esoft.alsskillminercloud.repository.AdminRepository;
import lk.edu.esoft.alsskillminercloud.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveAdmin(AdminDTO adminDTO) throws Exception {

        Admin admin = new Admin(adminDTO.getName(), adminDTO.getDisplayName(), adminDTO.getEmail(),
                passwordEncoder.encode(adminDTO.getPassword()), adminDTO.getAge(), adminDTO.getJoinDate(), adminDTO.getLocation(),
                adminDTO.getAbout(), adminDTO.getReputation(), adminDTO.getFacebookUrl(), adminDTO.getGithubUrl(),
                adminDTO.getProfileImageUrl(), adminDTO.getPosition());

        adminRepository.save(admin);

        return true;
    }

    @Override
    public AdminDTO getAdmin(String id) throws Exception {

        Admin admin = adminRepository.findById(id).get();

        AdminDTO adminDTO = new AdminDTO(admin.getName(), admin.getDisplayName(), admin.getEmail(),
                admin.getPassword(), admin.getAge(), admin.getJoinDate(), admin.getLocation(),
                admin.getAbout(), admin.getReputation(), admin.getFacebookUrl(), admin.getGithubUrl(),
                admin.getProfileImageUrl(), admin.getPosition());

        return adminDTO;
    }

    @Override
    public boolean deleteAdmin(String id) {

        adminRepository.deleteById(id);
        return true;
    }

    @Override
    public ArrayList<AdminDTO> getAllAdmins() throws Exception {

        ArrayList<AdminDTO> adminDTOS = new ArrayList<>();

        List<Admin> admins = adminRepository.findAll();

        for (Admin admin : admins) {
            AdminDTO adminDTO = new AdminDTO(admin.getName(), admin.getDisplayName(), admin.getEmail(),
                    passwordEncoder.encode(admin.getPassword()), admin.getAge(), admin.getJoinDate(),
                    admin.getLocation(), admin.getAbout(), admin.getReputation(), admin.getFacebookUrl(),
                    admin.getGithubUrl(), admin.getProfileImageUrl(), admin.getPosition());

            adminDTOS.add(adminDTO);
        }

        return adminDTOS;
    }

    @Override
    public boolean canAuthenticate(String username, String password) throws Exception {

        boolean exists = adminRepository.existsById(username);

        if (!exists) {
            return false;
        }

        Admin admin = adminRepository.findById(username).get();

        return passwordEncoder.matches(password, admin.getPassword());
    }

    @Override
    public long getTotalAdmins() throws Exception {
        return adminRepository.count();
    }
}
