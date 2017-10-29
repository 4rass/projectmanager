angular.module("myApp").config(function($routeProvider) {
	$routeProvider.when("/", { // דף כניסה
        templateUrl : "scripts/login/login.html",
        controller : "mainCtrl"
    })
});