package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class InfraccionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Infraccion.class);
        Infraccion infraccion1 = new Infraccion();
        infraccion1.setId("id1");
        Infraccion infraccion2 = new Infraccion();
        infraccion2.setId(infraccion1.getId());
        assertThat(infraccion1).isEqualTo(infraccion2);
        infraccion2.setId("id2");
        assertThat(infraccion1).isNotEqualTo(infraccion2);
        infraccion1.setId(null);
        assertThat(infraccion1).isNotEqualTo(infraccion2);
    }
}
