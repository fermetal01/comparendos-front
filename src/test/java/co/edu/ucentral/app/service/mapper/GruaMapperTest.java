package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GruaMapperTest {

    private GruaMapper gruaMapper;

    @BeforeEach
    public void setUp() {
        gruaMapper = new GruaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(gruaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(gruaMapper.fromId(null)).isNull();
    }
}
