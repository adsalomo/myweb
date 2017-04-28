/* global app */

/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description factory general para funciones globales
 */
app.factory('$generalFactory', ['$myService', '$setting', function ($myService, $setting) {
        return {
            
            firtsGrid: function (pGrid, pEntidad, pScope, pModelStructure) {
                pGrid.rowNumber = 0;

                // Se obtiene el valor de la grid en la primera posicion
                pGrid.rowSelect = pGrid.data[pGrid.rowNumber];
                pScope.gridApi[pEntidad].selection.selectRow(pGrid.rowSelect);
                setValueStructure(pGrid.rowSelect, pModelStructure);
            },
            
            previousGrid: function (pGrid, pEntidad, pScope, pModelStructure) {
                if (pGrid.rowNumber > 0) {
                    // Disminuimos en uno la fila
                    pGrid.rowNumber = pGrid.rowNumber - 1;

                    // Se obtiene el valor de la grid en la posicion anterior
                    pGrid.rowSelect = pGrid.data[pGrid.rowNumber];
                    pScope.gridApi[pEntidad].selection.selectRow(pGrid.rowSelect);
                    setValueStructure(pGrid.rowSelect, pModelStructure);
                }
            },
            
            lastGrid: function (pGrid, pEntidad, pScope, pModelStructure) {
                pGrid.rowNumber = pGrid.count;
                pGrid.rowSelect = pGrid.data[pGrid.rowNumber];
                pScope.gridApi[pEntidad].selection.selectRow(pGrid.rowSelect);
                setValueStructure(pGrid.rowSelect, pModelStructure);
            },
            
            nextGrid: function (pGrid, pEntidad, pScope, pModelStructure) {
                if (pGrid.count > pGrid.rowNumber) {
                    // Aumentamos en uno la fila
                    pGrid.rowNumber = pGrid.rowNumber + 1;

                    // Se obtiene el valor de la grid en la posicion siguiente
                    pGrid.rowSelect = pGrid.data[pGrid.rowNumber];
                    pScope.gridApi[pEntidad].selection.selectRow(pGrid.rowSelect);
                    setValueStructure(pGrid.rowSelect, pModelStructure);
                }
            },
            
            /**
             * Asigna los valores a las propiedades value de la estructura. 
             * Para el caso de las columnas que sean foronaeas busca el objeto al que hace referencia por el codigo.
             * @param {type} pRowSelect
             * @param {type} pModelEstructura
             */
            setValueStructure: function (pRowSelect, pModelEstructura) {
                if (!angular.isUndefined(pRowSelect)) {
                    for (var property in pRowSelect) {
                        angular.forEach(pModelEstructura, function (value, key) {
                            if (value.columnName === property) {
                                value.valor = pRowSelect[property];
                                if (value.isForeign) {
                                    var model = {nameTable: value.foreignTableName};
                                    var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(model), token: null};
                                    // Request para obtener la estructura
                                    $myService.getStructureTableService(actionRequest, obtenerUrlService('GetStructure')).success(function (data, status, headers, config) {
                                        // Valida la respuesta del servicio
                                        if (!isValidResponseService(data))
                                            return;
                                        // Obtiene la estructura
                                        var pStructure = JSON.parse(data.response);
                                        if (isArrayNotNull(pStructure)) {
                                            // Obtiene la lista solo con el objeto donde el nombre de columna es igual a codigo
                                            var item = $.grep(pStructure, function (e) {
                                                return e.columnName === 'codigo';
                                            });
                                            if (isArrayNotNull(item)) {
                                                // Asigna el valor
                                                item[0].valor = value.valor;
                                                // Elimina el objeto de la lista donde nombre columna es igual a codigo
                                                pStructure = $.grep(pStructure, function (e) {
                                                    return e.columnName !== 'codigo';
                                                });
                                                // Agrega el objeto donde la columna es igual a codigo a la estrucutra
                                                pStructure.push(item[0]);

                                                var obj = getObjectQueryModel(pStructure, null, false, false, value.foreignTableName, 0, false);
                                                var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(obj), token: null};
                                                // Request get consulta
                                                $myService.getQueryService(actionRequest, obtenerUrlService('GetQuery')).success(function (data, status, headers, config) {
                                                    // Valida la respuesta del servicio
                                                    if (!isValidResponseService(data))
                                                        return;

                                                    var response = JSON.parse(data.response);
                                                    value.valor = isArrayNotNull(response.listResult) ? response.listResult[0].codigo : '';
                                                    value.description = isArrayNotNull(response.listResult) ? response.listResult[0].codigo + ' - ' + response.listResult[0].nombre : '';

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
            },
            
            setListToGrid: function (pGrid, pList, pModelEstructura) {
                var vColumns = [];
                if (isArrayNotNull(pList)) {
                    for (var property in pList[0]) {
                        if (pList[0].hasOwnProperty(property)) {
                            angular.forEach(pModelEstructura, function (pValue, pKey) {
                                if (pValue.columnName === property)
                                    if (property === 'descripcion' || property === 'nombre')
                                        vColumns.push({name: pValue.labelName, field: property, width: 200});
                                    else
                                        vColumns.push({name: pValue.labelName, field: property, width: 100});
                            });
                        }
                    }
                    
                    angular.forEach(pList, function (pValue, pKey){
                        pValue.Secuence = pKey;
                    });
                    
                    pGrid.columnDefs = vColumns;
                    pGrid.data = pList;
                }
            }
        };
    }]);

