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

function prepararEdicao(id, descricao, concluido) {
    let modal = document.getElementById('modal-editar-tarefa');
    let inputs = modal.getElementsByClassName('form-control');
    inputs[0].value = 'PUT';
    inputs[1].value = id;
    inputs[2].value = descricao;
}

function prepararDelecao(id) {
    let modal = document.getElementById('modal-apagar-tarefa');
    let inputs = modal.getElementsByClassName('form-control');
    inputs[0].value = 'DELETE';
    inputs[1].value = id;
}

function atualizarConclusao(id){
    fetch('http://localhost:8080/conclusao?id=' + id, {
        method: 'PUT'
    }).catch(error => {
        alert('Ocorreu um erro ao concluir tarefa:' + error);
    });
}

marcarConclusao();