package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class PatioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatioDTO.class);
        PatioDTO patioDTO1 = new PatioDTO();
        patioDTO1.setId("id1");
        PatioDTO patioDTO2 = new PatioDTO();
        assertThat(patioDTO1).isNotEqualTo(patioDTO2);
        patioDTO2.setId(patioDTO1.getId());
        assertThat(patioDTO1).isEqualTo(patioDTO2);
        patioDTO2.setId("id2");
        assertThat(patioDTO1).isNotEqualTo(patioDTO2);
        patioDTO1.setId(null);
        assertThat(patioDTO1).isNotEqualTo(patioDTO2);
    }
}
