app.factory("ListService", function($http) {

	var stateList = [];
	var cityList = [];
	return {

		getList : function() {
			return stateList;
		},
		getC : function() {
			return cityList;
		},

		getUserList : function() {
			var endpoint = "http://localhost:8080/ORS_Project3/StateList";
			$http({
				method : 'GET',
				url : endpoint
			}).success(function(data, status, headers, config) {
				stateList = data.stateList;

			}).error(function(data, status, headers, config) {
				console.log("Error " + data);
			});

		},
		getCity : function(state) {
			var endpoint = "http://localhost:8080/ORS_Project3/CityList";
			$http({
				method : 'POST',
				url : endpoint,
				data : {
					state : state
				}
			}).success(function(data, status, headers, config) {

				cityList = data.cityList;

			}).error(function(data, status, headers, config) {

				console.log("Error " + data);
			});

		}

	};

});