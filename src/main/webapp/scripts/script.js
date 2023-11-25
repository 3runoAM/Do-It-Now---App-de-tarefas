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

function carregarInformacoes(id, descricao, concluido) {
    let modal = document.getElementById('modal-editar-tarefa');
    let inputs = modal.getElementsByClassName('form-control');
    inputs[0].value = 'PUT';
    inputs[1].value = id;
    inputs[2].value = descricao;
}

marcarConclusao();