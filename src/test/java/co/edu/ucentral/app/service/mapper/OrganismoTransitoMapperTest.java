package co.edu.ucentral.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OrganismoTransitoMapperTest {

    private OrganismoTransitoMapper organismoTransitoMapper;

    @BeforeEach
    public void setUp() {
        organismoTransitoMapper = new OrganismoTransitoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(organismoTransitoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(organismoTransitoMapper.fromId(null)).isNull();
    }
}
