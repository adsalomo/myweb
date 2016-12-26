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
                        if (property === 'descripcion' || property === 'nombre')
                            columns.push({name: value.labelName, field: property, width: 200});
                        else
                            columns.push({name: value.labelName, field: property, width: 100});

                });
            }
        }
        gridOptions.columnDefs = columns;
        gridOptions.data = list;
    }
}

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

/**
 * Valida que una lista no sea nula o vacia
 * @param {type} list
 * @returns {Boolean}
 */
function isArrayNotNull(list) {
    if (angular.isDefined(list) && angular.isArray(list) && list.length > 0) {
        return true;
    }
    return false;
}

/**
 * Obtiene objeto queryModel
 * @param {type} listModel
 * @param {type} listResult
 * @param {type} isOrderAscending
 * @param {type} isOrderDescending
 * @param {type} model
 * @param {type} page
 * @param {type} isPagination
 * @param {type} isInsert
 * @param {type} isUpdate
 * @returns {getObjectQueryModel.obj}
 */
function getObjectQueryModel(listModel, listResult, isOrderAscending, isOrderDescending, model, page, isPagination, isInsert, isUpdate) {
    var obj = {
        listModel: listModel,
        listResult: listResult,
        isOrderAscending: isOrderAscending,
        isOrderDescending: isOrderDescending,
        model: model,
        page: page,
        isPagination: isPagination,
        isInsert: isInsert,
        isUpdate: isUpdate
    };
    //console.log(JSON.stringify(obj));
    return obj;
}

/**
 * 
 * @returns {unresolved}
 */
function getValueState() {
    var array = [];
    var obj = {Description: 'Activo', Value: true};
    array.push(obj);
    var obj = {Description: 'Inactivo', Value: false};
    array.push(obj);
    return array;
}

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
                    if (value.columnName === property) {
                        value.valor = rowSelect[property];
                    }
                });
            }
        }
    }
}

/**
 * Limpia la propiedad valor del objeto structure
 * @param {type} structure
 * @returns {undefined}
 */
function clearValueStructure(structure) {
    if (!angular.isUndefined(structure) && isArrayNotNull(structure))
        angular.forEach(structure, function (value, key) {
            value.valor = null;
            if (value.hasOwnProperty('description')) {
                value.description = null;
            }
        });
}

/**
 * Valida la respuesta del servicio
 * @param {type} data
 * @returns {Boolean}
 */
function isValidResponseService(data) {
    if (angular.isDefined(data) && data !== null) {
        if (!data.status) {
            if (angular.isDefined(data.error) && data.error !== null) {
                messageBoxAlert('Solicitud', data.error.message, 'error');
            } else
                messageBoxAlert('Solicitud', 'Ocurrión un error al procesar la solicitud.', 'error');
            return false;
        }
        return true;
    } else {
        messageBoxAlert('Solicitud', 'Ocurrión un error al procesar la solicitud.', 'error');
        return false;
    }
}

/**
 * Setea los valores comparando dos estructuras iguales
 * @param {type} structure1 Estructura a la cual se asignaran los valores
 * @param {type} structure2 Estructura temporal con los valores
 * @returns {undefined}
 */
function setValoresNotNull(structure1, structure2) {
    structure2 = $.grep(structure2, function (e) {
        return e.valor !== null;
    });
    angular.forEach(structure1, function (value, key) {
        angular.forEach(structure2, function (value2, key2) {
            if (value.columnName === value2.columnName) {
                value.valor = value2.valor;
            }
        });
    });
}

/**
 * Elimina las columnas q se agregaron en la UI
 * @param {type} structure
 * @returns {undefined}
 */
function deleteColumnStructure(structure) {
    angular.forEach(structure, function (value, key) {
        if (value.hasOwnProperty('description')) {
            delete value['description'];
        }
    });
}

