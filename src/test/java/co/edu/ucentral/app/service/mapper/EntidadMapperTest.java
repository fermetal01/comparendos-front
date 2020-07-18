package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EntidadMapperTest {

    private EntidadMapper entidadMapper;

    @BeforeEach
    public void setUp() {
        entidadMapper = new EntidadMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(entidadMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(entidadMapper.fromId(null)).isNull();
    }
}
