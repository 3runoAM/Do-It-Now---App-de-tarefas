const botao = document.getElementById("concluido");
const paragrafo = document.getElementById("descricao");
const tarefaConteiner = document.getElementById("tarefa")
botao.addEventListener('change', function() {
    if(this.checked){
        paragrafo.classList.add("conclusao");
        tarefaConteiner.classList.add("tarefaConcluida")
    } else {
        paragrafo.classList.remove("conclusao")
        tarefaConteiner.classList.remove("tarefaConcluida")
    }
})
