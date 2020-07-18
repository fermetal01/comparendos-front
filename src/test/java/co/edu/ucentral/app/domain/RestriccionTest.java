package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class RestriccionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Restriccion.class);
        Restriccion restriccion1 = new Restriccion();
        restriccion1.setId("id1");
        Restriccion restriccion2 = new Restriccion();
        restriccion2.setId(restriccion1.getId());
        assertThat(restriccion1).isEqualTo(restriccion2);
        restriccion2.setId("id2");
        assertThat(restriccion1).isNotEqualTo(restriccion2);
        restriccion1.setId(null);
        assertThat(restriccion1).isNotEqualTo(restriccion2);
    }
}
