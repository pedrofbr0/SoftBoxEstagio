function removeProduto(id) {
    $('#mensagem')
        .load('remove' + 
        '?produto.id=' + id);
    $('#produto' + id).remove();
}

function adicionaProduto() {
    $('#mensagem')
        .load('formulario');
    $('#erro').hide();
};