/* global app */

/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description factory variables globales
 */
app.factory('$setting', [function () {
        return {
            varGlobals: {
                columnsXRow: 2, // número columnas en que se dividen los formularios
                nameApp: 'ElenaSchool' // nombre app
            }
        };
    }]);

