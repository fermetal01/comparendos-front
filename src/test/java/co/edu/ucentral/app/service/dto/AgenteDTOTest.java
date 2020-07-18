package co.edu.ucentral.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import co.edu.ucentral.app.web.rest.TestUtil;

public class AgenteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AgenteDTO.class);
        AgenteDTO agenteDTO1 = new AgenteDTO();
        agenteDTO1.setId("id1");
        AgenteDTO agenteDTO2 = new AgenteDTO();
        assertThat(agenteDTO1).isNotEqualTo(agenteDTO2);
        agenteDTO2.setId(agenteDTO1.getId());
        assertThat(agenteDTO1).isEqualTo(agenteDTO2);
        agenteDTO2.setId("id2");
        assertThat(agenteDTO1).isNotEqualTo(agenteDTO2);
        agenteDTO1.setId(null);
        assertThat(agenteDTO1).isNotEqualTo(agenteDTO2);
    }
}
