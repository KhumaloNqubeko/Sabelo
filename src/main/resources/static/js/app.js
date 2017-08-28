/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var app = angular.module('app', ['ngCookies']);
app.controller('RegController', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

        $scope.create = function () {
            var url = "http://localhost:8090/customers/create/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };


            $http.post(url, $scope.cust, config).then(function () {

                if ($scope.cust.password === $scope.cust.conf) {
                    alert("Customer successfully registered!!");
                    $window.location.href = "http://localhost:8090/shop";
                } else {
                    alert("The two passwords do not match!");
                    $scope.cust.password = "";
                    $scope.cust.conf = "";
                }
            }, function () {
                alert("Failed ");
            });
        };
    }]);

app.controller('LoginController', ['$scope', '$http', '$location', '$window', '$cookies', function ($scope, $http, $location, $window, $cookies) {
        var user;

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

                    $cookies.put("id", $scope.customerData.id);
                    $cookies.put("name", $scope.customerData.name);
                    $cookies.put("email", $scope.customerData.email);
                    $cookies.put("number", $scope.customerData.number);
                    $cookies.put("username", $scope.customerData.username);
                    $cookies.put("password", $scope.customerData.password);


                }

            }, function (response) {
                alert(response.toString() + "Failed");
                alert($scope.cust.username + " " + $scope.cust.password);
            });
        };
    }]);

app.controller('ShopController', ['$scope', '$http', '$location', function ($scope, $http, $location) {

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

app.controller('StockController', ['$scope', '$http', '$location', '$window', '$cookies', function ($scope, $http, $location, $window, $cookies) {
        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };


        $scope.addStock = function () {
            var url = "http://localhost:8090/admin/stock/";
            $scope.isTrue = true;

            $http.post(url, $scope.stock, config).then(function () {
                alert($scope.stock.description + " added as stock");
                $cookies.put("stockNumber", $scope.stock.stockNumber);
            }, function () {
                alert("Failed ");
            });

        };

        $scope.selectedUploadFile;
        $scope.uploadFile = function () {
            
            var stocknumber = $cookies.get("stockNumber");
            
            var formData = new FormData();
            formData.append('file', $scope.selectedUploadFile);
            formData.append('description', $scope.stock.description);
            formData.append('category', $scope.stock.category);
            formData.append('price', $scope.stock.price);
            
            $http.post('http://localhost:8090/admin/stock/', formData, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function () {
                alert("Success");
            }, function () {
                alert("Image size too large!!");
            });
        };
    }]);

app.controller('GetStockController', ['$scope', '$http', '$location', '$window', '$cookies', function ($scope, $http, $location, $window, $cookies) {

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

            $http.post(url, product, config).then(function (response) {
                alert(product.description + " added to cart!");
                $cookies.put("productId", product.id);
                $cookies.put("description", product.description);
                $cookies.put("quantity", product.quantity);
                $cookies.put("price", product.price);
                $cookies.put("imageURL", product.imageURL);
            }, function (response) {
                alert(angular.toJson(response) + "Failed");
            });
        };

    }]);

app.controller('GetCartController', ['$scope', '$http', '$location', '$window', '$cookies', function ($scope, $http, $location, $window, $cookies) {

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };
        $scope.total = function () {
            var url = "http://localhost:8090/shop/products/total";

            $http.get(url, config).then(function (response) {
                $scope.totalPrice = response.data;
                $cookies.put("total", totalPrice);

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
        $scope.proceedCheckout = function () {
            var url = "http://localhost:8090/order/place/";

            var id = $cookies.get("id");
            var name = $cookies.get("name");
            var address = $cookies.get("address");
            var email = $cookies.get("email");
            var number = $cookies.get("number");
            var username = $cookies.get("username");
            var password = $cookies.get("password");

            $scope.customer = {
                id: id,
                name: name,
                address: address,
                email: email,
                number: number,
                username: username,
                password: password
            };

            $http.post(url, $scope.customer, config).then(function (response) {
                alert("Order placed!!");
                var total = $cookies.get("total");
                $cookies.put("total", total);
                $window.location.href = "http://localhost:8090/payment";

            }, function (response) {
                alert("Failed to execute!!");
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

        $scope.show = false;

        $scope.updatequantity = function () {

            $scope.show = true;
        };

        $scope.updateQ = function (cart) {
            var url = "http://localhost:8090/updateQuantity/";

            $scope.product = {
                id: cart.id,
                description: cart.description,
                quantity: cart.quantity,
                price: cart.price,
                imageURL: cart.imageURL,
                total: 0.0
            };

            $http.post(url, $scope.product, config).then(function (response) {
                alert("Updated!!");
            }, function (response) {
                alert("Failed!!");
            });
        };
    }]);
app.controller('PlaceOrder', ['$scope', '$http', '$location', '$window', '$cookies', function ($scope, $http, $location, $window, $cookies) {
        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };

        var id = $cookies.get("id");
        var name = $cookies.get("name");
        var address = $cookies.get("address");
        var email = $cookies.get("email");
        var number = $cookies.get("number");
        var username = $cookies.get("username");
        var password = $cookies.get("password");

        $scope.customer = {
            id: id,
            name: name,
            address: address,
            email: email,
            number: number,
            username: username,
            password: password
        };

        $scope.sendCustomer = function () {
            var url = "http://localhost:8090/address/add/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };

            $http.post(url, $scope.customer, config).then(function () {
                alert("Delivery address successfully added!");
                $window.location.href = "http://localhost:8090/thank";

            }, function () {
                alert("Failed");
            });
        };
    }]);

app.controller('DAddressCtrl', ['$scope', '$http', '$location', '$window', '$cookies', function ($scope, $http, $location, $window, $cookies) {
        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };

        $scope.addAdress = function () {
            var url = "http://localhost:8090/address/add/";



            var id = $cookies.get("id");

            $scope.address = {
                city: $scope.d.city,
                suburb: $scope.d.suburb,
                street: $scope.d.street,
                snumber: $scope.d.snumber,
                bname: $scope.d.bname,
                unumber: $scope.d.unumber,
                custID: id
            };

            $http.post(url, $scope.address, config).then(function () {
                alert("Delivery address successfully added!");

                $window.location.href = "http://localhost:8090/thank";

            }, function () {
                alert("Failed");
            });
        };

        $scope.total = function () {
            var url = "http://localhost:8090/shop/products/total";

            $http.get(url, config).then(function (response) {
                $scope.totalPrice = response.data;
                $cookies.put("total", totalPrice);

            }, function (response) {
                $scope.getResultMessage = "Fail!";
                alert(getResultMessage);
            });
        };


        $scope.addPayment = function () {
            var url = "http://localhost:8090/payment/add/";

            var id = $cookies.get("id");

            $scope.payment = {
                cardName: $scope.p.cardName,
                cardNumber: $scope.p.cardNumber,
                expiryDate: $scope.p.expiryDate,
                securityCode: $scope.p.securityCode,
                ZIP: $scope.p.ZIP,
                customerId: id
            };

            $http.post(url, $scope.payment, config).then(function () {
                alert("Payment details successfully added!");
                $window.location.href = "http://localhost:8090/addess";

            }, function () {
                alert("Failed");
            });
        };
    }]);

app.controller('FetchStockController', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {

        $scope.getfunction = function () {
            var url = "http://localhost:8090/admin/stock/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };


            $http.get(url, config).then(function (response) {
                $scope.products = response.data;
            }, function (response) {
                $scope.getResultMessage = "Fail!";
                alert(getResultMessage);
            });
        };

        $scope.deletefromstock = function (stock) {

            var url = "http://localhost:8090/admin/delete/";

            var config = {
                headers: {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
            };

            $http.post(url, stock, config).then(function (response) {
                alert(stock.description + " removed from stock!");
            }, function () {
                alert("Failed to remove product!");
            });
        };

    }]);

app.controller('LoggedInController', ['$scope', '$http', '$location', '$window', '$cookies', function ($scope, $http, $location, $window, $cookies) {



        $scope.getLoggedInUser = function () {
            $scope.name = $cookies.get("name");
            $scope.email = $cookies.get("email");
            $scope.number = $cookies.get("number");
        };

        //$window.location.href = "http://localhost:8090/address/";
    }]);

app.controller('Direction', ['$scope', '$http', '$location', '$window', function ($scope, $http, $location, $window) {



        $scope.getDirection = function () {
            $window.location.href = "http://localhost:8090/addess/";
        };

    }]);

app.controller('GetCustomersController', ['$scope', '$http', '$location', '$window', '$cookies', function ($scope, $http, $location, $window, $cookies) {

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };

        $scope.getAllOrders = function () {
            var url = "http://localhost:8090/orders/";


            $http.get(url, config).then(function (response) {
                $scope.customers = response.data;
            }, function (response) {
                $scope.getResultMessage = "Failed!";
                alert(getResultMessage);
            });
        };

        $scope.getcustomerfunction = function () {
            var url = "http://localhost:8090/customers/";


            $http.get(url, config).then(function (response) {
                $scope.customers = response.data;
            }, function (response) {
                $scope.getResultMessage = "Failed!";
                alert(getResultMessage);
            });
        };
        $scope.show = false;
        $scope.getOrder = function (customer) {

            var url = "http://localhost:8090/getOrders/";

            $cookies.put("orID", customer.id);
            $cookies.put("orName", customer.name);
            $cookies.put("orEmail", customer.email);
            $cookies.put("orNumber", customer.number);
            $cookies.put("orAddress", customer.address);
            $cookies.put("orUsername", customer.username);
            $cookies.put("orPassword", customer.password);

            $http.post(url, customer, config).then(function (response) {

                $scope.orders = response.data;

                $scope.custName = $cookies.get("orName");
                $scope.custEmail = $cookies.get("orEmail");
                $scope.custNumber = $cookies.get("orNumber");

                $scope.show = true;

                $scope.tot = $scope.orders.total;

            }, function (response) {
                alert("Failed!!");
            });
        };

        $scope.getTotal = function () {

            var id = $cookies("orID");
            var name = $cookies("orName");
            var address = $cookies("orAddress");
            var email = $cookies("orEmail");
            var number = $cookies("orNumber");
            var username = $cookies("orUsername");
            var password = $cookies("orPassword");


            $scope.customer = {
                id: id,
                name: name,
                address: address,
                email: email,
                number: number,
                username: username,
                password: password
            };
            var url = "http://localhost:8090/getOrderTotal/";

            $http.post(url, $scope.customer, config).then(function (response) {

                $scope.totalPrice = response.data;

                alert("Passed!!");
            }, function () {
                alert("Failed");
            });
        };

        $scope.isTrue = false;

        $scope.getAddress = function (customer) {
            var url = "http://localhost:8090/getAddress/";

            $http.post(url, customer, config).then(function (response) {
                $scope.address = response.data;
                $scope.isTrue = true;
            }, function (response) {
                alert("Failed!!");
            });
        };

        $scope.isAvailable = false;

        $scope.getPayment = function (customer) {
            var url = "http://localhost:8090/payment/getPayment/";

            $http.post(url, customer, config).then(function (response) {
                $scope.payment = response.data;
                $scope.isAvailable = true;
            }, function (response) {
                alert("Failed!!");
            });
        };

    }]);

app.filter('unique', function () {
    return function (collection, keyname) {
        var output = [],
                keys = [];

        angular.forEach(collection, function (item) {
            var key = item[keyname];
            if (keys.indexOf(key) === -1) {
                keys.push(key);
                output.push(item);
            }
        });

        return output;
    };
});

app.controller('UploadCtrl', function ($scope, $http) {
    $scope.selectedUploadFile;
    $scope.uploadFile = function () {
        var formData = new FormData();
        formData.append('file', $scope.selectedUploadFile);
        formData.append('description', $scope.description);

        $http.post('http://localhost:8090/fileUpload/', formData, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function () {
            alert("Success");
        }, function () {
            alert("Image size too large!!");
        });
    };
});
app.directive('fileModel', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
});
