package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.CiudadService;
import co.edu.ucentral.app.domain.Ciudad;
import co.edu.ucentral.app.repository.CiudadRepository;
import co.edu.ucentral.app.service.dto.CiudadDTO;
import co.edu.ucentral.app.service.mapper.CiudadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Ciudad}.
 */
@Service
public class CiudadServiceImpl implements CiudadService {

    private final Logger log = LoggerFactory.getLogger(CiudadServiceImpl.class);

    private final CiudadRepository ciudadRepository;

    private final CiudadMapper ciudadMapper;

    public CiudadServiceImpl(CiudadRepository ciudadRepository, CiudadMapper ciudadMapper) {
        this.ciudadRepository = ciudadRepository;
        this.ciudadMapper = ciudadMapper;
    }

    @Override
    public CiudadDTO save(CiudadDTO ciudadDTO) {
        log.debug("Request to save Ciudad : {}", ciudadDTO);
        Ciudad ciudad = ciudadMapper.toEntity(ciudadDTO);
        ciudad = ciudadRepository.save(ciudad);
        return ciudadMapper.toDto(ciudad);
    }

    @Override
    public Page<CiudadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ciudads");
        return ciudadRepository.findAll(pageable)
            .map(ciudadMapper::toDto);
    }


    @Override
    public Optional<CiudadDTO> findOne(String id) {
        log.debug("Request to get Ciudad : {}", id);
        return ciudadRepository.findById(id)
            .map(ciudadMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Ciudad : {}", id);
        ciudadRepository.deleteById(id);
    }
}
