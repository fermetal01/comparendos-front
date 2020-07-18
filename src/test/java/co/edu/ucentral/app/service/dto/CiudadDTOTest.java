package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class CiudadDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CiudadDTO.class);
        CiudadDTO ciudadDTO1 = new CiudadDTO();
        ciudadDTO1.setId("id1");
        CiudadDTO ciudadDTO2 = new CiudadDTO();
        assertThat(ciudadDTO1).isNotEqualTo(ciudadDTO2);
        ciudadDTO2.setId(ciudadDTO1.getId());
        assertThat(ciudadDTO1).isEqualTo(ciudadDTO2);
        ciudadDTO2.setId("id2");
        assertThat(ciudadDTO1).isNotEqualTo(ciudadDTO2);
        ciudadDTO1.setId(null);
        assertThat(ciudadDTO1).isNotEqualTo(ciudadDTO2);
    }
}
