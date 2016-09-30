/**
 * @description Busca en la estructura de una tabla los campos que tengan un valor asignado para realizar un select
 * @autor AdrianL
 * @since 21 sep 2016
 */
app.controller('queryTableController', ['$scope', '$uibModalInstance', '$modelEstructura', '$myService', '$setting', function ($scope, $uibModalInstance, $modelEstructura, $myService, $setting) {
        
        
        $scope.modelEstructura = $modelEstructura;
        
        $scope.activarOrderAscAction = function (index){
            $scope.modelEstructura[index].isOrderDescending = false;
            $scope.modelEstructura[index].isOrderAscending = true;
        };
        
        $scope.activarOrderDescAction = function (index){
            $scope.modelEstructura[index].isOrderAscending = false;
            $scope.modelEstructura[index].isOrderDescending = true;
        };
        
        
        $scope.prueba = function (){
            $scope.modelEstructura;
        };
        
        $scope.closeQueryTableAction = function () {
            $uibModalInstance.dismiss('cancel');
        };
        
}]);


