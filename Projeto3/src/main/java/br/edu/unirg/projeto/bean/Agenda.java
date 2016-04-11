package br.edu.unirg.projeto.bean;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Agenda {
    private String id;
    private String nome;
    private String sobrenome;
    private String apelido;
    private Date aniversario;
    private String endereco;
    private List<Contato> contatos; 

    public Agenda() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Date getAniversario() {
        return aniversario;
    }

    public void setAniversario(Date aniversario) {
        this.aniversario = aniversario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agenda other = (Agenda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agenda{" + "id=" + id + ", \n"
                + "nome=" + nome + ", \n"
                + "sobrenome=" + sobrenome + ", \n"
                + "apelido=" + apelido + ", \n"
                + "aniversario=" + aniversario + ", \n"
                + "endereco=" + endereco + ", \n"
                + "contatos=" + contatos + 
                "}";
    }
}
