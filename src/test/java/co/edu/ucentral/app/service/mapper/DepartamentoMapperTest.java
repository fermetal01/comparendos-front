package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DepartamentoMapperTest {

    private DepartamentoMapper departamentoMapper;

    @BeforeEach
    public void setUp() {
        departamentoMapper = new DepartamentoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(departamentoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(departamentoMapper.fromId(null)).isNull();
    }
}
