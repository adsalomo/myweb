/**
 * @autor AdrianL
 * @since 21 sep 2016
 * @description definicion app
 */

var app = angular.module('elenaSchoolApp',
        [
            'ngRoute',
            'ui.bootstrap',
            'ngRoute',
            'ui.grid',
            'ui.grid.pagination',
            'ui.grid.resizeColumns',
            'ui.grid.autoResize',
            'ui.grid.selection',
            'ui.grid.cellNav',
            'ui.grid.exporter',
            'ngLoadingSpinner'
        ]);

app.config(['$routeProvider', '$provide', function ($routeProvider, $provide) {
        $routeProvider
                .when('/', {
                    redirectTo: '/home'
                })
                .when('/:page', {
                    templateUrl: function ($routeParams) {
                        return 'views/' + $routeParams.page + '.html';
                    },
                    controller: 'mainController'
                })
                .when('/:page/:child*', {
                    templateUrl: function ($routeParams) {
                        return 'views/' + $routeParams.page + '/' + $routeParams.child + '.html';
                    },
                    controller: 'mainController'
                })
                .otherwise({
                    redirectTo: '/home'
                });
    }]);

app.run(['$rootScope', function ($rootScope) {
    }]);


