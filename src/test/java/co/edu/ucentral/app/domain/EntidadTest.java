package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class EntidadTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Entidad.class);
        Entidad entidad1 = new Entidad();
        entidad1.setId("id1");
        Entidad entidad2 = new Entidad();
        entidad2.setId(entidad1.getId());
        assertThat(entidad1).isEqualTo(entidad2);
        entidad2.setId("id2");
        assertThat(entidad1).isNotEqualTo(entidad2);
        entidad1.setId(null);
        assertThat(entidad1).isNotEqualTo(entidad2);
    }
}
