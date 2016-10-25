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
                messageBoxAlert('Consulta Tabla', 'Ocurrió un error al procesar su solicitud', 'error');
                $uibModalInstance.dismiss(false);
            });
        }

        /**
         * Action ejecutar consulta
         * @returns {undefined}
         */
        $scope.consultarAction = function () {

            if ($scope.isTypeOrder === 1)
                $gridFormulario.isOrderAscending = true;
            else
                $gridFormulario.isOrderDescending = true;

            $gridFormulario.data = [];

            // Arma objeto queryModel
            var obj = getObjectQueryModel(
                    $scope.modelEstructura,
                    null,
                    $gridFormulario.isOrderAscending,
                    $gridFormulario.isOrderDescending,
                    $scope.modelEstructura[0].nameTable,
                    $gridFormulario.actualPage,
                    $gridFormulario.isPagination
                    );

            /**
             * Request para consultar a tabla
             */
            $myService.getConsultaService(obj).success(function (data, status, headers, config) {
                $gridFormulario.count = data.count;

                if (isArrayNotNull(data.listResult)) {
                    // Define el tamano de la pagina de acuerdo a si la grid es paginada o no
                    if ($gridFormulario.isPagination) {
                        $gridFormulario.pageSize = data.listResult.length;
                        var resp = $gridFormulario.count % $gridFormulario.pageSize;
                        $gridFormulario.totalPages = (resp !== 0 ? (Math.floor($gridFormulario.count / $gridFormulario.pageSize) + 1) : ($gridFormulario.count / $gridFormulario.pageSize)) - 1;
                        $gridFormulario.actualPage = 0;
                    } else
                        $gridFormulario.pageSize = data.count;

                    // Llena grid con los datos de la consulta
                    setListToGrid($gridFormulario, data.listResult, $scope.modelEstructura);
                    
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


