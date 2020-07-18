package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonaMapperTest {

    private PersonaMapper personaMapper;

    @BeforeEach
    public void setUp() {
        personaMapper = new PersonaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(personaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(personaMapper.fromId(null)).isNull();
    }
}
