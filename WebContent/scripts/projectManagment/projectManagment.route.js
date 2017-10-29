angular.module("myApp").config(function($routeProvider) {
	$routeProvider
  
.when("/projectmanagment", { 	// ניהול פרוייקטים ,!!!!!
        templateUrl : "scripts/projectManagment/managerprojectmanagment.html",
        controller : "projectmanagmentCtrl"
    })
    
});