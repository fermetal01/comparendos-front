package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EstadoComparendoMapperTest {

    private EstadoComparendoMapper estadoComparendoMapper;

    @BeforeEach
    public void setUp() {
        estadoComparendoMapper = new EstadoComparendoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(estadoComparendoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(estadoComparendoMapper.fromId(null)).isNull();
    }
}
