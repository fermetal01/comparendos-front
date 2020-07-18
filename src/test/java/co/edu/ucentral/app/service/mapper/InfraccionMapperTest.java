package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InfraccionMapperTest {

    private InfraccionMapper infraccionMapper;

    @BeforeEach
    public void setUp() {
        infraccionMapper = new InfraccionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(infraccionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(infraccionMapper.fromId(null)).isNull();
    }
}
