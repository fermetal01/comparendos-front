package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.EstadoComparendoService;
import co.edu.ucentral.app.domain.EstadoComparendo;
import co.edu.ucentral.app.repository.EstadoComparendoRepository;
import co.edu.ucentral.app.service.dto.EstadoComparendoDTO;
import co.edu.ucentral.app.service.mapper.EstadoComparendoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EstadoComparendo}.
 */
@Service
public class EstadoComparendoServiceImpl implements EstadoComparendoService {

    private final Logger log = LoggerFactory.getLogger(EstadoComparendoServiceImpl.class);

    private final EstadoComparendoRepository estadoComparendoRepository;

    private final EstadoComparendoMapper estadoComparendoMapper;

    public EstadoComparendoServiceImpl(EstadoComparendoRepository estadoComparendoRepository, EstadoComparendoMapper estadoComparendoMapper) {
        this.estadoComparendoRepository = estadoComparendoRepository;
        this.estadoComparendoMapper = estadoComparendoMapper;
    }

    @Override
    public EstadoComparendoDTO save(EstadoComparendoDTO estadoComparendoDTO) {
        log.debug("Request to save EstadoComparendo : {}", estadoComparendoDTO);
        EstadoComparendo estadoComparendo = estadoComparendoMapper.toEntity(estadoComparendoDTO);
        estadoComparendo = estadoComparendoRepository.save(estadoComparendo);
        return estadoComparendoMapper.toDto(estadoComparendo);
    }

    @Override
    public Page<EstadoComparendoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EstadoComparendos");
        return estadoComparendoRepository.findAll(pageable)
            .map(estadoComparendoMapper::toDto);
    }


    @Override
    public Optional<EstadoComparendoDTO> findOne(String id) {
        log.debug("Request to get EstadoComparendo : {}", id);
        return estadoComparendoRepository.findById(id)
            .map(estadoComparendoMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete EstadoComparendo : {}", id);
        estadoComparendoRepository.deleteById(id);
    }
}
