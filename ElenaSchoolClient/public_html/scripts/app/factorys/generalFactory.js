/* global app */

/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description factory general para funciones globales
 */
app.factory('$generalFactory', ['$myService', function ($myService) {
        return {
            /**
             * Ir siguiente en grid
             * @param {object} grid
             * @param {object} scope
             * @param {object} structure
             * @param {string} table
             * @param {string} nameObject
             * @returns {undefined}
             */
            nextGrid: function (grid, scope, structure, table, nameObject) {
                grid.rowNumber += 1;

                if (grid.pageSize === grid.rowNumber) {
                    // Si la grid es paginada
                    if (grid.isPagination) {

                        // Aumentamos en 1 la pagina actual
                        grid.actualPage += 1;

                        // Preguntamos si el total de paginas en mayor o igual que la pagina actual para realizar el request
                        if (grid.totalPages >= grid.actualPage) {

                            // Arma objero queryModel
                            var obj = getObjectQueryModel(
                                    structure,
                                    null,
                                    grid.isOrderAscending,
                                    grid.isOrderDescending,
                                    table,
                                    grid.actualPage,
                                    grid.isPagination
                                    );

                            /**
                             * Request get consulta
                             */
                            $myService.getConsultaService(obj)
                                    .success(function (data, status, headers, config) {
                                        grid.count = data.count;

                                        // Reiniciamos el contador de las filas
                                        grid.rowNumber = -1;

                                        // Define el tamano de la pagina de acuerdo a si la grid es paginada o no
                                        if (grid.isPagination)
                                            grid.pageSize = data.listResult.length;
                                        else
                                            grid.pageSize = data.count;

                                        // Llena grid con los datos de la consulta
                                        setListToGrid(grid, data.listResult, structure);

                                    }).error(function (data, status, headers, config) {
                                console.log(data);
                            });
                        }
                    }
                } else {
                    scope.gridApi[nameObject].selection.selectRowByVisibleIndex(grid.rowNumber);
                }
            },
            /**
             * Ir Anterior en grid
             * @param {object} grid
             * @param {object} scope
             * @param {object} structure
             * @param {string} table
             * @param {string} nameObject
             * @returns {undefined}
             */
            previousGrid: function (grid, scope, structure, table, nameObject) {
                grid.rowNumber -= 1;

                if (grid.rowNumber === -1) {

                    // Si la grid es paginada
                    if (grid.isPagination) {

                        // Restamos en 1 la pagina actual
                        grid.actualPage -= 1;

                        // 
                        if (grid.actualPage > -1) {

                            // Arma objero queryModel
                            var obj = getObjectQueryModel(
                                    structure,
                                    null,
                                    grid.isOrderAscending,
                                    grid.isOrderDescending,
                                    table,
                                    grid.actualPage,
                                    grid.isPagination
                                    );

                            /**
                             * Request get consulta
                             */
                            $myService.getConsultaService(obj)
                                    .success(function (data, status, headers, config) {
                                        grid.count = data.count;

                                        // Reiniciamos el contador de las filas
                                        grid.rowNumber = -1;

                                        // Define el tamano de la pagina de acuerdo a si la grid es paginada o no
                                        if (grid.isPagination)
                                            grid.pageSize = data.listResult.length;
                                        else
                                            grid.pageSize = data.count;

                                        // Llena grid con los datos de la consulta
                                        setListToGrid(grid, data.listResult, structure);

                                    }).error(function (data, status, headers, config) {
                                console.log(data);
                            });
                        }
                    }
                } else if (grid.rowNumber < -1) {
                    grid.rowNumber = grid.pageSize - 1;
                    scope.gridApi[nameObject].selection.selectRowByVisibleIndex(grid.rowNumber);
                } else {
                    scope.gridApi[nameObject].selection.selectRowByVisibleIndex(grid.rowNumber);
                }
            }
        };
    }]);

