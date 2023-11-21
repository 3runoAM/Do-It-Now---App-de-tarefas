public class TarefaValidator {
    public void validarInsercao(Tarefa tarefa){
        if (tarefa.getDescricao().length() > 20){
            throw new IllegalArgumentException("Título excede o tamanho máximo.");
        }
    }
}
