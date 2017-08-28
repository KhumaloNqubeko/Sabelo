/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module('nqubekoApp', ['ngCookies']);
app.controller('UploadCtrl', function($scope, $http){
    $scope.selectedUploadFile;
    $scope.uploadFile = function(){
        var formData = new FormData();
        formData.append('file', $scope.selectedUploadFile);
        formData.append('description', $scope.description);
        formData.append('price', $scope.price);
        
        $http.post('http://localhost:8090/fileUpload/', formData, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function(){
            alert("Success");
        }, function(){
            alert("Image size too large!!");
        });
    };
});
app.directive('fileModel', function($parse){
    return {
        restrict: 'A',
        link: function(scope, element, attrs){
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
});
