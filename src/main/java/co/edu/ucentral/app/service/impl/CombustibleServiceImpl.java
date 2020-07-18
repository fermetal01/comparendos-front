package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.CombustibleService;
import co.edu.ucentral.app.domain.Combustible;
import co.edu.ucentral.app.repository.CombustibleRepository;
import co.edu.ucentral.app.service.dto.CombustibleDTO;
import co.edu.ucentral.app.service.mapper.CombustibleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Combustible}.
 */
@Service
public class CombustibleServiceImpl implements CombustibleService {

    private final Logger log = LoggerFactory.getLogger(CombustibleServiceImpl.class);

    private final CombustibleRepository combustibleRepository;

    private final CombustibleMapper combustibleMapper;

    public CombustibleServiceImpl(CombustibleRepository combustibleRepository, CombustibleMapper combustibleMapper) {
        this.combustibleRepository = combustibleRepository;
        this.combustibleMapper = combustibleMapper;
    }

    @Override
    public CombustibleDTO save(CombustibleDTO combustibleDTO) {
        log.debug("Request to save Combustible : {}", combustibleDTO);
        Combustible combustible = combustibleMapper.toEntity(combustibleDTO);
        combustible = combustibleRepository.save(combustible);
        return combustibleMapper.toDto(combustible);
    }

    @Override
    public Page<CombustibleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Combustibles");
        return combustibleRepository.findAll(pageable)
            .map(combustibleMapper::toDto);
    }


    @Override
    public Optional<CombustibleDTO> findOne(String id) {
        log.debug("Request to get Combustible : {}", id);
        return combustibleRepository.findById(id)
            .map(combustibleMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Combustible : {}", id);
        combustibleRepository.deleteById(id);
    }
}
