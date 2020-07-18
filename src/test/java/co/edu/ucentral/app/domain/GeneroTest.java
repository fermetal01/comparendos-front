package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class GeneroTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Genero.class);
        Genero genero1 = new Genero();
        genero1.setId("id1");
        Genero genero2 = new Genero();
        genero2.setId(genero1.getId());
        assertThat(genero1).isEqualTo(genero2);
        genero2.setId("id2");
        assertThat(genero1).isNotEqualTo(genero2);
        genero1.setId(null);
        assertThat(genero1).isNotEqualTo(genero2);
    }
}
