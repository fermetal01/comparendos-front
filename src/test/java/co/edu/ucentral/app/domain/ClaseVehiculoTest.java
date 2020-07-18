package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class ClaseVehiculoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaseVehiculo.class);
        ClaseVehiculo claseVehiculo1 = new ClaseVehiculo();
        claseVehiculo1.setId("id1");
        ClaseVehiculo claseVehiculo2 = new ClaseVehiculo();
        claseVehiculo2.setId(claseVehiculo1.getId());
        assertThat(claseVehiculo1).isEqualTo(claseVehiculo2);
        claseVehiculo2.setId("id2");
        assertThat(claseVehiculo1).isNotEqualTo(claseVehiculo2);
        claseVehiculo1.setId(null);
        assertThat(claseVehiculo1).isNotEqualTo(claseVehiculo2);
    }
}
