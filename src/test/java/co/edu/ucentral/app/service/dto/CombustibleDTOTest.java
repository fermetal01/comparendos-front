package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class CombustibleDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CombustibleDTO.class);
        CombustibleDTO combustibleDTO1 = new CombustibleDTO();
        combustibleDTO1.setId("id1");
        CombustibleDTO combustibleDTO2 = new CombustibleDTO();
        assertThat(combustibleDTO1).isNotEqualTo(combustibleDTO2);
        combustibleDTO2.setId(combustibleDTO1.getId());
        assertThat(combustibleDTO1).isEqualTo(combustibleDTO2);
        combustibleDTO2.setId("id2");
        assertThat(combustibleDTO1).isNotEqualTo(combustibleDTO2);
        combustibleDTO1.setId(null);
        assertThat(combustibleDTO1).isNotEqualTo(combustibleDTO2);
    }
}
