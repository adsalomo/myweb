/* global app */

/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description factory general para funciones globales
 */
app.factory('$generalFactory', ['$http', function ($http) {
        return {
            /**
             * Ir siguiente en grid
             * @param {type} grid
             * @param {type} scope
             * @param {type} structure
             * @param {type} table
             * @param {type} nameObject
             * @returns {undefined}
             */
            nextGrid: function (grid, scope, structure, table, nameObject){
                grid.rowNumber += 1;
                
                if(grid.pageSize === grid.rowNumber){
                    
                }else{
                    scope.gridApi[nameObject].selection.selectRowByVisibleIndex(grid.rowNumber)
                }
            }
        };
    }]);

