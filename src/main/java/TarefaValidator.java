public class TarefaValidator {
    /**
     * Método para validar a inserção de uma tarefa no banco de dados.
     *
     * @param tarefa A tarefa que será validada antes da inserção.
     * @throws IllegalArgumentException Se a descrição da tarefa exceder o tamanho máximo permitido.
     */
    public void validarInsercao(Tarefa tarefa){
        if (tarefa.getDescricao().length() > 20 || tarefa.getDescricao().length() < 2){
            throw new IllegalArgumentException("Título excede o tamanho máximo.");
        }
    }
}
