$('.pagination').on('click', '.prev, .next, .page-numbers a', function(event) {
  event.preventDefault();
  var page = $(this).attr('href');
  loadPage(page);
});

function loadPage(page) {
  $.ajax({
    url: '/my-data?page=' + page,
    success: function(data) {
      $('.my-data-list').html(data);
      updatePagination(page);
    }
  });
}

function updatePagination(page) {
  $.ajax({
    url: '/my-data?page=' + page + '&count=true',
    success: function(data) {
      var totalPages = Math.ceil(data / 10); // 10 registros por página
      var pageNumbers = '';
      for (var i = 1; i <= totalPages; i++) {
        if (i === page) {
          pageNumbers += '<span class="current">' + i + '</span>';
        } else {
          pageNumbers += '<a href="' + i + '">' + i + '</a>';
        }
      }
      $('.pagination .page-numbers').html(pageNumbers);
    }
  });
}
