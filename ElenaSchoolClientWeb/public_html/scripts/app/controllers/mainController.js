/* global app */

/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description controller principal
 */
app.controller('mainController', ['$scope', '$myService', '$uibModal', '$setting', '$generalFactory', '$timeout', function ($scope, $myService, $modal, $setting, $generalFactory, $timeout) {

        /**
         * Definicion de variables
         */
        $scope.modelEstructura = [];
        $scope.numRow = [];
        $scope.numColumn = $setting.varGlobals.columnsXRow;
        $scope.isActivoGrid = false;
        $scope.gridApi = {};
        $scope.isModeInsert = false;
        $scope.isModeEdit = false;
        $scope.listState = getValueState();

        /**
         * Definicion de grid
         */
        $scope.gridFormulario = {
            enableRowSelection: true,
            enableRowHeaderSelection: false,
            enableFiltering: false,
            autoResize: true,
            multiSelect: false,
            noUnselect: true,
            flatEntityAccess: true,
            rowNumber: -1, // Fila actual seleccionada en la grid
            count: 0, // Total registros consulta
            isOrderAscending: false, // Ordenamiento consulta
            isOrderDescending: false, // Ordenamiento Consulta
            rowSelect: null // fila seleccionada
        };

        /**
         * Selection changed grid
         * @param {type} gridApi
         * @returns {undefined}
         */
        $scope.gridFormulario.onRegisterApi = function (gridApi) {
            $scope.gridApi['gridFormulario'] = gridApi;

            $scope.gridApi['gridFormulario'].cellNav.on.navigate($scope, function (newRowCol, oldRowCol) {
                if (angular.isUndefined(newRowCol.row.isSelected) || !newRowCol.row.isSelected) {
                    $scope.gridApi['gridFormulario'].selection.selectRow(newRowCol.row.entity);
                    $scope.gridFormulario.rowNumber = newRowCol.row.entity.Secuence;
                    $scope.gridFormulario.rowSelect = newRowCol.row.entity;
                }
            });

            gridApi.core.registerColumnsProcessor(hideIdColumn);

            function hideIdColumn(columns) {
                columns.forEach(function (column) {
                    if (column.field === 'usuario' || column.field === 'id' || column.field === 'fecha_creacion'
                            || column.field === 'fecha_modificacion' || column.field === 'fecha_proceso') {
                        column.visible = false;
                    }
                });
                return columns;
            }
        };

        /**
         * Obtiene la estructura de una tabla de la base de datos
         * @param {type} nameTable Nombre tabla a obtener estructura
         * @returns {undefined}
         */
        $scope.getStructureTableAction = function (nameTable) {
            var model = {nameTable: nameTable};
            var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(model), token: null};

            // Request para obtener la estructura
            $myService.getStructureTableService(actionRequest, obtenerUrlService('GetStructure')).success(function (data, status, headers, config) {
                // Valida la respuesta del servicio
                if (!isValidResponseService(data))
                    return;

                var response = JSON.parse(data.response);
                if (!isArrayNotNull(response)) {
                    messageBoxAlert('Obtener estructura', 'No existe estructura para la tabla ' + nameTable + '.', 'info');
                    return;
                }

                $scope.modelEstructura = response;
                var numRow = Math.floor($scope.modelEstructura.length / $setting.varGlobals.column);
                var resto = ($scope.modelEstructura.length % $setting.varGlobals.column);
                if (resto !== 0)
                    numRow++;

                for (var i = 0, l = numRow; i < l; i++) {
                    $scope.numRow[i] = i;
                }
            }).error(function (data, status, headers, config) {
                console.log(data);
                messageBoxAlert('Obtener estructura', 'Ocurrión un error al procesar la solicitud.', 'error');
            });
        };

        /**
         * Action actualizar tabla 
         * @returns {undefined}
         */
        $scope.updateModelAction = function () {
            deleteColumnStructure($scope.modelEstructura);

            var queryModel = getObjectQueryModel($scope.modelEstructura, null, false, false, $scope.modelEstructura[0].nameTable, 0, false, $scope.isModeInsert, $scope.isModeEdit);
            var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(queryModel), token: null};

            // Request para realizar la actualizacion del modelo
            $myService.insertModelService(actionRequest, obtenerUrlService('UpdateModel')).success(function (data, status, headers, config) {
                // Valida la respuesta del servicio
                if (!isValidResponseService(data))
                    return;

                messageBoxAlert('Registro', data.response, 'info');
                $scope.cancelEditionModelAction(false);
            }).error(function (data, status, headers, config) {
                console.log(data);
                messageBoxAlert('Registro', 'Ocurrión un error al procesar la solicitud.', 'error');
            });
        };

        /**
         * Activa el modo de nuevo registro
         * @returns {undefined}
         */
        $scope.activateModeInsertAction = function () {
            $timeout(function () {
                deleteColumnStructure($scope.modelEstructura);

                var queryModel = getObjectQueryModel($scope.modelEstructura, null, false, false, $scope.modelEstructura[0].nameTable, 0, false, $scope.isModeInsert, $scope.isModeEdit);
                var actionRequest = {user: 'adsalomo', password: null, credentials: null, request: angular.toJson(queryModel), token: null};

                // Requesta para obtener los valores de las columnas autonumericos y llaves primarias
                $myService.getMaxCodeService(actionRequest, obtenerUrlService('GetMaxCode')).success(function (data, status, headers, config) {
                    // Valida la respuesta del servicio
                    if (!isValidResponseService(data))
                        return;

                    var response = JSON.parse(data.response);
                    setValoresNotNull($scope.modelEstructura, response);
                    $scope.isModeInsert = true;
                }).error(function (data, status, headers, config) {
                    console.log(data);
                    messageBoxAlert('Registro', 'Ocurrión un error al procesar la solicitud.', 'error');
                });
            }, 200);
        };

        /**
         * Action para abrir calendario
         * @param {type} item
         */
        $scope.openCalendarAction = function (item) {
            $('#' + item.columnName + '').datepicker({
                dateFormat: 'yy-mm-dd',
                prevText: '<i class="fa fa-chevron-left"></i>',
                nextText: '<i class="fa fa-chevron-right"></i>',
                onSelect: function (selectedDate) {
                    item.valor = selectedDate;
                }
            });
        };

        /**
         * Cancela modo edicion / nuevo registro
         * @param {type} isViewConfirmation
         */
        $scope.cancelEditionModelAction = function (isViewConfirmation) {
            if (isViewConfirmation) {
                messageBoxConfirm('Confirmación', '¿ Está seguro de realizar está acción ?', function (result) {
                    $timeout(function () {
                        $scope.isModeInsert = false;
                        $scope.isModeEdit = false;
                        $scope.isActivoGrid = false;
                        $scope.gridFormulario.rowSelect = undefined;
                        clearValueStructure($scope.modelEstructura);
                        $scope.formOperation.$setPristine();
                    }, 200);
                }, function (result) {
                });
            } else {
                $timeout(function () {
                    $scope.isModeInsert = false;
                    $scope.isModeEdit = false;
                    $scope.isActivoGrid = false;
                    $scope.gridFormulario.rowSelect = undefined;
                    clearValueStructure($scope.modelEstructura);
                    $scope.formOperation.$setPristine();
                }, 200);
            }
        };

        /**
         * Activa modo edicion del modelo
         */
        $scope.activateModeEditAction = function () {
            $generalFactory.setValueStructure($scope.gridFormulario.rowSelect, $scope.modelEstructura);
            $scope.openViewFormToTableAction();
            $scope.isModeEdit = true;
        };

        /**
         * openLupaAction: abre lupa con los registros de los campos q son llaves foraneas
         * @param {type} item
         * @returns {undefined}
         */
        $scope.openLupaAction = function (item) {
            $modal.open({
                templateUrl: 'includes/controls/lupa.html',
                backdrop: false,
                windowClass: 'modal',
                controller: 'lupaController',
                resolve: {
                    $item: function () {
                        return item;
                    }
                }
            }).result.catch(function (resp) {

            });
        };

        /**
         * Abre ventana modal para realizar consulta
         */
        $scope.openQueryTablaAction = function () {
            $modal.open({
                templateUrl: 'includes/controls/queryTable.html',
                backdrop: false,
                windowClass: 'modal',
                controller: 'queryTableController',
                resolve: {
                    $tableName: function () {
                        if (isArrayNotNull($scope.modelEstructura))
                            return $scope.modelEstructura[0].nameTable;
                    },
                    $gridFormulario: function () {
                        return $scope.gridFormulario;
                    }
                }
            }).result.catch(function (resp) {
                $timeout(function () {
                    $scope.gridFormulario.rowSelect = undefined;
                    $scope.isModeEdit = false;
                    if (resp) {
                        if (isArrayNotNull($scope.gridFormulario.data)) {
                            $scope.isActivoGrid = true;
                            $scope.gridFormulario.rowSelect = $scope.gridFormulario.data[0];
                            $scope.gridApi['gridFormulario'].selection.selectRow($scope.gridFormulario.rowSelect);
                            $scope.gridFormulario.rowNumber = 0;
                            $scope.gridFormulario.count = $scope.gridFormulario.data.length - 1;
                        } else {
                            $scope.isActivoGrid = false;
                            messageBoxAlert($setting.varGlobals.nameApp + ' - Formulario', 'No hay datos para mostrar.', 'info');
                        }
                    } else {
                        $scope.cancelEditionModelAction(false);
                    }
                }, 200);
            });
        };

        /**
         * Activa inactiva forma tabla - forma formulario
         * @returns {undefined}
         */
        $scope.openViewFormToTableAction = function () {
            if ($scope.isActivoGrid)
                $scope.isActivoGrid = false;
            else
                $scope.isActivoGrid = true;
        };

        /**
         * *************************************
         * Definicion eventos grid Anterior, siguiente, primera pagina, ultima pagina
         * *************************************
         */

        /**
         * Action ir siguiente grid
         */
        $scope.nextGridAction = function () {
            $generalFactory.nextGrid($scope.gridFormulario, 'gridFormulario', $scope, $scope.modelEstructura);
        };

        /**
         * Action ir anterior
         */
        $scope.previousGridAction = function () {
            $generalFactory.previousGrid($scope.gridFormulario, 'gridFormulario', $scope, $scope.modelEstructura);
        };

        /**
         * Action ir ultima pagina grid
         */
        $scope.lastGridAction = function () {
            $generalFactory.lastGrid($scope.gridFormulario, 'gridFormulario', $scope, $scope.modelEstructura);
        };

        /**
         * Action ir primera pagina
         */
        $scope.firtsGridAction = function () {
            $generalFactory.firtsGrid($scope.gridFormulario, 'gridFormulario', $scope, $scope.modelEstructura);
        };

    }]);


