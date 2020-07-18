package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class OrganismoTransitoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganismoTransitoDTO.class);
        OrganismoTransitoDTO organismoTransitoDTO1 = new OrganismoTransitoDTO();
        organismoTransitoDTO1.setId("id1");
        OrganismoTransitoDTO organismoTransitoDTO2 = new OrganismoTransitoDTO();
        assertThat(organismoTransitoDTO1).isNotEqualTo(organismoTransitoDTO2);
        organismoTransitoDTO2.setId(organismoTransitoDTO1.getId());
        assertThat(organismoTransitoDTO1).isEqualTo(organismoTransitoDTO2);
        organismoTransitoDTO2.setId("id2");
        assertThat(organismoTransitoDTO1).isNotEqualTo(organismoTransitoDTO2);
        organismoTransitoDTO1.setId(null);
        assertThat(organismoTransitoDTO1).isNotEqualTo(organismoTransitoDTO2);
    }
}
