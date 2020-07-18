package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LicenciaMapperTest {

    private LicenciaMapper licenciaMapper;

    @BeforeEach
    public void setUp() {
        licenciaMapper = new LicenciaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(licenciaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(licenciaMapper.fromId(null)).isNull();
    }
}
