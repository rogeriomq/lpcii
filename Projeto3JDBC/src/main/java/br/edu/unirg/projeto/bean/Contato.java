package br.edu.unirg.projeto.bean;

public class Contato extends TipoContato {

    private String id;
    private int tipo;
    private String descricao;
    private boolean preferencial;
    private String registro_id;

    public Contato() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isPreferencial() {
        return preferencial;
    }

    public void setPreferencial(boolean preferencial) {
        this.preferencial = preferencial;
    }

    public String getRegistro_id() {
        return registro_id;
    }

    public void setRegistro_id(String registro_id) {
        this.registro_id = registro_id;
    }

    @Override
    public String toString() {
        return "Contato{" + "id=" + id + ", tipo=" + tipo + ", descricao=" + descricao + ", preferencial=" + preferencial + ", registro_id=" + registro_id + '}';
    }
}
