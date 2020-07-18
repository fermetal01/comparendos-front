package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VehiculoMapperTest {

    private VehiculoMapper vehiculoMapper;

    @BeforeEach
    public void setUp() {
        vehiculoMapper = new VehiculoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(vehiculoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(vehiculoMapper.fromId(null)).isNull();
    }
}
