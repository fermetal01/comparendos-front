package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TipoLicenciaMapperTest {

    private TipoLicenciaMapper tipoLicenciaMapper;

    @BeforeEach
    public void setUp() {
        tipoLicenciaMapper = new TipoLicenciaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(tipoLicenciaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tipoLicenciaMapper.fromId(null)).isNull();
    }
}
