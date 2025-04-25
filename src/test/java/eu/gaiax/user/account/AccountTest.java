package eu.gaiax.user.account;

import eu.gaiax.user.account.model.CredentialUser;
import eu.gaiax.user.account.model.ProviderAccountDetails;
import eu.gaiax.user.account.model.UserRegistrationRequest;
import eu.gaiax.user.account.rest.PprRest;
import eu.gaiax.user.account.rest.ProviderAccountRest;
import eu.gaiax.user.account.rest.UserAccountRest;
import eu.gaiax.user.account.service.SdService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("unused")
@SpringBootTest(properties = {
        "check-keycloak-token=false"
})
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles({"test"})
public class AccountTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProviderAccountRest providerRest;
    @MockBean
    private UserAccountRest userRest;
    @MockBean
    private PprRest pprRest;
    @MockBean
    private SdService sdService;

    @Test
    void getProviderAccountData() throws Exception {
        ProviderAccountDetails rs = new ProviderAccountDetails(null,
                null, null, null, null, null,
                null, null, null, null, null,
                null, null, null);
        when(providerRest.getProviderAccountData(any())).thenReturn(rs);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/provider"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProviderAccountData() throws Exception {
        ResponseEntity rs = ResponseEntity.ok().build();
        when(providerRest.deleteProviderAccountData()).thenReturn(rs);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/account/provider"))
                .andExpect(status().isOk());
    }

    @Ignore
    @Test
    void updateProviderAccountData() throws Exception {
        ResponseEntity rs = ResponseEntity.ok().build();
        ProviderAccountDetails rq = new ProviderAccountDetails(
                "link", null, null, null, null, null, null,
                null, null, null, null, null, null, null);
        when(providerRest.updateProviderSD(any(), any(), any())).thenReturn(rs);
        MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .multipart("/api/account/provider")
                                .file(firstFile))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUserAccountData() throws Exception {
        UserRegistrationRequest rs = new UserRegistrationRequest();
        when(userRest.getUserAccountData(any())).thenReturn(rs);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/user")).andExpect(status().isOk());
    }

    @Test
    void getUsers() throws Exception {
        ResponseEntity rs = ResponseEntity.ok().build();
        when(pprRest.getUsers(any())).thenReturn(rs);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/provider/users")).andExpect(status().isOk());
    }

    @Test
    void updateUsers() throws Exception {
        ResponseEntity rs = ResponseEntity.ok().build();
        CredentialUser rq = new CredentialUser();
        when(pprRest.updateUsers(any(), any())).thenReturn(rs);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/account/provider/users/11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.asJsonString(rq)))
                .andExpect(status().isOk());
    }

    @Test
    void addUsers() throws Exception {
        ResponseEntity rs = ResponseEntity.ok().build();
        CredentialUser rq = new CredentialUser();
        when(pprRest.addUsers(any(), any())).thenReturn(rs);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/account/provider/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.asJsonString(rq)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUsers() throws Exception {
        ResponseEntity rs = ResponseEntity.ok().build();
        CredentialUser rq = new CredentialUser();
        when(pprRest.addUsers(any(), any())).thenReturn(rs);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/account/provider/users/11"))
                .andExpect(status().isOk());
    }

}
