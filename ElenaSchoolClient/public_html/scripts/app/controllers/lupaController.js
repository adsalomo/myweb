app.controller('lupaController', ['$scope', '$uibModalInstance', '$foreignTableName', '$myService', function ($scope, $uibModalInstance, $foreignTableName, $myService) {

        $scope.gridOptionsLupa = {
            enableRowSelection: true,
            enableRowHeaderSelection: false,
            enableFiltering: true,
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

                                    setListToGrid($scope.gridOptionsLupa, data.listResult);
                            
                                }).error(function (data, status, headers, config) {
                            console.log(data);
                        });

                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        }
        ;
    }]);

