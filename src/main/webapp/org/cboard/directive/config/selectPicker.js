/*
cBoard.directive('selectWatcher', function ($timeout) {
    return {
        link: function (scope, element, attr) {
            var last = attr.last;
            if (last === "true") {
                $timeout(function () {
                    $(element).parent().selectpicker('val', 'any');
                    $(element).parent().selectpicker('refresh');
                });
            }
        }
    };
});*/

cBoard.directive('selectPicker', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        require: '?ngModel',
        priority: 10,
        compile: function (tElement, tAttrs, transclude) {
            tElement.selectpicker($parse(tAttrs.selectpicker)());
            tElement.selectpicker('refresh');
            return function (scope, element, attrs, ngModel) {
                if (!ngModel) return;

                scope.$watch(attrs.ngModel, function (newVal, oldVal) {
                    scope.$evalAsync(function () {
                        if (!attrs.ngOptions || /track by/.test(attrs.ngOptions)) element.val(newVal);
                        element.selectpicker('refresh');
                    });
                });

                ngModel.$render = function () {
                    scope.$evalAsync(function () {
                        element.selectpicker('refresh');
                    });
                }
            };
        }

    };
}])
