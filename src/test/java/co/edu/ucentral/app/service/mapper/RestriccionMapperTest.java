package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RestriccionMapperTest {

    private RestriccionMapper restriccionMapper;

    @BeforeEach
    public void setUp() {
        restriccionMapper = new RestriccionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(restriccionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(restriccionMapper.fromId(null)).isNull();
    }
}
