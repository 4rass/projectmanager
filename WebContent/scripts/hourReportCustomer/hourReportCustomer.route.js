	
angular.module("myApp").config(function($routeProvider) {
	$routeProvider
   
		//דוח שעות ללקוח
   .when("/hourreportforcustomer" ,  {
  	  templateUrl :"scripts/hourReportCustomer/hourreportforcustomer.html",
   		 controller :   "hourreportforcustomerCtrl" 
  	})
});