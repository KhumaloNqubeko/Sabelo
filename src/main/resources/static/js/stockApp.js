/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module('stockApp', []);
app.controller('StkCntrl', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

        $scope.getfunction = function () {
            var url = $location.absUrl() + "stock";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };
           

            $http.get(url, config).then(function (response) {
                $scope.response = response.data;
                alert("Success");
            }, function (response) {
                $scope.getResultMessage = "Fail!";
                alert(getResultMessage);
            });
        };
    }]);

