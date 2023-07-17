$('.pagination').on('click', '.prev, .next, .page-numbers a', function (event) {
    event.preventDefault();
    var page = $(this).attr('href');
    loadPage(page);
});

function loadPage(page) {
    $.ajax({
        url: '/my-data?page=' + page,
        success: function (data) {
            $('.my-data-list').html(data);
            updatePagination(page);
        }
    });
}

function updatePagination(page) {
    $.ajax({
        url: '/my-data?page=' + page + '&count=true',
        success: function (data) {
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


(function (document) {
    'use strict';

    var LightTableFilter = (function (Arr) {

        var _input;

        function _onInputEvent(e) {
            _input = e.target;
            var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
            Arr.forEach.call(tables, function (table) {
                Arr.forEach.call(table.tBodies, function (tbody) {
                    Arr.forEach.call(tbody.rows, _filter);
                });
            });
        }

        function _filter(row) {
            var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
            row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
        }

        return {
            init: function () {
                var inputs = document.getElementsByClassName('light-table-filter');
                Arr.forEach.call(inputs, function (input) {
                    input.oninput = _onInputEvent;
                });
            }
        };
    })(Array.prototype);

    document.addEventListener('readystatechange', function () {
        if (document.readyState === 'complete') {
            LightTableFilter.init();
        }
    });

})(document);

function limpiar() {
    formulario.reset();

}


function goBack() {
    window.location.replace(document.referrer);
}

function editarCliente(id_cliente, nombre, apellido, cedula, numeroTelefono, correo) {
    Swal.fire({
        title: '¿Está seguro?',
        text: '¿Desea editar el registro?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, editar',
        cancelButtonText: 'Cancelar'
    }).then(function(result) {
        if (result.isConfirmed) {
            var url = '/clientes/editar/' + id_cliente + '/' + encodeURIComponent(nombre) + '/' + encodeURIComponent(apellido) + '/' + encodeURIComponent(cedula) + '/' + encodeURIComponent(numeroTelefono) + '/' + encodeURIComponent(correo);
            window.location.href = url;
        }
    });
}

function eliminarCliente(id_cliente) {
    Swal.fire({
        title: '¿Está seguro?',
        text: '¿Desea eliminar el registro?',
        icon: 'error',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    }).then(function(result) {
        if (result.isConfirmed) {
            var url = '/clientes/eliminar/' + id_cliente;
            window.location.href = url;
        }
    });
}


