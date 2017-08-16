/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var app = angular.module('app', []);
app.controller('RegController', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

        var vm = this;
        vm.customers = [];
        var cust = {
            name: $scope.name,
            address: $scope.address,
            email: $scope.email,
            number: $scope.number,
            username: $scope.username,
            password: $scope.password
        };

        $scope.create = function () {
            var url = "http://localhost:8090/customers/create/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };


            $http.post(url, $scope.cust, config).then(function () {
                alert("Customer successfully registered!!");
                $window.location.href = "http://localhost:8090/shop";

            }, function () {
                alert("Failed ");
            });
        };
    }]);

app.controller('LoginController', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

        var vm = this;
        vm.customers = [];
        var cust = {

            username: $scope.username,
            password: $scope.password
        };

        $scope.loginCust = function () {
            var url = "http://localhost:8090/customers/login/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };


            $http.post(url, $scope.cust, config).then(function (response) {

                $scope.customerData = response.data;

                if ($scope.customerData.username === null) {
                    alert("Incorrect Username or password");
                } else {
                    alert("Successfully logged in as " + $scope.customerData.name);
                    $window.location.href = "http://localhost:8090/allProducts";
                }

            }, function (response) {
                alert(response.toString() + "Failed");
                alert($scope.cust.username + " " + $scope.cust.password);
            });
        };
    }]);

app.controller('ShopController', ['$scope', '$http', '$location', function ($scope, $http, $location) {

        var vm = this;
        vm.customers = [];
        var prod = {

            description: $scope.description,
            price: $scope.price,
            quantity: $scope.quantity
        };

        $scope.addToCart = function () {
            var url = "http://localhost:8090/shop/products/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };


            $http.post(url, $scope.prod, config).then(function (response) {
                alert($scope.prod.description + " added to cart!");
            }, function (response) {
                alert(response.toString() + "Failed");
            });
        };

    }]);

app.controller('AdminController', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

        var vm = this;
        vm.customers = [];
        var admin = {

            username: $scope.username,
            password: $scope.password
        };

        $scope.loginAdmin = function () {
            var url = "http://localhost:8090/admin/login/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };


            $http.post(url, $scope.admin, config).then(function (response) {

                $scope.customerData = response.data;

                if ($scope.customerData.username === null) {
                    alert("Incorrect Username or password");
                } else {
                    alert("Successfully logged in!!");
                    $window.location.href = "http://localhost:8090/stock";
                }

            }, function (response) {
                alert(response.toString() + "Failed");
            });
        };
    }]);

app.controller('StockController', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

        var vm = this;
        vm.customers = [];
        var stock = {
            description: $scope.description,
            image: $scope.image,
            category: $scope.category,
            price: $scope.price
        };

        $scope.addStock = function () {
            var url = "http://localhost:8090/admin/stock/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };


            $http.post(url, $scope.stock, config).then(function () {
                alert($scope.stock.description + " added as stock");

            }, function () {
                alert("Failed ");
            });

            var stock = {
                description: "",
                category: "",
                price: ""
            };
        };
    }]);
app.controller('GetStockController', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

        $scope.getfunction = function () {
            var url = "http://localhost:8090/admin/stock/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };


            $http.get(url, config).then(function (response) {
                $scope.response = response.data;
            }, function (response) {
                $scope.getResultMessage = "Fail!";
                alert(getResultMessage);
            });
        };

        var prod = {};
        prod = {
            description: $scope.description,
            price: $scope.price,
            quantity: 1
        };

        $scope.addToCart = function (product) {
            var url = "http://localhost:8090/shop/products/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };
            alert(product.description + " " + product.price);



            $http.post(url, product, config).then(function (response) {
                alert(product.description + " added to cart!");
            }, function (response) {
                alert(angular.toJson(response) + "Failed");
            });
        };

    }]);

app.controller('GetCartController', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };
        $scope.total = function () {
            var url = "http://localhost:8090/shop/products/total";

            $http.get(url, config).then(function (response) {
                $scope.totalPrice = response.data;
                
            }, function (response) {
                $scope.getResultMessage = "Fail!";
                alert(getResultMessage);
            });
        };
        
        $scope.getcartfunction = function () {
            var url = "http://localhost:8090/shop/products/";

            $http.get(url, config).then(function (response) {
                $scope.products = response.data;

            }, function (response) {
                $scope.getResultMessage = "Fail!";
                alert(getResultMessage);
            });
        };

        $scope.deletefromcart = function (cart) {
            var url = "http://localhost:8090/shop/product/delete/";

            $http.post(url, cart, config).then(function (response) {

                alert("Product removed!");
            }, function (response) {
                $scope.getResultMessage = "Failed!";
                alert(getResultMessage);
            });
        };

        $scope.updatequantity = function (cart, quantity) {
            var url = "http://localhost:8090/product/update/";

            $http.post(url, cart, config).then(function (response) {

                alert("Product quantity updated!");
            }, function (response) {
                $scope.getResultMessage = "Failed!";
                alert(getResultMessage);
            });
            
            var url1 = "http://localhost:8090/shop/product/update1/";
            $http.post(url1, quantity, config).then(function (response) {

                alert("Quantity sent!");
            }, function (response) {
                $scope.getResultMessage = "Failed!";
                alert(getResultMessage);
            });
        };
    }]);

app.controller('OrderCtrl', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

        $scope.addAdress = function () {
            var url = "http://localhost:8090/address/add/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };


            $http.post(url, $scope.d, config).then(function () {
                alert("Delivery address successfully added!");

            }, function () {
                alert("Failed");
            });
        };
    }]);