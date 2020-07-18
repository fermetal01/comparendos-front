package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class EstadoComparendoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadoComparendoDTO.class);
        EstadoComparendoDTO estadoComparendoDTO1 = new EstadoComparendoDTO();
        estadoComparendoDTO1.setId("id1");
        EstadoComparendoDTO estadoComparendoDTO2 = new EstadoComparendoDTO();
        assertThat(estadoComparendoDTO1).isNotEqualTo(estadoComparendoDTO2);
        estadoComparendoDTO2.setId(estadoComparendoDTO1.getId());
        assertThat(estadoComparendoDTO1).isEqualTo(estadoComparendoDTO2);
        estadoComparendoDTO2.setId("id2");
        assertThat(estadoComparendoDTO1).isNotEqualTo(estadoComparendoDTO2);
        estadoComparendoDTO1.setId(null);
        assertThat(estadoComparendoDTO1).isNotEqualTo(estadoComparendoDTO2);
    }
}
