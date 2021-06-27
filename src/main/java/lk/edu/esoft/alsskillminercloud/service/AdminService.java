package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.AdminDTO;

import java.util.ArrayList;

public interface AdminService {

    boolean saveAdmin(AdminDTO adminDTO) throws Exception;

    AdminDTO getAdmin(String id) throws Exception;

    boolean deleteAdmin(String id) throws Exception;

    ArrayList<AdminDTO> getAllAdmins() throws Exception;

    boolean canAuthenticate(String username, String password) throws Exception;

    long getTotalAdmins() throws Exception;

}
