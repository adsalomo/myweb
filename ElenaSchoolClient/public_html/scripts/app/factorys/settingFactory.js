/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description factory variables globales
 */
app.factory('$setting', [function () {
        return {
            varGlobals: {
                column: 4, // número columnas en que se dividen los formularios
                nameApp: 'ElenaSchool' // nombre app
            }
        }
    }]);

