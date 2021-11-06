package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.AdminDTO;
import lk.edu.esoft.alsskillminercloud.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveAdmin(@RequestBody AdminDTO adminDTO) {
        try {
            return adminService.saveAdmin(adminDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AdminDTO getAdmin(@PathVariable("name") String name) {
        try {
            return adminService.getAdmin(name);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @DeleteMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteAdmin(@PathVariable("name") String name) {
        try {
            return adminService.deleteAdmin(name);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<AdminDTO> getAllAdmins() {
        try {
            return adminService.getAllAdmins();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @PostMapping(value = "/{login}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean canAuthenticate(@RequestBody AdminDTO adminDTO) {
        try {
            return adminService.canAuthenticate(adminDTO.getName(), adminDTO.getPassword());
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getTotalAdmins() {
        try {
            return adminService.getTotalAdmins();
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

}
