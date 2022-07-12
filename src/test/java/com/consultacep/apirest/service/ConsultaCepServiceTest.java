package com.consultacep.apirest.service;

import com.consultacep.apirest.entity.Endereco;
import com.consultacep.apirest.repository.ContatoRepository;
import com.consultacep.apirest.service.impl.ConsultaCepServiceImpl;
import com.consultacep.apirest.entity.Contato;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsultaCepServiceTest {

    private static Endereco enderecoToSave, enderecoSaved;
    private static Contato contatoToSave, contatoSaved;

    @Mock
    private ContatoRepository repository;

    @InjectMocks
    private ConsultaCepServiceImpl service;

    @BeforeAll
    private static void build() {
        contatoToSave = Contato.builder()
                .email("email@email.com")
                .nome("Maria")
                .telefone("91234-5678")
                .endereco(enderecoToSave)
                .dataDeCadastro(new Date())
                .build();

        contatoSaved = Contato.builder()
                .email("email@email.com")
                .nome("Maria")
                .telefone("91234-5678")
                .endereco(enderecoSaved)
                .dataDeCadastro(contatoToSave.getDataDeCadastro())
                .build();

        enderecoToSave = Endereco.builder()
                .code("01007-020")
                .address(null)
                .city(null)
                .state(null)
                .build();

        enderecoSaved = Endereco.builder()
                .code("01007-020")
                .address("Praça da Bandeira")
                .city("São Paulo")
                .state("SP")
                .build();
    }

    @Test
    public void shouldReturnAFullEnderecoTest() throws Exception {
        when(repository.save( any( Contato.class ))).thenReturn(contatoSaved);
        Endereco enderecoToTest = service.findByCep("01007-020", contatoToSave);
        assertEquals("Praça da Bandeira", enderecoToTest.getAddress());
        assertEquals("São Paulo", enderecoToTest.getCity());
        assertNotEquals("RJ", enderecoToTest.getState());

    }

}
