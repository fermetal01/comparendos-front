package co.edu.ucentral.app.service.impl;

import co.edu.ucentral.app.service.PersonaService;
import co.edu.ucentral.app.domain.Persona;
import co.edu.ucentral.app.repository.PersonaRepository;
import co.edu.ucentral.app.service.dto.PersonaDTO;
import co.edu.ucentral.app.service.mapper.PersonaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Persona}.
 */
@Service
public class PersonaServiceImpl implements PersonaService {

    private final Logger log = LoggerFactory.getLogger(PersonaServiceImpl.class);

    private final PersonaRepository personaRepository;

    private final PersonaMapper personaMapper;

    public PersonaServiceImpl(PersonaRepository personaRepository, PersonaMapper personaMapper) {
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
    }

    @Override
    public PersonaDTO save(PersonaDTO personaDTO) {
        log.debug("Request to save Persona : {}", personaDTO);
        Persona persona = personaMapper.toEntity(personaDTO);
        persona = personaRepository.save(persona);
        return personaMapper.toDto(persona);
    }

    @Override
    public Page<PersonaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Personas");
        return personaRepository.findAll(pageable)
            .map(personaMapper::toDto);
    }


    @Override
    public Optional<PersonaDTO> findOne(String id) {
        log.debug("Request to get Persona : {}", id);
        return personaRepository.findById(id)
            .map(personaMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Persona : {}", id);
        personaRepository.deleteById(id);
    }
}
