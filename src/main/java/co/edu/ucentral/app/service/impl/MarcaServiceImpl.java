package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.MarcaService;
import co.edu.ucentral.app.domain.Marca;
import co.edu.ucentral.app.repository.MarcaRepository;
import co.edu.ucentral.app.service.dto.MarcaDTO;
import co.edu.ucentral.app.service.mapper.MarcaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Marca}.
 */
@Service
public class MarcaServiceImpl implements MarcaService {

    private final Logger log = LoggerFactory.getLogger(MarcaServiceImpl.class);

    private final MarcaRepository marcaRepository;

    private final MarcaMapper marcaMapper;

    public MarcaServiceImpl(MarcaRepository marcaRepository, MarcaMapper marcaMapper) {
        this.marcaRepository = marcaRepository;
        this.marcaMapper = marcaMapper;
    }

    @Override
    public MarcaDTO save(MarcaDTO marcaDTO) {
        log.debug("Request to save Marca : {}", marcaDTO);
        Marca marca = marcaMapper.toEntity(marcaDTO);
        marca = marcaRepository.save(marca);
        return marcaMapper.toDto(marca);
    }

    @Override
    public Page<MarcaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Marcas");
        return marcaRepository.findAll(pageable)
            .map(marcaMapper::toDto);
    }


    @Override
    public Optional<MarcaDTO> findOne(String id) {
        log.debug("Request to get Marca : {}", id);
        return marcaRepository.findById(id)
            .map(marcaMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Marca : {}", id);
        marcaRepository.deleteById(id);
    }
}
