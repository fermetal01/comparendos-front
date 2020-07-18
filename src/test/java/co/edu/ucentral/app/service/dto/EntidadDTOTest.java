package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class EntidadDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntidadDTO.class);
        EntidadDTO entidadDTO1 = new EntidadDTO();
        entidadDTO1.setId("id1");
        EntidadDTO entidadDTO2 = new EntidadDTO();
        assertThat(entidadDTO1).isNotEqualTo(entidadDTO2);
        entidadDTO2.setId(entidadDTO1.getId());
        assertThat(entidadDTO1).isEqualTo(entidadDTO2);
        entidadDTO2.setId("id2");
        assertThat(entidadDTO1).isNotEqualTo(entidadDTO2);
        entidadDTO1.setId(null);
        assertThat(entidadDTO1).isNotEqualTo(entidadDTO2);
    }
}
