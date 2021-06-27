package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.TagDTO;
import lk.edu.esoft.alsskillminercloud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveTag(@RequestBody TagDTO tagDTO) {
        try {
            return tagService.saveTag(tagDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TagDTO getTag(@PathVariable("id") long id) {
        try {
            return tagService.getTag(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteTag(@PathVariable("id") long id) {
        try {
            return tagService.deleteTag(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<TagDTO> getAllTags() {
        try {
            return tagService.getAllTags();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateTag(@RequestBody TagDTO tagDTO) {
        try {
            return tagService.updateTag(tagDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
