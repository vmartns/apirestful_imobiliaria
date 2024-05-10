package br.com.descomplica.projeto.diferencialapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Visita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visita_id")
    private Integer visitaId;

    @Column(name = "visita_cliente") String visitaCliente;

    @ManyToOne
    @JoinColumn(name = "agente_id")
    public Agente agente;

    public Integer getVisitaId() {
        return visitaId;
    }

    public void setVisitaId(Integer visitaId) {
        this.visitaId = visitaId;
    }

    public String getVisitaNome() {
        return visitaCliente;
    }

}
