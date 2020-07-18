package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ServicioMapperTest {

    private ServicioMapper servicioMapper;

    @BeforeEach
    public void setUp() {
        servicioMapper = new ServicioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(servicioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(servicioMapper.fromId(null)).isNull();
    }
}
