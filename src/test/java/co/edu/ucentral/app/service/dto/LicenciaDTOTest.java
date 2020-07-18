package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class LicenciaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LicenciaDTO.class);
        LicenciaDTO licenciaDTO1 = new LicenciaDTO();
        licenciaDTO1.setId("id1");
        LicenciaDTO licenciaDTO2 = new LicenciaDTO();
        assertThat(licenciaDTO1).isNotEqualTo(licenciaDTO2);
        licenciaDTO2.setId(licenciaDTO1.getId());
        assertThat(licenciaDTO1).isEqualTo(licenciaDTO2);
        licenciaDTO2.setId("id2");
        assertThat(licenciaDTO1).isNotEqualTo(licenciaDTO2);
        licenciaDTO1.setId(null);
        assertThat(licenciaDTO1).isNotEqualTo(licenciaDTO2);
    }
}
