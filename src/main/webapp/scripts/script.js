function carregar(id, descricao, concluido) {
    let modal = document.getElementById('modal-editar-tarefa');
    let inputs = modal.getElementsByClassName('form-control');
    inputs[0].value = 'PUT';
    inputs[1].value = id;
    inputs[2].value = descricao;
}