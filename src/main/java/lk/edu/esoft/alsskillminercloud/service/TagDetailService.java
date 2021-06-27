package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.CustomTagDetailDTO;

import java.util.ArrayList;

public interface TagDetailService {

    ArrayList<CustomTagDetailDTO> getAllQuestionsTags() throws Exception;

}
