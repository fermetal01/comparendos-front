package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CiudadMapperTest {

    private CiudadMapper ciudadMapper;

    @BeforeEach
    public void setUp() {
        ciudadMapper = new CiudadMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(ciudadMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ciudadMapper.fromId(null)).isNull();
    }
}
