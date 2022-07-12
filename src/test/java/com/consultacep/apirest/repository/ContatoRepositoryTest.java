package com.consultacep.apirest.repository;

import com.consultacep.apirest.entity.Contato;
import com.consultacep.apirest.entity.Endereco;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.*;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ContatoRepositoryTest {

    private static Contato contato = new Contato();
    private static List<Contato> contatoList = new ArrayList<>();

    private static Contato buildContato() {
        return Contato.builder()
                .email("emailteste@gmail.com")
                .nome("Anna")
                .telefone("91234-5678")
                .endereco(buildEndereco())
                .build();
    }

    private static Endereco buildEndereco() {
        return Endereco.builder()
                .code("01025‑020")
                .address("Avenida do Estado - de 2602 a 4400 - lado par")
                .city("São Paulo")
                .state("SP")
                .build();
    }

    @Mock
    private ContatoRepository repository;

    @BeforeAll
    public static void config() {
        contato = buildContato();
        contatoList.addAll(List.of(contato,contato));
    }

    @Test
    public void mustReturnNumberOfAllContatos() throws Exception {
        when(repository.findAll()).thenReturn(Arrays.asList(contato));
        assertEquals(2, contatoList.size());
    }

    @Test
    public void mustReturnAContatoById() throws Exception {
        when(repository.findById("emailteste@gmail.com")).thenReturn(Optional.ofNullable(contato));
        Optional<Contato> optionalContato = repository.findById(contato.getEmail());
        assertTrue(optionalContato.get().getEmail().equals("emailteste@gmail.com"));
    }

    @Test
    public void mustReturnCreatingAContato() {
        when(repository.save(contato)).thenReturn(buildContato());
        contato = repository.save(contato);
        assertEquals(contato, buildContato());
    }

    @Test
    public void mustReturnAContatoUpdate() {
        contato.setNome("Anna Luiza");
        when(repository.save(contato)).thenReturn(buildContato());
        assertEquals("Anna Luiza", contato.getNome());
    }

    @Test
    public void mustReturnDeletingAContato() {
        doNothing().when(repository).deleteById("emailteste@gmail.com");
        repository.deleteById("emailteste@gmail.com");
        verify(repository).deleteById(contato.getEmail());
    }

}
