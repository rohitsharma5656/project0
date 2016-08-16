app.controller("StateListCtl", function($scope, ListService) {

	ListService.getUserList();

	$scope.$watch(ListService.getList, function(newValue) {
		$scope.states = newValue;

	}, true);
	$scope.$watch(ListService.getC, function(newValue) {

		$scope.cities = newValue;

	}, true);

	$scope.getCityList = function() {
		var c = $scope.city;
		if (c != null) {
			ListService.getCity(c.state);
		} else {
			$scope.cities = [];
		}
	};
});