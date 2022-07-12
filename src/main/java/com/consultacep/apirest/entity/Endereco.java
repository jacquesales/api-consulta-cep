package com.consultacep.apirest.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_endereco_jac")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "cep")
    private String code;

    @Column(name = "endereco")
    private String address;

    @Column(name = "cidade")
    private String city;

    @Column(name = "uf")
    private String state;

    @OneToOne
    @JsonIgnore
    private Contato contato;
}
