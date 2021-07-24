package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.BadgeDTO;

import java.util.ArrayList;

public interface BadgeService {

    boolean saveBadge(BadgeDTO badgeDTO) throws Exception;

    BadgeDTO getBadge(Long id) throws Exception;

    boolean deleteBadge(Long id) throws Exception;

    ArrayList<BadgeDTO> getAllBadges() throws Exception;

    boolean updateBadge(BadgeDTO badgeDTO) throws Exception;

}
