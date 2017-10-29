app.config(function($routeProvider) {
	$routeProvider

  .when("/employeemanagment", { 	//ניהול עובדים
        templateUrl : "scripts/employeeManagment/manageremployeemanagment.html",
        controller : "employee1Controller"
   })
});