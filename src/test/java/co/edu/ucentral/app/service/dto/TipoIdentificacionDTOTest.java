package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class TipoIdentificacionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoIdentificacionDTO.class);
        TipoIdentificacionDTO tipoIdentificacionDTO1 = new TipoIdentificacionDTO();
        tipoIdentificacionDTO1.setId("id1");
        TipoIdentificacionDTO tipoIdentificacionDTO2 = new TipoIdentificacionDTO();
        assertThat(tipoIdentificacionDTO1).isNotEqualTo(tipoIdentificacionDTO2);
        tipoIdentificacionDTO2.setId(tipoIdentificacionDTO1.getId());
        assertThat(tipoIdentificacionDTO1).isEqualTo(tipoIdentificacionDTO2);
        tipoIdentificacionDTO2.setId("id2");
        assertThat(tipoIdentificacionDTO1).isNotEqualTo(tipoIdentificacionDTO2);
        tipoIdentificacionDTO1.setId(null);
        assertThat(tipoIdentificacionDTO1).isNotEqualTo(tipoIdentificacionDTO2);
    }
}
