$(document).ready(() => {
    $('table #updateBtn').click(function (e) {
        // Not request to the url
        e.preventDefault();

        // Do a get request to fetch category
        let href = $(this).attr('href');
        $.get(href, function (category, status) {
            $('#updateId').val(category.id);
            $('#updateName').val(category.name);
        });

        // Show modal
        $('#updateCategoryModal').modal();
    });
});