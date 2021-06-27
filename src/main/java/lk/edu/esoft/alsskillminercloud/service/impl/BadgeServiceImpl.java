package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.BadgeDTO;
import lk.edu.esoft.alsskillminercloud.entity.Badge;
import lk.edu.esoft.alsskillminercloud.repository.BadgeRepository;
import lk.edu.esoft.alsskillminercloud.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BadgeServiceImpl implements BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveBadge(BadgeDTO badgeDTO) throws Exception {

        Badge badge = new Badge(badgeDTO.getName(), badgeDTO.getPoints());
        badgeRepository.save(badge);

        return true;

    }

    @Override
    public BadgeDTO getBadge(int id) throws Exception {

        Badge badge = badgeRepository.findById(id).get();

        BadgeDTO badgeDTO = new BadgeDTO(badge.getId(), badge.getName(), badge.getPoints());

        return badgeDTO;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteBadge(int id) throws Exception {

        badgeRepository.deleteById(id);

        return true;

    }

    @Override
    public ArrayList<BadgeDTO> getAllBadges() throws Exception {

        ArrayList<BadgeDTO> badgeDTOS = new ArrayList<>();

        List<Badge> badges = badgeRepository.findAll();

        for (Badge badge : badges) {

            BadgeDTO badgeDTO = new BadgeDTO(badge.getId(), badge.getName(), badge.getPoints());

            badgeDTOS.add(badgeDTO);
        }

        return badgeDTOS;
    }

    @Override
    public boolean updateBadge(BadgeDTO badgeDTO) throws Exception {

        Badge badge = new Badge(badgeDTO.getId(), badgeDTO.getName(), badgeDTO.getPoints());
        badgeRepository.save(badge);

        return true;
    }
}
