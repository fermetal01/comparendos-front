package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class CiudadTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ciudad.class);
        Ciudad ciudad1 = new Ciudad();
        ciudad1.setId("id1");
        Ciudad ciudad2 = new Ciudad();
        ciudad2.setId(ciudad1.getId());
        assertThat(ciudad1).isEqualTo(ciudad2);
        ciudad2.setId("id2");
        assertThat(ciudad1).isNotEqualTo(ciudad2);
        ciudad1.setId(null);
        assertThat(ciudad1).isNotEqualTo(ciudad2);
    }
}
