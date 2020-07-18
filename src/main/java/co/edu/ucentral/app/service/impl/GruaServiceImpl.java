package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.GruaService;
import co.edu.ucentral.app.domain.Grua;
import co.edu.ucentral.app.repository.GruaRepository;
import co.edu.ucentral.app.service.dto.GruaDTO;
import co.edu.ucentral.app.service.mapper.GruaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Grua}.
 */
@Service
public class GruaServiceImpl implements GruaService {

    private final Logger log = LoggerFactory.getLogger(GruaServiceImpl.class);

    private final GruaRepository gruaRepository;

    private final GruaMapper gruaMapper;

    public GruaServiceImpl(GruaRepository gruaRepository, GruaMapper gruaMapper) {
        this.gruaRepository = gruaRepository;
        this.gruaMapper = gruaMapper;
    }

    @Override
    public GruaDTO save(GruaDTO gruaDTO) {
        log.debug("Request to save Grua : {}", gruaDTO);
        Grua grua = gruaMapper.toEntity(gruaDTO);
        grua = gruaRepository.save(grua);
        return gruaMapper.toDto(grua);
    }

    @Override
    public Page<GruaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Gruas");
        return gruaRepository.findAll(pageable)
            .map(gruaMapper::toDto);
    }


    @Override
    public Optional<GruaDTO> findOne(String id) {
        log.debug("Request to get Grua : {}", id);
        return gruaRepository.findById(id)
            .map(gruaMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Grua : {}", id);
        gruaRepository.deleteById(id);
    }
}
