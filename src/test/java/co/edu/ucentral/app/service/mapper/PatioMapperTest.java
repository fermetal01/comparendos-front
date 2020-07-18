package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PatioMapperTest {

    private PatioMapper patioMapper;

    @BeforeEach
    public void setUp() {
        patioMapper = new PatioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(patioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(patioMapper.fromId(null)).isNull();
    }
}
