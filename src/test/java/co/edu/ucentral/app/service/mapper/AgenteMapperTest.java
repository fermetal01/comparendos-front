package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AgenteMapperTest {

    private AgenteMapper agenteMapper;

    @BeforeEach
    public void setUp() {
        agenteMapper = new AgenteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(agenteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(agenteMapper.fromId(null)).isNull();
    }
}
