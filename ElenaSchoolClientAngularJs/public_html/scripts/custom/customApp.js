/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description definicion de funciones tranversales de la app
 */

/**
 * Asigna lista de valores a la grid dandole el ancho, nombre columna
 * @param {type} gridOptions Definición de la grid
 * @param {type} list Lista de items a pintar en la grid 
 * @param {type} modelEstructura Lista de items a pintar en la grid 
 * @returns {undefined}
 */
function setListToGrid(gridOptions, list, modelEstructura) {
    var columns = [];

    if (isArrayNotNull(list)) {
        for (var property in list[0]) {
            if (list[0].hasOwnProperty(property)) {
                angular.forEach(modelEstructura, function (value, key) {
                    if (value.columnName === property)
                        columns.push({name: value.labelName, field: property, width: 100});
                });
            }
            ;
        }
        ;
        gridOptions.columnDefs = columns;
        gridOptions.data = list;
    }
    ;
}
;

/**
 * Mensaje confirmacion
 * @param {type} titulo
 * @param {type} contenido
 * @param {type} funcionSi
 * @param {type} funcionNo
 * @returns {undefined}
 */
function messageBoxConfirm(titulo, contenido, funcionSi, funcionNo) {
    $.confirm({
        //icon: 'images/confirmacion.png',
        title: titulo,
        content: contenido,
        confirmButtonClass: 'btn btn-primary',
        cancelButtonClass: 'btn btn-danger',
        confirmButton: 'Si',
        cancelButton: 'No',
        columnClass: 'col-md-6 col-md-offset-3',
        confirm: funcionSi,
        cancel: funcionNo
    });
}
;

/**
 * Mensaje info
 * @param {type} titulo
 * @param {type} contenido
 * @param {type} tipo
 * @returns {undefined}
 */
function messageBoxAlert(titulo, contenido, tipo) {

    var tipoBoton = '';
    var imagen = 'images/';

    switch (tipo) {
        case 'info':
            tipoBoton = 'btn btn-primary';
            imagen += 'info.png';
            break;
        case 'error':
            tipoBoton = 'btn btn-danger';
            imagen += 'error.png';
            break;
        case 'alert':
            tipoBoton = 'btn btn-warning';
            imagen += 'alert.png';
            break;
    }
    ;

    $.alert({
        //icon: imagen,
        title: titulo,
        content: contenido,
        confirmButtonClass: tipoBoton,
        confirmButton: 'Ok',
        columnClass: 'col-md-6 col-md-offset-3',
        confirm: function () {
        }
    });
}
;

/**
 * Valida que una lista no sea nula o vacia
 * @param {type} list
 * @returns {Boolean}
 */
function isArrayNotNull(list) {
    if (angular.isArray(list) && list.length > 0) {
        return true;
    }
    return false;
}
;

/**
 * Obtiene objeto queryModel
 * @param {type} listModel
 * @param {type} listResult
 * @param {type} isOrderAscending
 * @param {type} isOrderDescending
 * @param {type} model
 * @param {type} page
 * @param {type} isPagination
 * @returns {getObjectQueryModel.obj}
 */
function getObjectQueryModel(listModel, listResult, isOrderAscending, isOrderDescending, model, page, isPagination) {
    var obj = {
        listModel: listModel,
        listResult: listResult,
        isOrderAscending: isOrderAscending,
        isOrderDescending: isOrderDescending,
        model: model,
        page: page,
        isPagination: isPagination
    };
    //console.log(JSON.stringify(obj));
    return obj;
}
;

/**
 * 
 * @param {type} rowSelect
 * @param {type} modelEstructura
 * @returns {undefined}
 */
function setValueStructure(rowSelect, modelEstructura) {
    if (!angular.isUndefined(rowSelect)) {
        for (var property in rowSelect) {
            if (rowSelect.hasOwnProperty(property)) {
                angular.forEach(modelEstructura, function (value, key) {
                    if (value.columnName === property)
                        value.valor = rowSelect[property];
                });
            }
        }
    }
}
;

/**
 * Limpia la propiedad valor del objeto structure
 * @param {type} structure
 * @returns {undefined}
 */
function clearValueStructure(structure) {
    if (!angular.isUndefined(structure) && isArrayNotNull(structure))
        angular.forEach(structure, function (value, key) {
            value.valor = null;
        });
}
;

