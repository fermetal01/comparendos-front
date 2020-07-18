package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class LicenciaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Licencia.class);
        Licencia licencia1 = new Licencia();
        licencia1.setId("id1");
        Licencia licencia2 = new Licencia();
        licencia2.setId(licencia1.getId());
        assertThat(licencia1).isEqualTo(licencia2);
        licencia2.setId("id2");
        assertThat(licencia1).isNotEqualTo(licencia2);
        licencia1.setId(null);
        assertThat(licencia1).isNotEqualTo(licencia2);
    }
}
