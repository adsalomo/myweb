/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description controller principal
 */
app.controller('mainController', ['$scope', '$myService', '$uibModal', '$setting', function ($scope, $myService, $modal, $setting) {

        /**
         * Definicion de variables
         */
        $scope.modelEstructura = [];
        $scope.numRow = [];
        $scope.numColumn = $setting.varGlobals.column;

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
                        };

                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };

        /**
         * openLupaAction: abre lupa con los registros de los campos q son llaves foraneas
         * @returns {undefined}
         */
        $scope.openLupaAction = function () {
            $modal.open({
                templateUrl: 'includes/lupa.html',
                backdrop: false,
                windowClass: 'modal',
                controller: 'lupaController',
                resolve: {
                    action: function () {
                        return true;
                    }
                }
            });
        };

    }]);


