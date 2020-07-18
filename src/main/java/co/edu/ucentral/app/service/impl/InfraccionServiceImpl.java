package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.InfraccionService;
import co.edu.ucentral.app.domain.Infraccion;
import co.edu.ucentral.app.repository.InfraccionRepository;
import co.edu.ucentral.app.service.dto.InfraccionDTO;
import co.edu.ucentral.app.service.mapper.InfraccionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Infraccion}.
 */
@Service
public class InfraccionServiceImpl implements InfraccionService {

    private final Logger log = LoggerFactory.getLogger(InfraccionServiceImpl.class);

    private final InfraccionRepository infraccionRepository;

    private final InfraccionMapper infraccionMapper;

    public InfraccionServiceImpl(InfraccionRepository infraccionRepository, InfraccionMapper infraccionMapper) {
        this.infraccionRepository = infraccionRepository;
        this.infraccionMapper = infraccionMapper;
    }

    @Override
    public InfraccionDTO save(InfraccionDTO infraccionDTO) {
        log.debug("Request to save Infraccion : {}", infraccionDTO);
        Infraccion infraccion = infraccionMapper.toEntity(infraccionDTO);
        infraccion = infraccionRepository.save(infraccion);
        return infraccionMapper.toDto(infraccion);
    }

    @Override
    public Page<InfraccionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Infraccions");
        return infraccionRepository.findAll(pageable)
            .map(infraccionMapper::toDto);
    }


    @Override
    public Optional<InfraccionDTO> findOne(String id) {
        log.debug("Request to get Infraccion : {}", id);
        return infraccionRepository.findById(id)
            .map(infraccionMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Infraccion : {}", id);
        infraccionRepository.deleteById(id);
    }
}
