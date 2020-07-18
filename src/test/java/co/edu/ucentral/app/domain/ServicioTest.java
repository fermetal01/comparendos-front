package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class ServicioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Servicio.class);
        Servicio servicio1 = new Servicio();
        servicio1.setId("id1");
        Servicio servicio2 = new Servicio();
        servicio2.setId(servicio1.getId());
        assertThat(servicio1).isEqualTo(servicio2);
        servicio2.setId("id2");
        assertThat(servicio1).isNotEqualTo(servicio2);
        servicio1.setId(null);
        assertThat(servicio1).isNotEqualTo(servicio2);
    }
}
