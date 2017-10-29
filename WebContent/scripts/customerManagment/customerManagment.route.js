angular.module("myApp").config(function($routeProvider) {
	$routeProvider
   .when("/customermanagment" ,  {
  	  templateUrl :"scripts/customerManagment/managercustomermanagment.html",
   		 controller :   "customermanagmentCtrl" 
   })
});
  	