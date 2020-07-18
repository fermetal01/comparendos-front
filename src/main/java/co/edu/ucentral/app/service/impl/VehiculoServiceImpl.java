package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.VehiculoService;
import co.edu.ucentral.app.domain.Vehiculo;
import co.edu.ucentral.app.repository.VehiculoRepository;
import co.edu.ucentral.app.service.dto.VehiculoDTO;
import co.edu.ucentral.app.service.mapper.VehiculoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Vehiculo}.
 */
@Service
public class VehiculoServiceImpl implements VehiculoService {

    private final Logger log = LoggerFactory.getLogger(VehiculoServiceImpl.class);

    private final VehiculoRepository vehiculoRepository;

    private final VehiculoMapper vehiculoMapper;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, VehiculoMapper vehiculoMapper) {
        this.vehiculoRepository = vehiculoRepository;
        this.vehiculoMapper = vehiculoMapper;
    }

    @Override
    public VehiculoDTO save(VehiculoDTO vehiculoDTO) {
        log.debug("Request to save Vehiculo : {}", vehiculoDTO);
        Vehiculo vehiculo = vehiculoMapper.toEntity(vehiculoDTO);
        vehiculo = vehiculoRepository.save(vehiculo);
        return vehiculoMapper.toDto(vehiculo);
    }

    @Override
    public Page<VehiculoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vehiculos");
        return vehiculoRepository.findAll(pageable)
            .map(vehiculoMapper::toDto);
    }


    public Page<VehiculoDTO> findAllWithEagerRelationships(Pageable pageable) {
        return vehiculoRepository.findAllWithEagerRelationships(pageable).map(vehiculoMapper::toDto);
    }

    @Override
    public Optional<VehiculoDTO> findOne(String id) {
        log.debug("Request to get Vehiculo : {}", id);
        return vehiculoRepository.findOneWithEagerRelationships(id)
            .map(vehiculoMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Vehiculo : {}", id);
        vehiculoRepository.deleteById(id);
    }
}
