package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.PatioService;
import co.edu.ucentral.app.domain.Patio;
import co.edu.ucentral.app.repository.PatioRepository;
import co.edu.ucentral.app.service.dto.PatioDTO;
import co.edu.ucentral.app.service.mapper.PatioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Patio}.
 */
@Service
public class PatioServiceImpl implements PatioService {

    private final Logger log = LoggerFactory.getLogger(PatioServiceImpl.class);

    private final PatioRepository patioRepository;

    private final PatioMapper patioMapper;

    public PatioServiceImpl(PatioRepository patioRepository, PatioMapper patioMapper) {
        this.patioRepository = patioRepository;
        this.patioMapper = patioMapper;
    }

    @Override
    public PatioDTO save(PatioDTO patioDTO) {
        log.debug("Request to save Patio : {}", patioDTO);
        Patio patio = patioMapper.toEntity(patioDTO);
        patio = patioRepository.save(patio);
        return patioMapper.toDto(patio);
    }

    @Override
    public Page<PatioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Patios");
        return patioRepository.findAll(pageable)
            .map(patioMapper::toDto);
    }


    @Override
    public Optional<PatioDTO> findOne(String id) {
        log.debug("Request to get Patio : {}", id);
        return patioRepository.findById(id)
            .map(patioMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Patio : {}", id);
        patioRepository.deleteById(id);
    }
}
