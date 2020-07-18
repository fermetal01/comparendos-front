package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CombustibleMapperTest {

    private CombustibleMapper combustibleMapper;

    @BeforeEach
    public void setUp() {
        combustibleMapper = new CombustibleMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(combustibleMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(combustibleMapper.fromId(null)).isNull();
    }
}
