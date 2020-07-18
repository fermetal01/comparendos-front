package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class ClaseVehiculoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaseVehiculoDTO.class);
        ClaseVehiculoDTO claseVehiculoDTO1 = new ClaseVehiculoDTO();
        claseVehiculoDTO1.setId("id1");
        ClaseVehiculoDTO claseVehiculoDTO2 = new ClaseVehiculoDTO();
        assertThat(claseVehiculoDTO1).isNotEqualTo(claseVehiculoDTO2);
        claseVehiculoDTO2.setId(claseVehiculoDTO1.getId());
        assertThat(claseVehiculoDTO1).isEqualTo(claseVehiculoDTO2);
        claseVehiculoDTO2.setId("id2");
        assertThat(claseVehiculoDTO1).isNotEqualTo(claseVehiculoDTO2);
        claseVehiculoDTO1.setId(null);
        assertThat(claseVehiculoDTO1).isNotEqualTo(claseVehiculoDTO2);
    }
}
