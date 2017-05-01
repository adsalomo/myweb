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
        $scope.modelStructure = [];
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
        $scope.gridForm = {
            enableRowSelection: true,
            enableRowHeaderSelection: false,
            enableFiltering: false,
            autoResize: true,
            multiSelect: false,
            noUnselect: true,
            flatEntityAccess: true,
            rowNumber: 0, // Fila actual seleccionada en la grid
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
        $scope.gridForm.onRegisterApi = function (gridApi) {
            $scope.gridApi['gridForm'] = gridApi;

            $scope.gridApi['gridForm'].cellNav.on.navigate($scope, function (newRowCol, oldRowCol) {
                if (angular.isUndefined(newRowCol.row.isSelected) || !newRowCol.row.isSelected) {
                    $scope.gridApi['gridForm'].selection.selectRow(newRowCol.row.entity);
                    $scope.gridForm.rowNumber = newRowCol.row.entity.Secuence;
                    $scope.gridForm.rowSelect = newRowCol.row.entity;
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

                $scope.modelStructure = response;
                var numRow = Math.floor($scope.modelStructure.length / $scope.numColumn);
                var resto = ($scope.modelStructure.length % $scope.numColumn);
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
            deleteColumnStructure($scope.modelStructure);

            var queryModel = { 
                listModel: $scope.modelStructure,
                model: $scope.modelStructure[0].nameTable, 
                isInsert: $scope.isModeInsert, 
                isUpdate: $scope.isModeEdit 
            };
            
            var actionRequest = {
                user: null, 
                password: null, 
                credentials: null, 
                request: angular.toJson(queryModel), 
                token: null
            };

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
                deleteColumnStructure($scope.modelStructure);

                var queryModel = { 
                    listModel: $scope.modelStructure, 
                    model: $scope.modelStructure[0].nameTable
                };
                
                var actionRequest = {
                    user: 'adsalomo', 
                    password: null, 
                    credentials: null, 
                    request: angular.toJson(queryModel), 
                    token: null
                };

                // Requesta para obtener los valores de las columnas autonumericos y llaves primarias
                $myService.getMaxCodeService(actionRequest, obtenerUrlService('GetMaxCode')).success(function (data, status, headers, config) {
                    // Valida la respuesta del servicio
                    if (!isValidResponseService(data))
                        return;

                    var response = JSON.parse(data.response);
                    setValoresNotNull($scope.modelStructure, response);
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
                        $scope.gridForm.rowSelect = undefined;
                        clearValueStructure($scope.modelStructure);
                        $scope.formOperation.$setPristine();
                        $scope.gridForm.data = [];
                    }, 200);
                }, function (result) {
                });
            } else {
                $timeout(function () {
                    $scope.isModeInsert = false;
                    $scope.isModeEdit = false;
                    $scope.isActivoGrid = false;
                    $scope.gridForm.rowSelect = undefined;
                    clearValueStructure($scope.modelStructure);
                    $scope.formOperation.$setPristine();
                }, 200);
            }
        };

        /**
         * Activa modo edicion del modelo
         */
        $scope.activateModeEditAction = function () {
            $generalFactory.setValueStructure($scope.gridForm.rowSelect, $scope.modelStructure);
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
                        if (isArrayNotNull($scope.modelStructure))
                            return $scope.modelStructure[0].nameTable;
                    },
                    $gridForm: function () {
                        return $scope.gridForm;
                    }
                }
            }).result.catch(function (resp) {
                $timeout(function () {
                    $scope.gridForm.rowSelect = undefined;
                    $scope.isModeEdit = false;
                    if (resp) {
                        if (isArrayNotNull($scope.gridForm.data)) {
                            $scope.isActivoGrid = true;
                            $scope.gridForm.rowSelect = $scope.gridForm.data[0];
                            $scope.gridApi['gridForm'].selection.selectRow($scope.gridForm.rowSelect);
                            $scope.gridForm.rowNumber = 0;
                            $scope.gridForm.count = $scope.gridForm.data.length - 1;
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
            $generalFactory.nextGrid($scope.gridForm, 'gridForm', $scope, $scope.modelStructure);
        };

        /**
         * Action ir anterior
         */
        $scope.previousGridAction = function () {
            $generalFactory.previousGrid($scope.gridForm, 'gridForm', $scope, $scope.modelStructure);
        };

        /**
         * Action ir ultima pagina grid
         */
        $scope.lastGridAction = function () {
            $generalFactory.lastGrid($scope.gridForm, 'gridForm', $scope, $scope.modelStructure);
        };

        /**
         * Action ir primera pagina
         */
        $scope.firtsGridAction = function () {
            $generalFactory.firtsGrid($scope.gridForm, 'gridForm', $scope, $scope.modelStructure);
        };

    }]);


