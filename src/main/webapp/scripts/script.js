/**
 * Método para marcar a conclusão de uma tarefa.
 *
 * Este método obtém todos os botões com a classe 'botaoConcluido' e adiciona um evento de mudança a cada botão.
 * Quando o botão é alterado, o método verifica se o botão está marcado.
 * Se estiver marcado, a classe 'conclusao' é adicionada ao parágrafo com o ID 'descricao'.
 * Se não estiver marcado, a classe 'conclusao' é removida do parágrafo.
 */
function marcarConclusao(){
    const botoes = document.querySelectorAll(".botaoConcluido");
    botoes.forEach(botao => {
        botao.addEventListener('change', () => {
            const paragrafo = botao.parentNode.parentNode.querySelector("#descricao");
            if(botao.checked){
                paragrafo.classList.add("conclusao");
            } else {
                paragrafo.classList.remove("conclusao")
            }
        })
    })
}

/**
 * Método para preparar a edição de uma tarefa.
 *
 *  @param {number} id - O ID da tarefa a ser editada.
 *  @param {string} descricao - A descrição da tarefa a ser editada.
 *
 * Esté método seleciona o modal com o ID 'modal-editar-tarefa' e os inputs dentro desse modal que possuem
 * a classe 'form-control', então define o valor do primeiro input para 'PUT', do segundo para o id e
 * do terceiro para descricao.
 */

function prepararEdicao(id, descricao) {
    let modal = document.getElementById('modal-editar-tarefa');
    let inputs = modal.getElementsByClassName('form-control');
    inputs[0].value = 'PUT';
    inputs[1].value = id;
    inputs[2].value = descricao;
}

/**
 * Método para preparar a deleção de uma tarefa.
 *
 * @param {number} id - O ID da tarefa a ser deletada.
 *
 * Este método obtém o modal com o ID 'modal-apagar-tarefa' e os inputs
 * dentro deste modal que possuem a classe 'form-control', então define o valor
 * do primeiro input para 'DELETE' e o valor do segundo input para o ID da tarefa.
 */
function prepararDelecao(id) {
    let modal = document.getElementById('modal-apagar-tarefa');
    let inputs = modal.getElementsByClassName('form-control');
    inputs[0].value = 'DELETE';
    inputs[1].value = id;
}

/**
 * Método para atualizar a conclusão de uma tarefa.
 *
 * @param {number} id - O ID da tarefa a ser concluída.
 *
 * Este método faz uma requisição PUT para ConclusaoServlet com o ID da
 * tarefa como parâmetro. Se a requisição falhar, um alerta será exibido com
 * a mensagem de erro.
 */
function atualizarConclusao(id){
    fetch('http://localhost:8080/conclusao?id=' + id, {
        method: 'PUT'
    }).catch(error => {
        alert('Ocorreu um erro ao concluir tarefa:' + error);
    });
}

marcarConclusao();