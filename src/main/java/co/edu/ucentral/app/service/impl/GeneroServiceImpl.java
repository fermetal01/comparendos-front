package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.GeneroService;
import co.edu.ucentral.app.domain.Genero;
import co.edu.ucentral.app.repository.GeneroRepository;
import co.edu.ucentral.app.service.dto.GeneroDTO;
import co.edu.ucentral.app.service.mapper.GeneroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Genero}.
 */
@Service
public class GeneroServiceImpl implements GeneroService {

    private final Logger log = LoggerFactory.getLogger(GeneroServiceImpl.class);

    private final GeneroRepository generoRepository;

    private final GeneroMapper generoMapper;

    public GeneroServiceImpl(GeneroRepository generoRepository, GeneroMapper generoMapper) {
        this.generoRepository = generoRepository;
        this.generoMapper = generoMapper;
    }

    @Override
    public GeneroDTO save(GeneroDTO generoDTO) {
        log.debug("Request to save Genero : {}", generoDTO);
        Genero genero = generoMapper.toEntity(generoDTO);
        genero = generoRepository.save(genero);
        return generoMapper.toDto(genero);
    }

    @Override
    public Page<GeneroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Generos");
        return generoRepository.findAll(pageable)
            .map(generoMapper::toDto);
    }


    @Override
    public Optional<GeneroDTO> findOne(String id) {
        log.debug("Request to get Genero : {}", id);
        return generoRepository.findById(id)
            .map(generoMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Genero : {}", id);
        generoRepository.deleteById(id);
    }
}
