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
        getEstructura();

        /**
         * Obtiene la estructura de la tabla
         * @returns {undefined}
         */
        function getEstructura() {
            $myService.getEstructuraTablaService($tableName).success(function (data, status, headers, config) {

                $scope.modelEstructura = data;

            }).error(function (data, status, headers, config) {
                console.log(data);
            });
        }

        /**
         * Action ejecutar consulta
         * @returns {undefined}
         */
        $scope.consultarAction = function () {
            var isOrderAscending = false;
            var isOrderDescending = false;

            if ($scope.isTypeOrder === 1)
                isOrderAscending = true;
            else
                isOrderDescending = true;

            $gridFormulario.data = [];

            // Arma objero queryModel
            var obj = getObjectQueryModel($scope.modelEstructura, null, isOrderAscending, isOrderDescending, $scope.modelEstructura[0].nameTable, $gridFormulario.actualPage, $gridFormulario.isPagination);

            /**
             * Request para consultar a tabla
             */
            $myService.getConsultaService(obj)
                    .success(function (data, status, headers, config) {
                        $gridFormulario.count = data.count;
                        
                        // Define el tamano de la pagina de acuerdo a si la grid es paginada o no
                        if($gridFormulario.isPagination)
                            $gridFormulario.pageSize = data.listResult.length;
                        else
                            $gridFormulario.pageSize = data.count;
                        
                        // Llena grid con los datos de la consulta
                        setListToGrid($gridFormulario, data.listResult, $scope.modelEstructura);
                        
                        $uibModalInstance.dismiss(true);
                    }).error(function (data, status, headers, config) {
                console.log(data);
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


