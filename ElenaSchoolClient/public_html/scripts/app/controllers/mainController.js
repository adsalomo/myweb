/* global app */

/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description controller principal
 */

app.controller('mainController', ['$scope', '$myService', '$uibModal', '$setting', '$generalFactory', function ($scope, $myService, $modal, $setting, $generalFactory) {

        /**
         * Definicion de variables
         */
        $scope.modelEstructura = [];
        $scope.numRow = [];
        $scope.numColumn = $setting.varGlobals.column;
        $scope.isActivoGrid = false;
        $scope.gridApi = {};

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
            actualPage: 0, // Pagina actual
            totalPages: 0, // Total paginas
            pageSize: 0, // Numero registros por pagina
            isPagination: true, // Si la grid es paginada
            isOrderAscending: false, // Ordenamiento consulta
            isOrderDescending: false // Ordenamiento consulta
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
                    $scope.rowSelect = newRowCol.row.entity;
                }
                // Obtiene row index grid selection
                $scope.gridFormulario.rowNumber = $scope.gridFormulario.data.indexOf(newRowCol.row.entity);
            });

            // Selection row
            $scope.gridApi['gridFormulario'].selection.on.rowSelectionChanged($scope, function (row) {
                $scope.rowSelect = row.entity;
            });
        };

        /**
         * Obtiene la estructura de una tabla de la base de datos
         * @param {type} nameTable Nombre tabla a obtener estructura
         * @returns {undefined}
         */
        $scope.getEstructuraTablaAction = function (nameTable) {

            $myService.getEstructuraTablaService(nameTable)
                    .success(function (data, status, headers, config) {

                        $scope.modelEstructura = data;
                        var numRow = Math.floor($scope.modelEstructura.length / $setting.varGlobals.column);
                        var resto = ($scope.modelEstructura.length % $setting.varGlobals.column);
                        if (resto !== 0)
                            numRow++;

                        for (var i = 0, l = numRow; i < l; i++) {
                            $scope.numRow[i] = i;
                        }

                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };

        /**
         * openLupaAction: abre lupa con los registros de los campos q son llaves foraneas
         * @returns {undefined}
         */
        $scope.openLupaAction = function (item) {
            $modal.open({
                templateUrl: 'includes/controls/lupa.html',
                backdrop: false,
                windowClass: 'modal',
                controller: 'lupaController',
                resolve: {
                    $foreignTableName: function () {
                        return item.foreignTableName;
                    }
                }
            }).result.catch(function (resp) {
                item.valor = resp;
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
                if (resp)
                    if (isArrayNotNull($scope.gridFormulario.data))
                        $scope.isActivoGrid = true;
                    else {
                        $scope.isActivoGrid = false;
                        messageBoxAlert($setting.varGlobals.nameApp + ' - Formulario', 'No hay datos para mostrar', 'info');
                    }
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
            setValueStructure($scope.rowSelect, $scope.modelEstructura);
        };

        /**
         * Action select por page
         * @returns {undefined}
         */
        $scope.searchPageAction = function () {
            $scope.isSearchPage = true;
        };

        /**
         * *************************************
         * Definicion eventos grid Anterior, siguiente, primera pagina, ultima pagina
         * *************************************
         */

        /**
         * Action ir siguiente grid
         * @returns {undefined}
         */
        $scope.nextGridAction = function () {
            clearValueStructure($scope.modelEstructura);
            $generalFactory.nextGrid(
                    $scope.gridFormulario,
                    $scope,
                    $scope.modelEstructura,
                    $scope.modelEstructura[0].nameTable,
                    'gridFormulario');
        };

        /**
         * Action ir anterior
         * @returns {undefined}
         */
        $scope.previousGridAction = function () {
            $generalFactory.previousGrid(
                    $scope.gridFormulario,
                    $scope,
                    $scope.modelEstructura,
                    $scope.modelEstructura[0].nameTable,
                    'gridFormulario');
        };
        
        /**
         * Action ir ultima pagina grid
         * @returns {undefined}
         */
        $scope.lastGridAction = function () {
            $generalFactory.lastGrid($scope.gridFormulario, $scope.modelEstructura, $scope.modelEstructura[0].nameTable);
        };
        
        /**
         * Action ir primera pagina
         * @returns {undefined}
         */
        $scope.firtsGridAction = function () {
            $generalFactory.firtsGrid($scope.gridFormulario, $scope.modelEstructura, $scope.modelEstructura[0].nameTable);
        };

    }]);


