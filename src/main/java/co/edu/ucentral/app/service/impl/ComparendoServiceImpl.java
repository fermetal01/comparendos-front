package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.ComparendoService;
import co.edu.ucentral.app.domain.Comparendo;
import co.edu.ucentral.app.repository.ComparendoRepository;
import co.edu.ucentral.app.service.dto.ComparendoDTO;
import co.edu.ucentral.app.service.mapper.ComparendoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Comparendo}.
 */
@Service
public class ComparendoServiceImpl implements ComparendoService {

    private final Logger log = LoggerFactory.getLogger(ComparendoServiceImpl.class);

    private final ComparendoRepository comparendoRepository;

    private final ComparendoMapper comparendoMapper;

    public ComparendoServiceImpl(ComparendoRepository comparendoRepository, ComparendoMapper comparendoMapper) {
        this.comparendoRepository = comparendoRepository;
        this.comparendoMapper = comparendoMapper;
    }

    @Override
    public ComparendoDTO save(ComparendoDTO comparendoDTO) {
        log.debug("Request to save Comparendo : {}", comparendoDTO);
        Comparendo comparendo = comparendoMapper.toEntity(comparendoDTO);
        comparendo = comparendoRepository.save(comparendo);
        return comparendoMapper.toDto(comparendo);
    }

    @Override
    public Page<ComparendoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comparendos");
        return comparendoRepository.findAll(pageable)
            .map(comparendoMapper::toDto);
    }


    public Page<ComparendoDTO> findAllWithEagerRelationships(Pageable pageable) {
        return comparendoRepository.findAllWithEagerRelationships(pageable).map(comparendoMapper::toDto);
    }

    @Override
    public Optional<ComparendoDTO> findOne(String id) {
        log.debug("Request to get Comparendo : {}", id);
        return comparendoRepository.findOneWithEagerRelationships(id)
            .map(comparendoMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Comparendo : {}", id);
        comparendoRepository.deleteById(id);
    }
}
