/* global app */

/**
 * @description Busca en la estructura de una tabla los campos que tengan un valor asignado para realizar un select
 * @autor AdrianL
 * @since 21 sep 2016
 */
app.controller('queryTableController', ['$scope', '$uibModalInstance', '$myService', '$setting', '$gridFormulario', '$tableName', '$generalFactory', function ($scope, $uibModalInstance, $myService, $setting, $gridFormulario, $tableName, $generalFactory) {

        /**
         * Inicializacion variables
         */
        $scope.modelEstructura = [];
        $scope.isTypeOrder = 1;

        /**
         * Obtiene la estructura de la tabla
         */
        $scope.getStructureAction = function() {
            var vModel = {nameTable: $tableName};
            var vActionRequest = {user: null, password: null, credentials: null, request: angular.toJson(vModel), token: null};
            $myService.getStructureTableService(vActionRequest, obtenerUrlService('GetStructure')).success(function (data, status, headers, config) {
                // Valida la respuesta del servicio
                if (!isValidResponseService(data))
                    return;
                $scope.modelEstructura = JSON.parse(data.response);
            }).error(function (data, status, headers, config) {
                console.log(data);
                messageBoxAlert('Consulta Tabla', 'Ocurrió un error al procesar su solicitud', 'error');
                $uibModalInstance.dismiss(false);
            });
        };

        /**
         * Action ejecutar consulta
         */
        $scope.consultarAction = function () {
            // Define tipo de ordenamiento
            if ($scope.isTypeOrder === 1)
                $gridFormulario.isOrderAscending = true;
            else
                $gridFormulario.isOrderDescending = true;

            $gridFormulario.data = [];

            // Arma objeto queryModel
            var vQueryModel = { listModel: $scope.modelEstructura, 
                isOrderAscending: $gridFormulario.isOrderAscending, 
                isOrderDescending: $gridFormulario.isOrderDescending,
                model: $scope.modelEstructura[0].nameTable 
            };
            
            var vActionRequest = {user: null, password: null, credentials: null, request: angular.toJson(vQueryModel), token: null};
            
            // Request para obtener la consulta
            $myService.getQueryService(vActionRequest, obtenerUrlService('GetQuery')).success(function (data, status, headers, config) {
                // Valida la respuesta del servicio
                if (!isValidResponseService(data))
                    return;
                
                var vResponse = JSON.parse(data.response);

                if (vResponse !== null && angular.isDefined(vResponse) && isArrayNotNull(vResponse.listResult)) {
                    // Llena grid con los datos de la consulta
                    $generalFactory.setListToGrid($gridFormulario, vResponse.listResult, $scope.modelEstructura);
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


