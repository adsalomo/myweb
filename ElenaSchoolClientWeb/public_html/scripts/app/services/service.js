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
             * @param {type} nameTable
             * @returns {unresolved}
             */
            getEstructuraTablaService: function (nameTable) {
                return $http({
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    url: 'http://localhost:8081/web/elenaschool/getEstructuraTabla',
                    data: {nameTable: nameTable}
                });
            },
            
            /**
             * Peticion http para realizar una consulta sobre una tabla
             * @param {type} queryModel
             * @returns {unresolved}
             */
            getConsultaService: function (queryModel) {
                return $http({
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    url: 'http://localhost:8081/web/elenaschool/getConsulta',
                    data: {
                        listModel: queryModel.listModel,
                        listResult: queryModel.listResult,
                        isOrderAscending: queryModel.isOrderAscending,
                        isOrderDescending: queryModel.isOrderDescending,
                        model: queryModel.model,
                        page: queryModel.page,
                        isPagination: queryModel.isPagination
                    }
                });
            },
            
            /**
             * Peticion http para insertar un registro en una tabla
             * @param {type} queryModel
             * @returns {unresolved}
             */
            insertModelService: function (queryModel) {
                return $http({
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json; charset=utf-8'
                    },
                    url: 'http://localhost:8081/web/elenaschool/insertModel',
                    data: {
                        listModel: queryModel.listModel,
                        model: queryModel.model
                    }
                });
            }
        };
    }]);


