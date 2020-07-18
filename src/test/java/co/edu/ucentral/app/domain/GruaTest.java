package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class GruaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Grua.class);
        Grua grua1 = new Grua();
        grua1.setId("id1");
        Grua grua2 = new Grua();
        grua2.setId(grua1.getId());
        assertThat(grua1).isEqualTo(grua2);
        grua2.setId("id2");
        assertThat(grua1).isNotEqualTo(grua2);
        grua1.setId(null);
        assertThat(grua1).isNotEqualTo(grua2);
    }
}
