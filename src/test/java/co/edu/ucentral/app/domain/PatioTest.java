package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class PatioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Patio.class);
        Patio patio1 = new Patio();
        patio1.setId("id1");
        Patio patio2 = new Patio();
        patio2.setId(patio1.getId());
        assertThat(patio1).isEqualTo(patio2);
        patio2.setId("id2");
        assertThat(patio1).isNotEqualTo(patio2);
        patio1.setId(null);
        assertThat(patio1).isNotEqualTo(patio2);
    }
}
