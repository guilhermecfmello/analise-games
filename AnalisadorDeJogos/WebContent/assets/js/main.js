function showDatepicker() {
    var $datepicker = $('.datepicker');
    if ($datepicker.length > 0) {
        $datepicker.datepicker({
            language: 'pt-BR',
            format: 'dd/mm/yyyy',
            autoclose: true
        });
    }
}

function showErrors() {
    var $errorMsg = $('.error-message');
    if ($errorMsg.length > 0) {
        $errorMsg.alert();
    }

    var $rollbackMsg = $('.rollback-error-message');
    if ($rollbackMsg.length > 0) {
        $rollbackMsg.alert();
    }
}

function deleteUser(e) {
    e.preventDefault();
    $('.link_confirmacao_excluir_usuario').attr('href', $(this).data('href'));
    $('.modal_excluir_usuario').modal();
}

function deleteUsers(e) {
    e.preventDefault();
    $('.form_excluir_usuarios').submit();
}

function readUser(e) {
    e.preventDefault();
    $.get($(this).data('href'), function (data) {
        var usuario = JSON.parse(data);
        var $modal = $('.modal-visualizar-usuario');

        $modal.find('.p_id').html('<strong>ID: </strong>' + usuario.id);
        $modal.find(".p_login").html('<strong>Login: </strong>' + usuario.login);
        $modal.find('.p_nome').html('<strong>Nome: </strong>' + usuario.nome);
        $modal.find('.p_nascimento').html('<strong>Data de nascimento: </strong>' + usuario.nascimento);
        if (usuario.avatar) {
            $modal.find('.usuario-img').prop('src', $.url(usuario.avatar));
        }
        else {
            $modal.find('.usuario-img').prop('src', $.url('/assets/img/default_avatar.png'));
        }

        $modal.modal();
    });
}

$(document).on('focusout', '.password-input,.password-confirm', function(e) {
    var $form = $(this).closest("form");
    var $password = $form.find(".password-input");
    var $passwordConfirm = $form.find(".password-confirm");

    if ($password.val().trim() == '') {
        return false;
    }

    if ($password.val() !== $passwordConfirm.val()) {
        $passwordConfirm.closest('.form-group').addClass('has-error');
        $password.closest('.form-group').addClass('has-error');
        $passwordConfirm.next('p.help-block').html('<strong>Erro</strong>: as senhas não coincidem!');
        $form.find("button,input[type='submit']").prop('disabled', true);
    } else {
        $passwordConfirm.closest('.form-group').removeClass('has-error').addClass('has-success');
        $password.closest('.form-group').removeClass('has-error').addClass('has-success');
        $passwordConfirm.next('p.help-block').html('');
        $form.find("button,input[type='submit']").prop('disabled', false);
    }
});


$(document).ready(function () {
    showDatepicker();
//    showErrors();
    $(document).on('click', '.link_excluir_usuario', deleteUser);
    $(document).on('click', '.button_confirmacao_excluir_usuarios', deleteUsers);
    $(document).on('click', '.link_visualizar_usuario', readUser);
    $("*[data-toggle='tooltip']").tooltip({
        'container': 'body'
    });
});