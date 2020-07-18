package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class VehiculoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VehiculoDTO.class);
        VehiculoDTO vehiculoDTO1 = new VehiculoDTO();
        vehiculoDTO1.setId("id1");
        VehiculoDTO vehiculoDTO2 = new VehiculoDTO();
        assertThat(vehiculoDTO1).isNotEqualTo(vehiculoDTO2);
        vehiculoDTO2.setId(vehiculoDTO1.getId());
        assertThat(vehiculoDTO1).isEqualTo(vehiculoDTO2);
        vehiculoDTO2.setId("id2");
        assertThat(vehiculoDTO1).isNotEqualTo(vehiculoDTO2);
        vehiculoDTO1.setId(null);
        assertThat(vehiculoDTO1).isNotEqualTo(vehiculoDTO2);
    }
}
