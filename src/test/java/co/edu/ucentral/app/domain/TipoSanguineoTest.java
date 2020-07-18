package co.edu.ucentral.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class TipoSanguineoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoSanguineo.class);
        TipoSanguineo tipoSanguineo1 = new TipoSanguineo();
        tipoSanguineo1.setId("id1");
        TipoSanguineo tipoSanguineo2 = new TipoSanguineo();
        tipoSanguineo2.setId(tipoSanguineo1.getId());
        assertThat(tipoSanguineo1).isEqualTo(tipoSanguineo2);
        tipoSanguineo2.setId("id2");
        assertThat(tipoSanguineo1).isNotEqualTo(tipoSanguineo2);
        tipoSanguineo1.setId(null);
        assertThat(tipoSanguineo1).isNotEqualTo(tipoSanguineo2);
    }
}
