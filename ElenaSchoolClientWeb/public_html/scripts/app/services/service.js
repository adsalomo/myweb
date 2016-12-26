/* global app */

/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description Peticiones Http
 */

/**
 * Manejador de servios
 * @param {type} $http
 */
app.factory('$myService', ['$http', function ($http) {
        return {
            
            /**
             * Peticion http para obtener la estructura de una tabla
             * @param {type} actionRequest
             * @param {type} url
             * @returns {unresolved}
             */
            getStructureTableService: function (actionRequest, url) {
                return $http({
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    url: url,
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
             * @param {type} url
             * @returns {unresolved}
             */
            getQueryService: function (actionRequest, url) {
                return $http({
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    url: url,
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
             * @param {type} url
             * @returns {unresolved}
             */
            insertModelService: function (actionRequest, url) {
                return $http({
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    url: url,
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
             * 
             * @param {type} actionRequest
             * @param {type} url
             * @returns {unresolved}
             */
            getMaxCodeService: function (actionRequest, url) {
                return $http({
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    url: url,
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


