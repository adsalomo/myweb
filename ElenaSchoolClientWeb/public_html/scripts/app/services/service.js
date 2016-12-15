/* global app */

/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description Peticiones Http
 */

/**
 * 
 * @param {type} $http
 */
app.factory('$myService', ['$http', function ($http) {
        return {
            /**
             * Peticion http para obtener la estructura de una tabla
             * @param {type} actionRequest
             * @returns {unresolved}
             */
            getEstructuraTablaService: function (actionRequest) {
                return $http({
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    url: 'http://localhost:8081/web/elenaschool/getEstructuraTabla',
                    data: {
                        user: actionRequest.user,
                        password: actionRequest.password,
                        credentials: actionRequest.credentials,
                        request: actionRequest.request,
                        token: actionRequest.token
                    }
                });
            },
            /**
             * Peticion http para realizar una consulta sobre una tabla
             * @param {type} actionRequest
             * @returns {unresolved}
             */
            getConsultaService: function (actionRequest) {
                return $http({
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    url: 'http://localhost:8081/web/elenaschool/getConsulta',
                    data: {
                        user: actionRequest.user,
                        password: actionRequest.password,
                        credentials: actionRequest.credentials,
                        request: actionRequest.request,
                        token: actionRequest.token
                    }
                });
            },
            /**
             * Peticion http para insertar un registro en una tabla
             * @param {type} actionRequest
             * @returns {unresolved}
             */
            insertModelService: function (actionRequest) {
                return $http({
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    url: 'http://localhost:8081/web/elenaschool/updateModel',
                    data: {
                        user: actionRequest.user,
                        password: actionRequest.password,
                        credentials: actionRequest.credentials,
                        request: actionRequest.request,
                        token: actionRequest.token
                    }
                });
            }
        };
    }]);


