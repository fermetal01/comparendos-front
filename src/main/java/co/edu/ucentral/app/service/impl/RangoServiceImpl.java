package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.RangoService;
import co.edu.ucentral.app.domain.Rango;
import co.edu.ucentral.app.repository.RangoRepository;
import co.edu.ucentral.app.service.dto.RangoDTO;
import co.edu.ucentral.app.service.mapper.RangoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Rango}.
 */
@Service
public class RangoServiceImpl implements RangoService {

    private final Logger log = LoggerFactory.getLogger(RangoServiceImpl.class);

    private final RangoRepository rangoRepository;

    private final RangoMapper rangoMapper;

    public RangoServiceImpl(RangoRepository rangoRepository, RangoMapper rangoMapper) {
        this.rangoRepository = rangoRepository;
        this.rangoMapper = rangoMapper;
    }

    @Override
    public RangoDTO save(RangoDTO rangoDTO) {
        log.debug("Request to save Rango : {}", rangoDTO);
        Rango rango = rangoMapper.toEntity(rangoDTO);
        rango = rangoRepository.save(rango);
        return rangoMapper.toDto(rango);
    }

    @Override
    public Page<RangoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Rangos");
        return rangoRepository.findAll(pageable)
            .map(rangoMapper::toDto);
    }


    @Override
    public Optional<RangoDTO> findOne(String id) {
        log.debug("Request to get Rango : {}", id);
        return rangoRepository.findById(id)
            .map(rangoMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Rango : {}", id);
        rangoRepository.deleteById(id);
    }
}
