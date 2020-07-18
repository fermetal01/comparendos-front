package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class ServicioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServicioDTO.class);
        ServicioDTO servicioDTO1 = new ServicioDTO();
        servicioDTO1.setId("id1");
        ServicioDTO servicioDTO2 = new ServicioDTO();
        assertThat(servicioDTO1).isNotEqualTo(servicioDTO2);
        servicioDTO2.setId(servicioDTO1.getId());
        assertThat(servicioDTO1).isEqualTo(servicioDTO2);
        servicioDTO2.setId("id2");
        assertThat(servicioDTO1).isNotEqualTo(servicioDTO2);
        servicioDTO1.setId(null);
        assertThat(servicioDTO1).isNotEqualTo(servicioDTO2);
    }
}
