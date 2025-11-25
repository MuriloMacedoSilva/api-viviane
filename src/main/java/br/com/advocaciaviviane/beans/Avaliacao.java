package br.com.advocaciaviviane.beans;

public class Avaliacao {

    private String id;
    private String nome;
    private String dia;
    private String mes;
    private String ano;
    private String avaliacao;
    private int nota;

    public Avaliacao() {
    }

    public Avaliacao(String id, String nome, String dia, String mes, String ano, String avaliacao, int nota) {
        this.id = id;
        this.nome = nome;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.avaliacao = avaliacao;
        this.nota = nota;
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dia='" + dia + '\'' +
                ", mes='" + mes + '\'' +
                ", ano='" + ano + '\'' +
                ", avaliacao='" + avaliacao + '\'' +
                ", nota=" + nota +
                '}';
    }
}
