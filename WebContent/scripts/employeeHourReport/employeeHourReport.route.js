angular.module("myApp").config(function($routeProvider) {
	$routeProvider

.when("/employeehourreportform", { //דיווח שעות לעובד
        templateUrl : "scripts/employeeHourReport/hourreportformemployee.html",
       controller : "employeehourreportformCtrl"
    })
}); 