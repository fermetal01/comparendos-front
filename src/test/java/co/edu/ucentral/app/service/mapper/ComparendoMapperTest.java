package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ComparendoMapperTest {

    private ComparendoMapper comparendoMapper;

    @BeforeEach
    public void setUp() {
        comparendoMapper = new ComparendoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(comparendoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(comparendoMapper.fromId(null)).isNull();
    }
}
