package br.com.descomplica.projeto.diferencialapi.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "imovel")
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imovel_id")
    private Integer id;

    @Column(name = "imovel_nome")
    private String nome;

    /**
     * List of Agentes associated with this Imovel.
     */
    @OneToMany(mappedBy = "imovel")
    @JsonBackReference
    private List<Agente> agentes;

    public void setId(Integer id2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }
}