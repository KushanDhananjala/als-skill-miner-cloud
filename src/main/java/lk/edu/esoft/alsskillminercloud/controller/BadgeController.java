package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.BadgeDTO;
import lk.edu.esoft.alsskillminercloud.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/badges")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveBadge(@RequestBody BadgeDTO badgeDTO) {
        try {
            return badgeService.saveBadge(badgeDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BadgeDTO getBadge(@PathVariable("id") Long id) {
        try {
            return badgeService.getBadge(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteBadge(@PathVariable("id") Long id) {
        try {
            return badgeService.deleteBadge(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<BadgeDTO> getAllBadges() {
        try {
            return badgeService.getAllBadges();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateBadge(@RequestBody BadgeDTO badgeDTO) {
        try {
            return badgeService.updateBadge(badgeDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
