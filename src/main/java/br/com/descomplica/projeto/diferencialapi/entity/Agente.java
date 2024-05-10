package br.com.descomplica.projeto.diferencialapi.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "agente")
public class Agente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agente_id")
    private Integer id;

    @Column(name = "agente_data")
    private LocalTime data;
    
    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;
 
    @OneToMany(mappedBy = "agente")
    private List<Visita> visitas = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
    }

    public void setVisitaCliente(Visita visita, String string) {
        visita.visitaCliente = string;
    }


}