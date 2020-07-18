package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class EstadoComparendoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadoComparendo.class);
        EstadoComparendo estadoComparendo1 = new EstadoComparendo();
        estadoComparendo1.setId("id1");
        EstadoComparendo estadoComparendo2 = new EstadoComparendo();
        estadoComparendo2.setId(estadoComparendo1.getId());
        assertThat(estadoComparendo1).isEqualTo(estadoComparendo2);
        estadoComparendo2.setId("id2");
        assertThat(estadoComparendo1).isNotEqualTo(estadoComparendo2);
        estadoComparendo1.setId(null);
        assertThat(estadoComparendo1).isNotEqualTo(estadoComparendo2);
    }
}
