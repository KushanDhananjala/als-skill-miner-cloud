package lk.edu.esoft.alsskillminercloud.service;

import lk.edu.esoft.alsskillminercloud.dto.StreamDTO;

import java.util.List;

public interface StreamService {

    boolean save(StreamDTO streamDTO) throws Exception;

    List<StreamDTO> getStreams() throws Exception;

    StreamDTO getStreamById(Long id) throws Exception;

    boolean deleteStream(Long id) throws Exception;

}
