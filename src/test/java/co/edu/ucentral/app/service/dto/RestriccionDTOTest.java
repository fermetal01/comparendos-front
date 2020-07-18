package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class RestriccionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RestriccionDTO.class);
        RestriccionDTO restriccionDTO1 = new RestriccionDTO();
        restriccionDTO1.setId("id1");
        RestriccionDTO restriccionDTO2 = new RestriccionDTO();
        assertThat(restriccionDTO1).isNotEqualTo(restriccionDTO2);
        restriccionDTO2.setId(restriccionDTO1.getId());
        assertThat(restriccionDTO1).isEqualTo(restriccionDTO2);
        restriccionDTO2.setId("id2");
        assertThat(restriccionDTO1).isNotEqualTo(restriccionDTO2);
        restriccionDTO1.setId(null);
        assertThat(restriccionDTO1).isNotEqualTo(restriccionDTO2);
    }
}
