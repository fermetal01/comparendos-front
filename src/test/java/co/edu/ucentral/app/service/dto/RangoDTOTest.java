package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class RangoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RangoDTO.class);
        RangoDTO rangoDTO1 = new RangoDTO();
        rangoDTO1.setId("id1");
        RangoDTO rangoDTO2 = new RangoDTO();
        assertThat(rangoDTO1).isNotEqualTo(rangoDTO2);
        rangoDTO2.setId(rangoDTO1.getId());
        assertThat(rangoDTO1).isEqualTo(rangoDTO2);
        rangoDTO2.setId("id2");
        assertThat(rangoDTO1).isNotEqualTo(rangoDTO2);
        rangoDTO1.setId(null);
        assertThat(rangoDTO1).isNotEqualTo(rangoDTO2);
    }
}
