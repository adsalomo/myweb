/* global app */

/**
 * @description Busca en la estructura de una tabla los campos que tengan un valor asignado para realizar un select
 * @autor AdrianL
 * @since 21 sep 2016
 */
app.controller('queryTableController', ['$scope', '$uibModalInstance', '$myService', '$setting', '$gridFormulario', '$tableName', function ($scope, $uibModalInstance, $myService, $setting, $gridFormulario, $tableName) {

        /**
         * Inicializacion variables
         */
        $scope.modelEstructura = [];
        $scope.isTypeOrder = 1;
        getStructure();

        /**
         * Obtiene la estructura de la tabla
         * @returns {undefined}
         */
        function getStructure() {
            var model = {nameTable: $tableName};
            var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(model), token: null};
            $myService.getStructureTableService(actionRequest, obtenerUrlService('GetStructure')).success(function (data, status, headers, config) {
                // Valida la respuesta del servicio
                if (!isValidResponseService(data))
                    return;
                $scope.modelEstructura = JSON.parse(data.response);
            }).error(function (data, status, headers, config) {
                console.log(data);
                messageBoxAlert('Consulta Tabla', 'Ocurrió un error al procesar su solicitud', 'error');
                $uibModalInstance.dismiss(false);
            });
        }

        /**
         * Action ejecutar consulta
         * @returns {undefined}
         */
        $scope.consultarAction = function () {
            // Define tipo de ordenamiento
            if ($scope.isTypeOrder === 1)
                $gridFormulario.isOrderAscending = true;
            else
                $gridFormulario.isOrderDescending = true;

            $gridFormulario.data = [];

            // Arma objeto queryModel
            var queryModel = getObjectQueryModel($scope.modelEstructura, null, $gridFormulario.isOrderAscending, $gridFormulario.isOrderDescending, $scope.modelEstructura[0].nameTable, $gridFormulario.actualPage, $gridFormulario.isPagination);
            var actionRequest = {user: null, password: null, credentials: null, request: angular.toJson(queryModel), token: null};
            
            // Request para obtener la consulta
            $myService.getQueryService(actionRequest, obtenerUrlService('GetQuery')).success(function (data, status, headers, config) {
                // Valida la respuesta del servicio
                if (!isValidResponseService(data))
                    return;
                
                var response = JSON.parse(data.response);
                $gridFormulario.count = response.count;

                if (isArrayNotNull(response.listResult)) {
                    // Define el tamano de la pagina de acuerdo a si la grid es paginada o no
                    if ($gridFormulario.isPagination) {
                        $gridFormulario.pageSize = response.listResult.length;
                        var resp = ($gridFormulario.count % $gridFormulario.pageSize);
                        $gridFormulario.totalPages = (resp !== 0 ? (Math.floor($gridFormulario.count / $gridFormulario.pageSize) + 1) : ($gridFormulario.count / $gridFormulario.pageSize)) - 1;
                        $gridFormulario.actualPage = 0;
                    } else
                        $gridFormulario.pageSize = response.count;

                    // Llena grid con los datos de la consulta
                    setListToGrid($gridFormulario, response.listResult, $scope.modelEstructura);
                    $uibModalInstance.dismiss(true);
                } else{
                    messageBoxAlert('Consulta Tabla', 'No hay datos para mostrar', 'info');
                    $uibModalInstance.dismiss(false);
                }
            }).error(function (data, status, headers, config) {
                console.log(data);
                messageBoxAlert('Consulta Tabla', 'Ocurrió un error al procesar su solicitud', 'error');
            });
        };

        /**
         * Close modal
         * @returns {undefined}
         */
        $scope.cancelQueryTableAction = function () {
            $uibModalInstance.dismiss(false);
        };

    }]);


