/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description definicion de funciones tranversales de la app
 */

/**
 * Asigna lista de valores a la grid
 * @param {type} gridOptions Definición de la grid
 * @param {type} list Lista de items a pintar en la grid 
 * @returns {undefined}
 */
function setListToGrid(gridOptions, list) {
    var columns = [];

    if (angular.isArray(list)) {
        for (var property in list[0]) {
            if (list[0].hasOwnProperty(property)) {
                columns.push({name: property, width: 100});
            }
        }
        gridOptions.columnDefs = columns;
        gridOptions.data = list;
    }
}

