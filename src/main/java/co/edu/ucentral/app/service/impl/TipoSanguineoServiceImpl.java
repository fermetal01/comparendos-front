package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.TipoSanguineoService;
import co.edu.ucentral.app.domain.TipoSanguineo;
import co.edu.ucentral.app.repository.TipoSanguineoRepository;
import co.edu.ucentral.app.service.dto.TipoSanguineoDTO;
import co.edu.ucentral.app.service.mapper.TipoSanguineoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoSanguineo}.
 */
@Service
public class TipoSanguineoServiceImpl implements TipoSanguineoService {

    private final Logger log = LoggerFactory.getLogger(TipoSanguineoServiceImpl.class);

    private final TipoSanguineoRepository tipoSanguineoRepository;

    private final TipoSanguineoMapper tipoSanguineoMapper;

    public TipoSanguineoServiceImpl(TipoSanguineoRepository tipoSanguineoRepository, TipoSanguineoMapper tipoSanguineoMapper) {
        this.tipoSanguineoRepository = tipoSanguineoRepository;
        this.tipoSanguineoMapper = tipoSanguineoMapper;
    }

    @Override
    public TipoSanguineoDTO save(TipoSanguineoDTO tipoSanguineoDTO) {
        log.debug("Request to save TipoSanguineo : {}", tipoSanguineoDTO);
        TipoSanguineo tipoSanguineo = tipoSanguineoMapper.toEntity(tipoSanguineoDTO);
        tipoSanguineo = tipoSanguineoRepository.save(tipoSanguineo);
        return tipoSanguineoMapper.toDto(tipoSanguineo);
    }

    @Override
    public Page<TipoSanguineoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoSanguineos");
        return tipoSanguineoRepository.findAll(pageable)
            .map(tipoSanguineoMapper::toDto);
    }


    @Override
    public Optional<TipoSanguineoDTO> findOne(String id) {
        log.debug("Request to get TipoSanguineo : {}", id);
        return tipoSanguineoRepository.findById(id)
            .map(tipoSanguineoMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete TipoSanguineo : {}", id);
        tipoSanguineoRepository.deleteById(id);
    }
}
