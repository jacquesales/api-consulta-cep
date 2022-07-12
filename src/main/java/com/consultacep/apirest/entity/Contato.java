package com.consultacep.apirest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contato_jac")
public class Contato implements Serializable {

    @Id
    private String email;

    @Column( nullable = false )
    private String nome;

    @Column( nullable = false )
    private String telefone;

    @OneToOne(mappedBy = "contato", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("contato")
    private Endereco endereco;

    @Column(name = "dh_cadastro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonDeserialize
    private Date dataDeCadastro;
}


