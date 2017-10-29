angular.module("myApp").config(function($routeProvider) {
	$routeProvider
    
.when("/managerhourreport" , {		// דוח שעות למנהל 
    	templateUrl : "scripts/managerHourReport/managerhourreport.html",
    	controller : "hourreportCtrl" 
    })
});