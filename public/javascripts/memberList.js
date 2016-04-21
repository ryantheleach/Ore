function updateIndices() {
    $('.list-members').find('.user-new').each(function(i) {
        $(this).find('input').attr('name', 'users[' + i + ']');
        $(this).find('select').attr('name', 'roles[' + i + ']');
    });
}

function getItemContainer(element) {
    return element.closest('.list-group-item');
}

function initMember(memberRow) {
    // Replace title with select on click
    memberRow.find('.fa-edit').parent().click(function(event) {
        event.preventDefault();
        getItemContainer($(this)).find('span').replaceWith($('#select-role').clone().show());
        $(this).find('.fa-edit').parent().remove();
    });

    // Set form input on modal when delete is clicked
    memberRow.find('.fa-trash').parent().click(function(event) {
        event.preventDefault();
        $('#modal-user-delete').find('input').val(getItemContainer($(this)).find('.username').text());
    });
}

$(function() {
    initMember($('.list-members').find('.list-group-item'));

    initUserSearch(function(result) {
        var alert = $('.alert-danger');
        var message = alert.find('span');
        if (!result.isSuccess) {
            message.text('Could not find user with name "' + result.username + '".');
            alert.fadeIn();
            return;
        }
        alert.fadeOut();

        var user = result.user;
        // Check if user is already defined
        if ($('.list-members').find('a[href="/' + user.username + '"]').length) {
            return;
        }

        // Build result row
        var resultRow = $('#row-user').clone().attr('id', '').addClass('user-new');
        console.log(resultRow.first('a'));
        resultRow.find('a').attr('href', '/' + user.username).text(user.username);
        resultRow.find('input').attr('form', 'save').val(user.id);
        resultRow.find('select').attr('form', 'save');
        resultRow.find('i').click(function() {
            $(this).parent().remove();
            updateIndices();
        });

        // Add result to list
        $('.user-search').parent().before(resultRow);
        updateIndices();
    });

    updateIndices();
});