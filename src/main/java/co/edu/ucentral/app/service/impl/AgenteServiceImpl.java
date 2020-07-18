package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.AgenteService;
import co.edu.ucentral.app.domain.Agente;
import co.edu.ucentral.app.repository.AgenteRepository;
import co.edu.ucentral.app.service.dto.AgenteDTO;
import co.edu.ucentral.app.service.mapper.AgenteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Agente}.
 */
@Service
public class AgenteServiceImpl implements AgenteService {

    private final Logger log = LoggerFactory.getLogger(AgenteServiceImpl.class);

    private final AgenteRepository agenteRepository;

    private final AgenteMapper agenteMapper;

    public AgenteServiceImpl(AgenteRepository agenteRepository, AgenteMapper agenteMapper) {
        this.agenteRepository = agenteRepository;
        this.agenteMapper = agenteMapper;
    }

    @Override
    public AgenteDTO save(AgenteDTO agenteDTO) {
        log.debug("Request to save Agente : {}", agenteDTO);
        Agente agente = agenteMapper.toEntity(agenteDTO);
        agente = agenteRepository.save(agente);
        return agenteMapper.toDto(agente);
    }

    @Override
    public Page<AgenteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Agentes");
        return agenteRepository.findAll(pageable)
            .map(agenteMapper::toDto);
    }


    @Override
    public Optional<AgenteDTO> findOne(String id) {
        log.debug("Request to get Agente : {}", id);
        return agenteRepository.findById(id)
            .map(agenteMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Agente : {}", id);
        agenteRepository.deleteById(id);
    }
}
