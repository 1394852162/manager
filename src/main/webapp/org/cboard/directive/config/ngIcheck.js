/**
 * Created by 陶鹏飞 on 2018/1/23.
 */

/*
 * angular directive ng-icheck
 *
 * @description icheck is a plugin of jquery for beautifying checkbox & radio, now I complied it with angular directive
 * @require jquery, icheck
 * @example <input type="radio" ng-model="paomian" value="kangshifu" ng-icheck>
 *          <input type="checkbox" class="icheckbox" name="mantou" ng-model="mantou" ng-icheck checked>
 */
/**
 * 使用了icheck插件后，会生成一个美化过的div覆盖在原来的checkbox或者radio之上，
 * 而原来的checkbox或者radio会被影藏。故而，当我们点击它们时，不会直接触发事件，
 * 使得绑定到checkbox或者radio上的model值改变。所以我们这里需要重新绑定事件，
 * 使用$ngModel.$setViewValue() 方法来给model赋值。具体逻辑，则相根据checkbox和radio而不同。
 */

cBoard.directive('ngIcheck', function($compile) {
    return {
        restrict : 'A',
        require : '?ngModel',
        link : function($scope, $element, $attrs, $ngModel) {
            // console.log($scope);
            console.log($element);
            // console.log($attrs);
            // console.log($ngModel);
            /*if($ngModel.$attr.checked === true){
                $($element).iCheck('check');
            }*/
            if (!$ngModel) {
                return;
            }
            //using iCheck
            $($element).iCheck({
                labelHover : false,
                cursor : true,
                checkboxClass : 'icheckbox_square-blue',
                radioClass : 'iradio_square-blue',
                increaseArea : '20%'
            }).on('ifChecked', function(event) {
                console.log(event.target.checked);
                if ($attrs.type == "checkbox") {
                    //checkbox, $ViewValue = true/false/undefined
                    $scope.$apply(function() {
                        $ngModel.$setViewValue(!($ngModel.$modelValue == undefined ? false : $ngModel.$modelValue));
                    });
                } else {
                    // radio, $ViewValue = $attrs.value
                    $scope.$apply(function() {
                        $ngModel.$setViewValue($attrs.value);
                        // $ngModel.$setViewValue();
                    });
                }
            }).on('ifChecked', function(event) {
                console.log($attrs);
                console.log($ngModel);
                /*if ($attrs.type == "checkbox") { ifClicked
                    $scope.$apply(function() {
                        return $ngModel.$setViewValue(event.target.checked);
                    });
                } else {
                    return $scope.$apply(function() {
                        return $ngModel.$setViewValue($attrs.value);
                    });
                }*/
                /*if ($(element).attr('type') === 'radio' && $attrs['ngModel']) {
                    return $scope.$apply(function() {
                        return $ngModel.$setViewValue($attrs.value);
                    });
                }*/
            })/*.on('ifChanged', function(event) {
                console.log($attrs);
                console.log($ngModel);时
                if ($(element).attr('type') === 'checkbox' && $attrs['ngModel']) {
                    $scope.$apply(function() {
                        return ngModel.$setViewValue(event.target.checked);
                    });
                }
                if ($(element).attr('type') === 'radio' && $attrs['ngModel']) {
                    return $scope.$apply(function() {
                        return $ngModel.$setViewValue($attrs.value);
                    });
                }
            })*/;
        },
    };
});