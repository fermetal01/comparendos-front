package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.ServicioService;
import co.edu.ucentral.app.domain.Servicio;
import co.edu.ucentral.app.repository.ServicioRepository;
import co.edu.ucentral.app.service.dto.ServicioDTO;
import co.edu.ucentral.app.service.mapper.ServicioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Servicio}.
 */
@Service
public class ServicioServiceImpl implements ServicioService {

    private final Logger log = LoggerFactory.getLogger(ServicioServiceImpl.class);

    private final ServicioRepository servicioRepository;

    private final ServicioMapper servicioMapper;

    public ServicioServiceImpl(ServicioRepository servicioRepository, ServicioMapper servicioMapper) {
        this.servicioRepository = servicioRepository;
        this.servicioMapper = servicioMapper;
    }

    @Override
    public ServicioDTO save(ServicioDTO servicioDTO) {
        log.debug("Request to save Servicio : {}", servicioDTO);
        Servicio servicio = servicioMapper.toEntity(servicioDTO);
        servicio = servicioRepository.save(servicio);
        return servicioMapper.toDto(servicio);
    }

    @Override
    public Page<ServicioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Servicios");
        return servicioRepository.findAll(pageable)
            .map(servicioMapper::toDto);
    }


    @Override
    public Optional<ServicioDTO> findOne(String id) {
        log.debug("Request to get Servicio : {}", id);
        return servicioRepository.findById(id)
            .map(servicioMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Servicio : {}", id);
        servicioRepository.deleteById(id);
    }
}
