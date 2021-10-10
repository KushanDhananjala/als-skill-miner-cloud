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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@RequiredArgsConstructor
public class StreamServiceImpl implements StreamService {

    private final StreamRepository streamRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean save(StreamDTO streamDTO) throws Exception {
        streamRepository.save(convertStreamDtoToStream(streamDTO));
        return true;
    }

    @Override
    public List<StreamDTO> getStreams() throws Exception {
        return streamRepository.findAll().stream().map(this::convertStreamToStreamDto).collect(Collectors.toList());
    }

    @Override
    public StreamDTO getStreamById(Long id) throws Exception {
        Optional<Stream> optionalStream = streamRepository.findById(id);
        if (!optionalStream.isPresent()) {
            throw new RuntimeException("Stream not found for id: " + id);
        }
        return convertStreamToStreamDto(optionalStream.get());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteStream(Long id) throws Exception {
        streamRepository.deleteById(id);
        return true;
    }

    private StreamDTO convertStreamToStreamDto(Stream stream) {
        StreamDTO streamDTO = new StreamDTO();
        BeanUtils.copyProperties(stream, streamDTO);
        return streamDTO;
    }

    private Stream convertStreamDtoToStream(StreamDTO streamDTO) {
        Stream stream = new Stream();
        BeanUtils.copyProperties(streamDTO, stream);
        return stream;
    }
}
