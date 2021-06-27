package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.BadgeDTO;

import java.util.ArrayList;

public interface BadgeService {

    boolean saveBadge(BadgeDTO badgeDTO) throws Exception;

    BadgeDTO getBadge(int id) throws Exception;

    boolean deleteBadge(int id) throws Exception;

    ArrayList<BadgeDTO> getAllBadges() throws Exception;

    boolean updateBadge(BadgeDTO badgeDTO) throws Exception;

}
