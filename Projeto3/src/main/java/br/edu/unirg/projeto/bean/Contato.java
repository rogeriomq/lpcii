package br.edu.unirg.projeto.bean;

public class Contato extends TipoContato{
    private int tipo;
    private String descricao;
    private boolean preferencial;

    public Contato() {
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
    
    @Override
    public String toString() {
        return "Contato{" + "tipo=" + tipo + ", descricao=" + descricao + ", preferencial=" + preferencial + '}';
    }
    
    
}
