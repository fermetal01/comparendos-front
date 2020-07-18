package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class GruaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GruaDTO.class);
        GruaDTO gruaDTO1 = new GruaDTO();
        gruaDTO1.setId("id1");
        GruaDTO gruaDTO2 = new GruaDTO();
        assertThat(gruaDTO1).isNotEqualTo(gruaDTO2);
        gruaDTO2.setId(gruaDTO1.getId());
        assertThat(gruaDTO1).isEqualTo(gruaDTO2);
        gruaDTO2.setId("id2");
        assertThat(gruaDTO1).isNotEqualTo(gruaDTO2);
        gruaDTO1.setId(null);
        assertThat(gruaDTO1).isNotEqualTo(gruaDTO2);
    }
}
