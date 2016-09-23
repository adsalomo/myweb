/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description request a los servicios
 */
app.factory('$myService', ['$http', function ($http) {
    return {
        
        getEstructuraTablaService: function (nameTable) {
            return $http({
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                },
                url: 'http://localhost:8081/web/elenaschool/getEstructuraTabla',
                data: { nameTable: nameTable }
            });
        }
        
    }
}]);

