package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClaseVehiculoMapperTest {

    private ClaseVehiculoMapper claseVehiculoMapper;

    @BeforeEach
    public void setUp() {
        claseVehiculoMapper = new ClaseVehiculoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(claseVehiculoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(claseVehiculoMapper.fromId(null)).isNull();
    }
}
