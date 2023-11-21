public class Tarefa {
    private Integer id;
    private String descricao;
    private boolean concluido;
    /*Constructor*/
    public Tarefa() {
    }

    public Tarefa(String descricao, boolean concluido) {
        this.descricao = descricao;
        this.concluido = concluido;
    }

    /**/
    public String getDescricao() {
        return descricao;
    }

    public Integer getId() {
        return id;
    }

    public boolean isConcluido() {
        return concluido;
    }
}
