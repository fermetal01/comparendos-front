package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class CombustibleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Combustible.class);
        Combustible combustible1 = new Combustible();
        combustible1.setId("id1");
        Combustible combustible2 = new Combustible();
        combustible2.setId(combustible1.getId());
        assertThat(combustible1).isEqualTo(combustible2);
        combustible2.setId("id2");
        assertThat(combustible1).isNotEqualTo(combustible2);
        combustible1.setId(null);
        assertThat(combustible1).isNotEqualTo(combustible2);
    }
}
