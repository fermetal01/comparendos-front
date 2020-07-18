package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class OrganismoTransitoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganismoTransito.class);
        OrganismoTransito organismoTransito1 = new OrganismoTransito();
        organismoTransito1.setId("id1");
        OrganismoTransito organismoTransito2 = new OrganismoTransito();
        organismoTransito2.setId(organismoTransito1.getId());
        assertThat(organismoTransito1).isEqualTo(organismoTransito2);
        organismoTransito2.setId("id2");
        assertThat(organismoTransito1).isNotEqualTo(organismoTransito2);
        organismoTransito1.setId(null);
        assertThat(organismoTransito1).isNotEqualTo(organismoTransito2);
    }
}
