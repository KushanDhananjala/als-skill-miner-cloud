package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.dto.StreamDTO;
import lk.edu.esoft.alsskillminercloud.entity.Stream;
import lk.edu.esoft.alsskillminercloud.repository.StreamRepository;
import lk.edu.esoft.alsskillminercloud.service.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@RequiredArgsConstructor
public class StreamServiceImpl implements StreamService {

    private final StreamRepository streamRepository;

    @Override
    public List<StreamDTO> getStreams() throws Exception {
        return streamRepository.findAll().stream().map(this::copyPropertiesStream).collect(Collectors.toList());
    }

    private StreamDTO copyPropertiesStream(Stream stream) {
        StreamDTO streamDTO = new StreamDTO();
        BeanUtils.copyProperties(stream, streamDTO);
        return streamDTO;
    }
}
