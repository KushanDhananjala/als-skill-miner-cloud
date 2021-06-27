package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.UserDTO;

import java.util.ArrayList;

public interface UserService {

    boolean saveUser(UserDTO userDTO) throws Exception;

    UserDTO getUser(String id) throws Exception;

    boolean deleteUser(String id) throws Exception;

    ArrayList<UserDTO> getAllUsers() throws Exception;

    boolean canAuthenticate(String username, String password) throws Exception;

    long getTotalUsers() throws Exception;

    boolean updateUser(UserDTO userDTO) throws Exception;

    ArrayList<UserDTO> getTopFiveUsers() throws Exception;

    boolean updateUserPoints(String userName, long points) throws Exception;

}
