package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class MarcaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Marca.class);
        Marca marca1 = new Marca();
        marca1.setId("id1");
        Marca marca2 = new Marca();
        marca2.setId(marca1.getId());
        assertThat(marca1).isEqualTo(marca2);
        marca2.setId("id2");
        assertThat(marca1).isNotEqualTo(marca2);
        marca1.setId(null);
        assertThat(marca1).isNotEqualTo(marca2);
    }
}
