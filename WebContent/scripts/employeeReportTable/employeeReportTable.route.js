
angular.module("myApp").config(function($routeProvider) {
	$routeProvider
  
    .when("/employeehourreporttable", { //  YEARANDMONTH דוח שעות לעובד
        templateUrl : "scripts/employeeReportTable/hourreporttableofemployee.html",
        controller : "employeehourreporttableCtrl"
    })
});