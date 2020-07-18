package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TipoIdentificacionMapperTest {

    private TipoIdentificacionMapper tipoIdentificacionMapper;

    @BeforeEach
    public void setUp() {
        tipoIdentificacionMapper = new TipoIdentificacionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(tipoIdentificacionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tipoIdentificacionMapper.fromId(null)).isNull();
    }
}
