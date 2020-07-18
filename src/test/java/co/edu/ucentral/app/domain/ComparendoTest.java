package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class ComparendoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Comparendo.class);
        Comparendo comparendo1 = new Comparendo();
        comparendo1.setId("id1");
        Comparendo comparendo2 = new Comparendo();
        comparendo2.setId(comparendo1.getId());
        assertThat(comparendo1).isEqualTo(comparendo2);
        comparendo2.setId("id2");
        assertThat(comparendo1).isNotEqualTo(comparendo2);
        comparendo1.setId(null);
        assertThat(comparendo1).isNotEqualTo(comparendo2);
    }
}
