package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.CategoriaService;
import co.edu.ucentral.app.domain.Categoria;
import co.edu.ucentral.app.repository.CategoriaRepository;
import co.edu.ucentral.app.service.dto.CategoriaDTO;
import co.edu.ucentral.app.service.mapper.CategoriaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Categoria}.
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    private final CategoriaRepository categoriaRepository;

    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        log.debug("Request to save Categoria : {}", categoriaDTO);
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(categoria);
    }

    @Override
    public Page<CategoriaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Categorias");
        return categoriaRepository.findAll(pageable)
            .map(categoriaMapper::toDto);
    }


    @Override
    public Optional<CategoriaDTO> findOne(String id) {
        log.debug("Request to get Categoria : {}", id);
        return categoriaRepository.findById(id)
            .map(categoriaMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Categoria : {}", id);
        categoriaRepository.deleteById(id);
    }
}
