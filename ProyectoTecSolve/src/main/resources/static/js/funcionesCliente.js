function eliminar(id) {
    swal({
        title: "Estar seguro?",
        text: "Una vez eliminado no podra recuperar los datos del cliente!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
            .then((OK) => {
                if (OK) {
                    $.ajax({
                        url:"/eliminar/"+id,
                        success: function(res){
                            console.log(res);
                        }
                    });
                    swal("Se elimino correctamente!", {
                        icon: "success",
                    }).then((ok)=>{
                        if(ok){
                            location.href="/listar";
                        }
                    });
                } else {
                    swal("Los datos del cliente no se eliminaron!");
                }
            });
}
//function guardar(id) {
//    swal({
//        title: "Estar seguro?",
//        text: "Una vez eliminado no podra recuperar los datos del cliente!",
//        icon: "warning",
//        buttons: true,
//        dangerMode: true,
//    })
//            .then((OK) => {
//                if (OK) {
//                    $.ajax({
//                        url:"//"+id,
//                        success: function(res){
//                            console.log(res);
//                        }
//                    });
//                    swal("Se elimino correctamente!", {
//                        icon: "success",
//                    }).then((ok)=>{
//                        if(ok){
//                            location.href="/listar";
//                        }
//                    });
//                } else {
//                    swal("Los datos del cliente no se eliminaron!");
//                }
//            });
//}


(function(document) {
      'use strict';

      var LightTableFilter = (function(Arr) {

        var _input;

        function _onInputEvent(e) {
          _input = e.target;
          var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
          Arr.forEach.call(tables, function(table) {
            Arr.forEach.call(table.tBodies, function(tbody) {
              Arr.forEach.call(tbody.rows, _filter);
            });
          });
        }

        function _filter(row) {
          var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
          row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
        }

        return {
          init: function() {
            var inputs = document.getElementsByClassName('light-table-filter');
            Arr.forEach.call(inputs, function(input) {
              input.oninput = _onInputEvent;
            });
          }
        };
      })(Array.prototype);

      document.addEventListener('readystatechange', function() {
        if (document.readyState === 'complete') {
          LightTableFilter.init();
        }
      });

    })(document);
    
function limpiar(){
    formulario.reset();
    
} 