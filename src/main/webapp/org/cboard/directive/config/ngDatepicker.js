/**
 *
 */
cBoard.directive('ngTime',function () {
    return {
        restrict : 'A',
        require : '?ngModel',
        link : function ($scope, $element, $attrs, $ngModel) {
            if (!$ngModel) {
                return;
            }
            /*$('.form_datetime').datetimepicker({
                language:  'zh-CN',
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1
            });*/
            $('.form_date').datetimepicker({
                language:  'zh-CN',
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                // startView: 2,
                startView: 4,
                minView: 2,
                forceParse: 0,
                pickerPosition:  'bottom-left'
            });
            /*$('.form_time').datetimepicker({
                language:  'zh-CN',
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 1,
                minView: 0,
                maxView: 1,
                forceParse: 0
            });*/
        }
    }
})