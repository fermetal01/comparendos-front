package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class ComparendoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComparendoDTO.class);
        ComparendoDTO comparendoDTO1 = new ComparendoDTO();
        comparendoDTO1.setId("id1");
        ComparendoDTO comparendoDTO2 = new ComparendoDTO();
        assertThat(comparendoDTO1).isNotEqualTo(comparendoDTO2);
        comparendoDTO2.setId(comparendoDTO1.getId());
        assertThat(comparendoDTO1).isEqualTo(comparendoDTO2);
        comparendoDTO2.setId("id2");
        assertThat(comparendoDTO1).isNotEqualTo(comparendoDTO2);
        comparendoDTO1.setId(null);
        assertThat(comparendoDTO1).isNotEqualTo(comparendoDTO2);
    }
}
