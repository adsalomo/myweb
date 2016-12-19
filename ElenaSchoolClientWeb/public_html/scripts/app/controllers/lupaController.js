/* global app */

/**
 * @description Obtiene los datos de los atributos de una tabla que son llaves foraneas y los pinta en una grid
 * @autor AdrianL
 * @since 21 sep 2016
 */
app.controller('lupaController', ['$scope', '$uibModalInstance', '$item', '$myService', '$setting', function ($scope, $uibModalInstance, $item, $myService, $setting) {

        var nombreApp = $setting.varGlobals.nameApp;
        $scope.gridApi = {};
        getEstructuraTabla();
        $scope.foreignTableName = $item.foreignTableName;

        /**
         * Definición de la grid
         */
        $scope.gridOptionsLupa = {
            enableRowSelection: true,
            enableRowHeaderSelection: false,
            enableFiltering: false,
            autoResize: true,
            multiSelect: false,
            noUnselect: true,
            flatEntityAccess: true,
            rowTemplate: rowTemplate()
        };

        /**
         * Row selection
         * @param {type} gridApi
         * @returns {undefined}
         */
        $scope.gridOptionsLupa.onRegisterApi = function (gridApi) {
            $scope.gridApi['gridOptionsLupa'] = gridApi;

            $scope.gridApi['gridOptionsLupa'].cellNav.on.navigate($scope, function (newRowCol, oldRowCol) {
                if (angular.isUndefined(newRowCol.row.isSelected) || !newRowCol.row.isSelected) {
                    $scope.gridApi['gridOptionsLupa'].selection.selectRow(newRowCol.row.entity);
                    $scope.rowSelect = newRowCol.row.entity;
                }
            });

            /**
             * Cambio de fila
             */
            $scope.gridApi['gridOptionsLupa'].selection.on.rowSelectionChanged($scope, function (row) {
                $scope.rowSelect = row.entity;
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
         * Action double click grid
         * @param {type} row
         * @returns {undefined}
         */
        $scope.doubleClickGridAction = function (row) {
            var codigo = '';
            var nombre = '';
            if (!angular.isUndefined(row)) {
                for (var property in row.entity) {
                    if ($scope.rowSelect.hasOwnProperty(property)) {
                        if (property === 'codigo')
                            codigo = row.entity[property];
                        if (property === 'nombre')
                            nombre = row.entity[property];
                    }
                }
            }
            $item.valor = codigo + ' - ' + nombre;
            $item.codigo = codigo;
            $scope.closeLupaAction();
        };

        /**
         * Action close modal
         * @returns {undefined}
         */
        $scope.closeLupaAction = function () {
            $uibModalInstance.dismiss('cancel');
        };

        /**
         * Funcion obtener estructura tabla
         * @returns {undefined}
         */
        function getEstructuraTabla() {
            var model = {nameTable: $item.foreignTableName};
            var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(model), token: null};

            // Request para obtener estructura
            $myService.getEstructuraTablaService(actionRequest, obtenerUrlService('GetStructure')).success(function (data, status, headers, config) {

                // Valida la respuesta del servicio
                if (!isValidResponseService(data))
                    return;

                $scope.modelEstructura = JSON.parse(data.response);

                // Si la estructura es un array obtenemos la consulta
                if (isArrayNotNull($scope.modelEstructura)) {
                    var queryModel = getObjectQueryModel($scope.modelEstructura, null, false, false, $scope.modelEstructura[0].nameTable);
                    var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(queryModel), token: null};

                    // Request para obtener consulta
                    $myService.getConsultaService(actionRequest, obtenerUrlService('GetQuery')).success(function (data, status, headers, config) {
                        // Valida la respuesta del servicio
                        if (!isValidResponseService(data))
                            return;
                        
                        var response = JSON.parse(data.response);
                        
                        if (isArrayNotNull(response.listResult))
                            setListToGrid($scope.gridOptionsLupa, response.listResult, $scope.modelEstructura);
                        else {
                            messageBoxAlert(nombreApp + ' - Lupa', 'No hay datos para mostrar', 'info');
                            $uibModalInstance.dismiss(null);
                        }
                    }).error(function (data, status, headers, config) {
                        console.log(data);
                        messageBoxAlert(nombreApp + ' - Lupa', 'Ocurrió un error al procesar la solicitud', 'error');
                        $uibModalInstance.dismiss(null);
                    });
                } else {
                    messageBoxAlert(nombreApp + ' - Lupa', 'No hay datos para mostrar', 'info');
                    $uibModalInstance.dismiss(null);
                }
            }).error(function (data, status, headers, config) {
                console.log(data);
                messageBoxAlert(nombreApp + ' - Lupa', 'Ocurrió un error al procesar la solicitud', 'error');
                $uibModalInstance.dismiss(null);
            });
        }
        ;

        /**
         * Template action double click
         * @returns {String}
         */
        function rowTemplate() {
            return '<div ng-dblclick="grid.appScope.doubleClickGridAction(row)" ng-class="grid.options.rowStyle(row)" ' +
                    '  <div ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name" class="ui-grid-cell ui-grid-selection" ng-class="{ \'ui-grid-row-header-cell\': col.isRowHeader }"  ui-grid-cell ui-grid-selection></div>' +
                    '</div>';
        }
        ;

    }]);

