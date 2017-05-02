function locale(locale) {
    $.ajax({
        url: '?loc=' + locale,
        method: 'get',
        success: function () {
            location.reload()
        }
    })
}