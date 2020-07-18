package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoriaMapperTest {

    private CategoriaMapper categoriaMapper;

    @BeforeEach
    public void setUp() {
        categoriaMapper = new CategoriaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(categoriaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(categoriaMapper.fromId(null)).isNull();
    }
}
