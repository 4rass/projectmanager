app.config(function($routeProvider) {
	$routeProvider
    

	.when("/activproject", {		//פרוייקטים פעילים
	        templateUrl : "scripts/activeProjectCustomer/activprojectscustomer.html",
	        controller : "activprojectscustomerCtrl"
	    })
});    	