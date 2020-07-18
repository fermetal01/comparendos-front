package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MarcaMapperTest {

    private MarcaMapper marcaMapper;

    @BeforeEach
    public void setUp() {
        marcaMapper = new MarcaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(marcaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(marcaMapper.fromId(null)).isNull();
    }
}
