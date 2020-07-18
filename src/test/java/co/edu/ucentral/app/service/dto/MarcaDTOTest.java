package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class MarcaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MarcaDTO.class);
        MarcaDTO marcaDTO1 = new MarcaDTO();
        marcaDTO1.setId("id1");
        MarcaDTO marcaDTO2 = new MarcaDTO();
        assertThat(marcaDTO1).isNotEqualTo(marcaDTO2);
        marcaDTO2.setId(marcaDTO1.getId());
        assertThat(marcaDTO1).isEqualTo(marcaDTO2);
        marcaDTO2.setId("id2");
        assertThat(marcaDTO1).isNotEqualTo(marcaDTO2);
        marcaDTO1.setId(null);
        assertThat(marcaDTO1).isNotEqualTo(marcaDTO2);
    }
}
