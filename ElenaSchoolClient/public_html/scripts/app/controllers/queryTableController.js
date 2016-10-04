/* global app */

/**
 * @description Busca en la estructura de una tabla los campos que tengan un valor asignado para realizar un select
 * @autor AdrianL
 * @since 21 sep 2016
 */
app.controller('queryTableController', ['$scope', '$uibModalInstance', '$modelEstructura', '$myService', '$setting', '$gridFormulario', function ($scope, $uibModalInstance, $modelEstructura, $myService, $setting, $gridFormulario) {

        /**
         * Inicializacion variables
         */
        $scope.modelEstructura = $modelEstructura;
        $scope.isTypeOrder = 1;
        $scope.isOpenDivOrder = false;
        clearValorColumn();
        
        /**
         * Asigna null a la propiedad valor del objeto model Estructura
         * @returns {undefined}
         */
        function clearValorColumn(){
            angular.forEach($scope.modelEstructura, function(value, key){
                value.valor = null;
            });
        }

        /**
         * Check in order
         * @returns {undefined}
         */
        $scope.openOrderAction = function () {
            $scope.isOpenDivOrder = false;
            angular.forEach($scope.modelEstructura, function (value, key) {
                if (value.isOrder)
                    $scope.isOpenDivOrder = true;
            });
        };

        $scope.prueba = function () {
            var isOrderAscending = false;
            var isOrderDescending = false;

            if ($scope.isOpenDivOrder) {
                if ($scope.isTypeOrder === 1)
                    isOrderAscending = true;
                else
                    isOrderDescending = true;
            };
            
            $gridFormulario.data = [];

            var obj = getObjectQueryModel($scope.modelEstructura, null, isOrderAscending, isOrderDescending, $scope.modelEstructura[0].nameTable);
            
            $myService.getConsultaService(obj)
                    .success(function (data, status, headers, config) {

                        setListToGrid($gridFormulario, data.listResult, $scope.modelEstructura);
                        $scope.closeQueryTableAction();

                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };

        /**
         * Close modal
         * @returns {undefined}
         */
        $scope.closeQueryTableAction = function () {
            $uibModalInstance.dismiss('cancel');
        };

    }]);


