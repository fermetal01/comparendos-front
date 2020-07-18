package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class TipoIdentificacionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoIdentificacion.class);
        TipoIdentificacion tipoIdentificacion1 = new TipoIdentificacion();
        tipoIdentificacion1.setId("id1");
        TipoIdentificacion tipoIdentificacion2 = new TipoIdentificacion();
        tipoIdentificacion2.setId(tipoIdentificacion1.getId());
        assertThat(tipoIdentificacion1).isEqualTo(tipoIdentificacion2);
        tipoIdentificacion2.setId("id2");
        assertThat(tipoIdentificacion1).isNotEqualTo(tipoIdentificacion2);
        tipoIdentificacion1.setId(null);
        assertThat(tipoIdentificacion1).isNotEqualTo(tipoIdentificacion2);
    }
}
