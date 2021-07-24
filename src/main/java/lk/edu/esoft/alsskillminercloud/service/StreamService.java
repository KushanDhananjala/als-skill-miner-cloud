package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.StreamDTO;

import java.util.List;

public interface StreamService {

    List<StreamDTO> getStreams() throws Exception;

}
