package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class VehiculoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vehiculo.class);
        Vehiculo vehiculo1 = new Vehiculo();
        vehiculo1.setId("id1");
        Vehiculo vehiculo2 = new Vehiculo();
        vehiculo2.setId(vehiculo1.getId());
        assertThat(vehiculo1).isEqualTo(vehiculo2);
        vehiculo2.setId("id2");
        assertThat(vehiculo1).isNotEqualTo(vehiculo2);
        vehiculo1.setId(null);
        assertThat(vehiculo1).isNotEqualTo(vehiculo2);
    }
}
