package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class TipoLicenciaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoLicenciaDTO.class);
        TipoLicenciaDTO tipoLicenciaDTO1 = new TipoLicenciaDTO();
        tipoLicenciaDTO1.setId("id1");
        TipoLicenciaDTO tipoLicenciaDTO2 = new TipoLicenciaDTO();
        assertThat(tipoLicenciaDTO1).isNotEqualTo(tipoLicenciaDTO2);
        tipoLicenciaDTO2.setId(tipoLicenciaDTO1.getId());
        assertThat(tipoLicenciaDTO1).isEqualTo(tipoLicenciaDTO2);
        tipoLicenciaDTO2.setId("id2");
        assertThat(tipoLicenciaDTO1).isNotEqualTo(tipoLicenciaDTO2);
        tipoLicenciaDTO1.setId(null);
        assertThat(tipoLicenciaDTO1).isNotEqualTo(tipoLicenciaDTO2);
    }
}
