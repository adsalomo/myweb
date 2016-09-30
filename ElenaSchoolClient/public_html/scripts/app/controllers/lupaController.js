/**
 * @description Obtiene los datos de los atributos de una tabla que son llaves foraneas y los pinta en una grid
 * @autor AdrianL
 * @since 21 sep 2016
 */
app.controller('lupaController', ['$scope', '$uibModalInstance', '$foreignTableName', '$myService', '$setting', function ($scope, $uibModalInstance, $foreignTableName, $myService, $setting) {

        var nombreApp = $setting.varGlobals.nameApp;

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
            flatEntityAccess: true
        };

        getEstructuraTabla();

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

            /**
             * Request para obtener estructura
             */
            $myService.getEstructuraTablaService($foreignTableName)
                    .success(function (data, status, headers, config) {

                        $scope.modelEstructura = data;
                        var queryModel = {listModel: $scope.modelEstructura};

                        /**
                         * Request para obtener consulta
                         */
                        $myService.getConsultaService(queryModel)
                                .success(function (data, status, headers, config) {

                                    if (isArrayNotNull(data.listResult))
                                        setListToGrid($scope.gridOptionsLupa, data.listResult, $scope.modelEstructura);
                                    else {
                                        messageBoxAlert(nombreApp + ' - Lupa', 'No hay datos para mostrar', 'info');
                                        $uibModalInstance.dismiss('cancel');
                                    }

                                }).error(function (data, status, headers, config) {
                            console.log(data);
                        });

                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        }
        ;
    }]);

