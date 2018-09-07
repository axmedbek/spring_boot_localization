



function loadModal(url,params){
    $.post(url,params,function(response){
        $('#mainModal').find('.modal-content').html(response);
    });
    $('#mainModal').modal('show');
}