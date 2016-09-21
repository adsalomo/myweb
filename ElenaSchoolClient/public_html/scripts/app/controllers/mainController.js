/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description controller principal
 */
app.controller('mainController', ['$scope', '$http', '$myService',
    function ($scope, $http, $myService) {
        
        /**
         * Definicion de variables
         */
        $scope.modelEstructura = [];
        
        /**
         * Obtiene la estructura de una tabla de la base de datos
         * @param {type} nameTable Nombre tabla a obtener estructura
         * @returns {undefined}
         */
        $scope.getEstructuraTablaAction = function (nameTable) {

            $myService.getEstructuraTablaService(nameTable)
                    .success(function (data, status, headers, config) {



                    }).error(function (data, status, headers, config) {
                console.log(data);
            });
        };

    }]);


