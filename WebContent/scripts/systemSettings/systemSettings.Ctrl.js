
angular.module("myApp").controller("systemsettingsCtrl",function($scope, $http) {
					
										var sorting;
										for (var i = 1; i <= 24; i++) {

											sorting = i+":00";

											$("#starttime").append("<option value=" + sorting+ ">" + sorting+ "</option>");
											$("#endtime").append("<option value=" + sorting+ ">" + sorting+ "</option>");
										}
								
					$scope.setHours = function() {
						$http.get("http://localhost/projectmanager/rest/systemSettings/setSystemHours?beginHour="
												+ $("#starttime").val()
												+ "&endHour="
												+ $("#endtime").val())
								.then(function(response) {
											$scope.hours = response.data;
											console.log($scope.hours);
											if ($scope.hours == "OK") {
												alert("You set hours for employees !");
											} else {
												alert("You'r NOT set hours , try again .");
											}
										});
					}

					$scope.SendDays = function() {
						var sunday = $("#Sunday").is(":checked") ? "true"
								: "false";
						var monday = $("#Monday").is(":checked") ? "true"
								: "false";
						var tuesday = $("#Tuesday").is(":checked") ? "true"
								: "false";
						var wednesday = $("#Wednesday").is(":checked") ? "true"
								: "fa lse";
						var thursday = $("#Thursday").is(":checked") ? "true"
								: "false";
						var friday = $("#Friday").is(":checked") ? "true"
								: "false";
						var saturday = $("#Saturday").is(":checked") ? "true"
								: "false";
						$http.get(
								"http://localhost/projectmanager/rest/systemSettings/setSystemDays?sunday="
										+ sunday + "&monday=" + monday
										+ "&tuesday=" + tuesday + "&wednesday="
										+ wednesday + "&thursday=" + thursday
										+ "&friday=" + friday + "&saturday="
										+ saturday).then(function(response) {
							$scope.days = response.data;
							if ($scope.days == 'OK') {
								alert("You set Days to report !!");
							} else {
								alert("You NOT set Days to report !! ");
							}
						});
					}
});