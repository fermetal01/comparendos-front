package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class TipoLicenciaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoLicencia.class);
        TipoLicencia tipoLicencia1 = new TipoLicencia();
        tipoLicencia1.setId("id1");
        TipoLicencia tipoLicencia2 = new TipoLicencia();
        tipoLicencia2.setId(tipoLicencia1.getId());
        assertThat(tipoLicencia1).isEqualTo(tipoLicencia2);
        tipoLicencia2.setId("id2");
        assertThat(tipoLicencia1).isNotEqualTo(tipoLicencia2);
        tipoLicencia1.setId(null);
        assertThat(tipoLicencia1).isNotEqualTo(tipoLicencia2);
    }
}
