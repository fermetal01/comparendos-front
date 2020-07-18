package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.TipoLicenciaService;
import co.edu.ucentral.app.domain.TipoLicencia;
import co.edu.ucentral.app.repository.TipoLicenciaRepository;
import co.edu.ucentral.app.service.dto.TipoLicenciaDTO;
import co.edu.ucentral.app.service.mapper.TipoLicenciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoLicencia}.
 */
@Service
public class TipoLicenciaServiceImpl implements TipoLicenciaService {

    private final Logger log = LoggerFactory.getLogger(TipoLicenciaServiceImpl.class);

    private final TipoLicenciaRepository tipoLicenciaRepository;

    private final TipoLicenciaMapper tipoLicenciaMapper;

    public TipoLicenciaServiceImpl(TipoLicenciaRepository tipoLicenciaRepository, TipoLicenciaMapper tipoLicenciaMapper) {
        this.tipoLicenciaRepository = tipoLicenciaRepository;
        this.tipoLicenciaMapper = tipoLicenciaMapper;
    }

    @Override
    public TipoLicenciaDTO save(TipoLicenciaDTO tipoLicenciaDTO) {
        log.debug("Request to save TipoLicencia : {}", tipoLicenciaDTO);
        TipoLicencia tipoLicencia = tipoLicenciaMapper.toEntity(tipoLicenciaDTO);
        tipoLicencia = tipoLicenciaRepository.save(tipoLicencia);
        return tipoLicenciaMapper.toDto(tipoLicencia);
    }

    @Override
    public Page<TipoLicenciaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoLicencias");
        return tipoLicenciaRepository.findAll(pageable)
            .map(tipoLicenciaMapper::toDto);
    }


    @Override
    public Optional<TipoLicenciaDTO> findOne(String id) {
        log.debug("Request to get TipoLicencia : {}", id);
        return tipoLicenciaRepository.findById(id)
            .map(tipoLicenciaMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete TipoLicencia : {}", id);
        tipoLicenciaRepository.deleteById(id);
    }
}
