package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.UserDTO;
import lk.edu.esoft.alsskillminercloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveUser(@RequestBody UserDTO userDTO) {
        try {
            return userService.saveUser(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUser(@PathVariable("name") String name) {
        try {
            return userService.getUser(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteUser(@PathVariable("name") String name) {
        try {
            return userService.deleteUser(name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<UserDTO> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "/{login}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean canAuthenticate(@RequestBody UserDTO userDTO) {
        try {
            return userService.canAuthenticate(userDTO.getName(), userDTO.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getTotleUsers() {
        try {
            return userService.getTotalUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @PostMapping(value = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateUser(@RequestBody UserDTO userDTO) {
        try {
            return userService.updateUser(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(value = "/topFiveUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<UserDTO> getTopFiveUsers() {
        try {
            return userService.getTopFiveUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "/updatePoints", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateUserPoints(@RequestParam("userName") String userName, @RequestParam("points") long points) {
        try {
            return userService.updateUserPoints(userName, points);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
