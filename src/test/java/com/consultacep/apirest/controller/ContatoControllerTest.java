package com.consultacep.apirest.controller;

import com.consultacep.apirest.service.impl.GerenciamentoDeContatoServiceImpl;
import com.consultacep.apirest.entity.Contato;
import com.consultacep.apirest.entity.Endereco;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContatoControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GerenciamentoDeContatoServiceImpl service;


    private final String URL_TEMPLATE = "/contato";
    private static final String EXCEPTION_MESSAGE = "Email não existe";
    public static Contato contato, contatoUpdated;

    @BeforeAll
    public static void setup() {
        contato = Contato.builder()
                .email("email@email.com")
                .nome("Maria")
                .telefone("1191234-5678")
                .endereco(new Endereco(1,"01008-000", "Rua Líbero Badaró - lado par","São Paulo","SP",null))
                .dataDeCadastro(new Date())
                .build();

        contatoUpdated = Contato.builder()
                .email("email@email.com")
                .nome("Maria UPDATED")
                .telefone("1191234-5678")
                .endereco(new Endereco(1,"01008-000", "Rua Líbero Badaró - lado par","São Paulo","SP",null))
                .dataDeCadastro(contato.getDataDeCadastro())
                .build();
    }


    @Test
    public void shouldCreateAContato() throws Exception {
        when(service.create((contato))).thenReturn(contato);
        mockMvc.perform(post(URL_TEMPLATE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contato))
                )
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldReturnAContatoByIdTest() throws Exception {
        when(service.findById(contato.getEmail()))
                .thenReturn(contato);
        mockMvc.perform(get(URL_TEMPLATE + "/email@email.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("email").value("email@email.com"))
                .andExpect(jsonPath("nome").value("Maria"))
                .andExpect(jsonPath("telefone").value("1191234-5678"))
                .andExpect(jsonPath("endereco.code").value("01008-000"))
                .andExpect(jsonPath("endereco.address").value("Rua Líbero Badaró - lado par"))
                .andExpect(jsonPath("endereco.city").value("São Paulo"))
                .andExpect(jsonPath("endereco.state").value("SP"));
    }

    @Test
    public void shouldUpdateAContato() throws Exception {
        when(service.findById(contato.getEmail())).thenReturn(contato);
        when(service.update(contato)).thenReturn(contatoUpdated);
        mockMvc.perform(put("/contato")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contato)))
                .andExpect(status().isOk());
    }
}
