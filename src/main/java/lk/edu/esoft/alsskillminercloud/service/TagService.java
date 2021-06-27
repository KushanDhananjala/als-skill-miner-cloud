package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.TagDTO;

import java.util.ArrayList;

public interface TagService {

    boolean saveTag(TagDTO tagDTO) throws Exception;

    TagDTO getTag(long id) throws Exception;

    boolean deleteTag(long id) throws Exception;

    ArrayList<TagDTO> getAllTags() throws Exception;

    boolean updateTag(TagDTO tagDTO) throws Exception;

}
