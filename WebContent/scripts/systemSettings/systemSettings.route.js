angular.module("myApp").config(function($routeProvider) {
	$routeProvider
  					//הגדרות מערכת
   .when("/systemsettings" ,  {
  	  templateUrl :"scripts/systemSettings/systemsettings.html",
   		 controller :   "systemsettingsCtrl" 
   })
});