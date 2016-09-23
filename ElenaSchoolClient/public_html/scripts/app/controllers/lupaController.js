app.controller('lupaController', ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {
        
        $scope.closeLupaAction = function(){
             $uibModalInstance.dismiss('cancel');
        };
}]);

