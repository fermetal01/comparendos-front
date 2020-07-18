package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TipoSanguineoMapperTest {

    private TipoSanguineoMapper tipoSanguineoMapper;

    @BeforeEach
    public void setUp() {
        tipoSanguineoMapper = new TipoSanguineoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(tipoSanguineoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tipoSanguineoMapper.fromId(null)).isNull();
    }
}
