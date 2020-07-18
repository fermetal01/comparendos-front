package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneroMapperTest {

    private GeneroMapper generoMapper;

    @BeforeEach
    public void setUp() {
        generoMapper = new GeneroMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(generoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(generoMapper.fromId(null)).isNull();
    }
}
