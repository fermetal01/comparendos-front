package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class InfraccionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InfraccionDTO.class);
        InfraccionDTO infraccionDTO1 = new InfraccionDTO();
        infraccionDTO1.setId("id1");
        InfraccionDTO infraccionDTO2 = new InfraccionDTO();
        assertThat(infraccionDTO1).isNotEqualTo(infraccionDTO2);
        infraccionDTO2.setId(infraccionDTO1.getId());
        assertThat(infraccionDTO1).isEqualTo(infraccionDTO2);
        infraccionDTO2.setId("id2");
        assertThat(infraccionDTO1).isNotEqualTo(infraccionDTO2);
        infraccionDTO1.setId(null);
        assertThat(infraccionDTO1).isNotEqualTo(infraccionDTO2);
    }
}
