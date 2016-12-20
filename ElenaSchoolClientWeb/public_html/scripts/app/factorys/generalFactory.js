/* global app */

/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description factory general para funciones globales
 */
app.factory('$generalFactory', ['$myService', '$setting', function ($myService, $setting) {
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

                        // Si la pagina supera al total de paginas, la pagina actual es igual al numero de paginas
                        if (grid.actualPage > grid.totalPages) {
                            grid.actualPage = grid.totalPages;
                            messageBoxAlert($setting.varGlobals.nameApp + ' - Grid', 'No hay más datos para mostrar', 'info');
                            grid.rowNumber = grid.pageSize - 1;
                        } else {
                            // Preguntamos si el total de paginas en mayor o igual que la pagina actual para realizar el request
                            if (grid.totalPages >= grid.actualPage) {

                                // Arma objero queryModel
                                var obj = getObjectQueryModel(structure, null, grid.isOrderAscending, grid.isOrderDescending, table, grid.actualPage, grid.isPagination);
                                var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(obj), token: null};

                                // Request get consulta
                                $myService.getConsultaService(actionRequest, obtenerUrlService('GetQuery')).success(function (data, status, headers, config) {
                                    // Valida la respuesta del servicio
                                    if (!isValidResponseService(data))
                                        return;

                                    var response = JSON.parse(data.response);

                                    grid.count = response.count;
                                    // Reiniciamos el contador de las filas
                                    grid.rowNumber = -1;

                                    // Define el tamano de la pagina de acuerdo a si la grid es paginada o no
                                    if (grid.isPagination)
                                        grid.pageSize = isArrayNotNull(response.listResult) ? response.listResult.length : 0;
                                    else
                                        grid.pageSize = data.count;

                                    // Llena grid con los datos de la consulta
                                    setListToGrid(grid, response.listResult, structure);

                                }).error(function (data, status, headers, config) {
                                    console.log(data);
                                });
                            }
                        }
                    } else {
                        grid.rowNumber = grid.pageSize - 1;
                        messageBoxAlert($setting.varGlobals.nameApp + ' - Grid', 'No hay más datos para mostrar', 'info');
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

                        // Si la pagina actual es mayor que 0 se le resta una pagina para ir a la anterior
                        if (grid.actualPage > 0) {
                            grid.actualPage -= 1;

                            // Arma objero queryModel
                            var obj = getObjectQueryModel(structure, null, grid.isOrderAscending, grid.isOrderDescending, table, grid.actualPage, grid.isPagination);
                            var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(obj), token: null};

                            // Request get consulta
                            $myService.getConsultaService(actionRequest, obtenerUrlService('GetQuery')).success(function (data, status, headers, config) {
                                // Valida la respuesta del servicio
                                if (!isValidResponseService(data))
                                    return;

                                var response = JSON.parse(data.response);

                                grid.count = response.count;
                                // Reiniciamos el contador de las filas
                                grid.rowNumber = -1;

                                // Define el tamano de la pagina de acuerdo a si la grid es paginada o no
                                if (grid.isPagination)
                                    grid.pageSize = isArrayNotNull(response.listResult) ? response.listResult.length : 0;
                                else
                                    grid.pageSize = response.count;

                                // Llena grid con los datos de la consulta
                                setListToGrid(grid, response.listResult, structure);

                            }).error(function (data, status, headers, config) {
                                console.log(data);
                            });
                        } else {
                            grid.actualPage = 0;
                            grid.rowNumber = 0;
                            scope.gridApi[nameObject].selection.selectRowByVisibleIndex(grid.rowNumber);
                        }
                    }
                } else if (grid.rowNumber < -1) {
                    if (grid.isPagination) {
                        grid.rowNumber = grid.pageSize - 1;
                        scope.gridApi[nameObject].selection.selectRowByVisibleIndex(grid.rowNumber);
                    } else {
                        grid.rowNumber = 0;
                        scope.gridApi[nameObject].selection.selectRowByVisibleIndex(grid.rowNumber);
                    }
                } else {
                    scope.gridApi[nameObject].selection.selectRowByVisibleIndex(grid.rowNumber);
                }
            },
            
            /**
             * Va a la ultima pagina
             * @param {type} grid
             * @param {type} structure
             * @param {type} table
             * @returns {undefined}
             */
            lastGrid: function (grid, structure, table) {
                grid.actualPage = grid.totalPages;
                // Arma objero queryModel
                var obj = getObjectQueryModel(structure, null, grid.isOrderAscending, grid.isOrderDescending, table, grid.actualPage, grid.isPagination);
                var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(obj), token: null};

                // Request get consulta
                $myService.getConsultaService(actionRequest, obtenerUrlService('GetQuery')).success(function (data, status, headers, config) {
                    // Valida la respuesta del servicio
                    if (!isValidResponseService(data))
                        return;

                    var response = JSON.parse(data.response);

                    grid.count = response.count;
                    // Reiniciamos el contador de las filas
                    grid.rowNumber = -1;

                    // Define el tamano de la pagina de acuerdo a si la grid es paginada o no
                    if (grid.isPagination)
                        grid.pageSize = isArrayNotNull(response.listResult) ? response.listResult.length : 0;
                    else
                        grid.pageSize = response.count;

                    // Llena grid con los datos de la consulta
                    setListToGrid(grid, response.listResult, structure);

                }).error(function (data, status, headers, config) {
                    console.log(data);
                });
            },
            
            /**
             * Va a la primera pagina
             * @param {type} grid
             * @param {type} structure
             * @param {type} table
             * @returns {undefined}
             */
            firtsGrid: function (grid, structure, table) {
                grid.actualPage = 0;
                // Arma objero queryModel
                var obj = getObjectQueryModel(structure, null, grid.isOrderAscending, grid.isOrderDescending, table, grid.actualPage, grid.isPagination);
                var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(obj), token: null};

                // Request get consulta
                $myService.getConsultaService(actionRequest, obtenerUrlService('GetQuery')).success(function (data, status, headers, config) {
                    // Valida la respuesta del servicio
                    if (!isValidResponseService(data))
                        return;

                    var response = JSON.parse(data.response);

                    grid.count = response.count;
                    // Reiniciamos el contador de las filas
                    grid.rowNumber = -1;

                    // Define el tamano de la pagina de acuerdo a si la grid es paginada o no
                    if (grid.isPagination)
                        grid.pageSize = isArrayNotNull(response.listResult) ? response.listResult.length : 0;
                    else
                        grid.pageSize = response.count;

                    // Llena grid con los datos de la consulta
                    setListToGrid(grid, response.listResult, structure);

                }).error(function (data, status, headers, config) {
                    console.log(data);
                });
            },
            
            /**
             * Asigna los valores a las propiedades value de la estructura. 
             * Para el caso de las columnas que sean foronaeas busca el objeto al que hace referencia por el codigo.
             * @param {type} rowSelect
             * @param {type} modelEstructura
             * @returns {undefined}
             */
            setValueStructure: function (rowSelect, modelEstructura) {
                if (!angular.isUndefined(rowSelect)) {
                    for (var property in rowSelect) {
                        angular.forEach(modelEstructura, function (value, key) {
                            if (value.columnName === property) {
                                value.valor = rowSelect[property];
                                if (value.isForeign) {
                                    var model = {nameTable: value.foreignTableName};
                                    var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(model), token: null};
                                    // Request para obtener la estructura
                                    $myService.getEstructuraTablaService(actionRequest, obtenerUrlService('GetStructure')).success(function (data, status, headers, config) {
                                        // Valida la respuesta del servicio
                                        if (!isValidResponseService(data))
                                            return;
                                        // Obtiene la estructura
                                        var structure = JSON.parse(data.response);
                                        if (isArrayNotNull(structure)) {
                                            // Obtiene la lista solo con el objeto donde el nombre de columna es igual a codigo
                                            var item = $.grep(structure, function (e) {
                                                return e.columnName === 'codigo';
                                            });
                                            if (isArrayNotNull(item)) {
                                                // Asigna el valor
                                                item[0].valor = value.valor;
                                                // Elimina el objeto de la lista donde nombre columna es igual a codigo
                                                structure = $.grep(structure, function (e) {
                                                    return e.columnName !== 'codigo';
                                                });
                                                // Agrega el objeto donde la columna es igual a codigo a la estrucutra
                                                structure.push(item[0]);

                                                var obj = getObjectQueryModel(structure, null, false, false, value.foreignTableName, 0, false);
                                                var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(obj), token: null};
                                                // Request get consulta
                                                $myService.getConsultaService(actionRequest, obtenerUrlService('GetQuery')).success(function (data, status, headers, config) {
                                                    // Valida la respuesta del servicio
                                                    if (!isValidResponseService(data))
                                                        return;

                                                    var response = JSON.parse(data.response);
                                                    value.valor = isArrayNotNull(response.listResult) ? response.listResult[0].codigo : '';
                                                    value.descripcion = isArrayNotNull(response.listResult) ? response.listResult[0].codigo + ' - ' + response.listResult[0].nombre : '';

                                                }).error(function (data, status, headers, config) {
                                                    console.log(data);
                                                });
                                            }
                                        }
                                    }).error(function (data, status, headers, config) {
                                        console.log(data);
                                        messageBoxAlert('Obtener estructura', 'Ocurrión un error al procesar la solicitud.', 'error');
                                    });
                                }
                            }
                        });
                    }
                }
            }
        };
    }]);

