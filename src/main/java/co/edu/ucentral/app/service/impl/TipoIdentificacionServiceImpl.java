package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.TipoIdentificacionService;
import co.edu.ucentral.app.domain.TipoIdentificacion;
import co.edu.ucentral.app.repository.TipoIdentificacionRepository;
import co.edu.ucentral.app.service.dto.TipoIdentificacionDTO;
import co.edu.ucentral.app.service.mapper.TipoIdentificacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoIdentificacion}.
 */
@Service
public class TipoIdentificacionServiceImpl implements TipoIdentificacionService {

    private final Logger log = LoggerFactory.getLogger(TipoIdentificacionServiceImpl.class);

    private final TipoIdentificacionRepository tipoIdentificacionRepository;

    private final TipoIdentificacionMapper tipoIdentificacionMapper;

    public TipoIdentificacionServiceImpl(TipoIdentificacionRepository tipoIdentificacionRepository, TipoIdentificacionMapper tipoIdentificacionMapper) {
        this.tipoIdentificacionRepository = tipoIdentificacionRepository;
        this.tipoIdentificacionMapper = tipoIdentificacionMapper;
    }

    @Override
    public TipoIdentificacionDTO save(TipoIdentificacionDTO tipoIdentificacionDTO) {
        log.debug("Request to save TipoIdentificacion : {}", tipoIdentificacionDTO);
        TipoIdentificacion tipoIdentificacion = tipoIdentificacionMapper.toEntity(tipoIdentificacionDTO);
        tipoIdentificacion = tipoIdentificacionRepository.save(tipoIdentificacion);
        return tipoIdentificacionMapper.toDto(tipoIdentificacion);
    }

    @Override
    public Page<TipoIdentificacionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoIdentificacions");
        return tipoIdentificacionRepository.findAll(pageable)
            .map(tipoIdentificacionMapper::toDto);
    }


    @Override
    public Optional<TipoIdentificacionDTO> findOne(String id) {
        log.debug("Request to get TipoIdentificacion : {}", id);
        return tipoIdentificacionRepository.findById(id)
            .map(tipoIdentificacionMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete TipoIdentificacion : {}", id);
        tipoIdentificacionRepository.deleteById(id);
    }
}
